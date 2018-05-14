import java.util.LinkedList;
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.net.InetAddress.* ;
import java.net.* ;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NodeImpl 
	extends UnicastRemoteObject 
	implements Node
{
	
	private NodeImpl[] voisins;
	private LinkedList<String> fileOperations;
	private LinkedList<Integer> participants;
	
	private Block racine;
	private Block cur;
	private int profAttendue;
	
	public NodeImpl()
		throws RemoteException
	{
		super();
		voisins = null;
		fileOperations = new LinkedList<String>();
		participants = new LinkedList<Integer>();
		racine = null;
		cur = racine ;
		profAttendue = 0;
	}
	
	public void setVoisins(NodeImpl[] tab)
		throws RemoteException
	{
		voisins = tab;
	}
	
	public boolean inscrit(int p)
		throws RemoteException
	{
		Block tmp = racine;
		LinkedList<String> ops;
		String test = "IB:" + p + ":" + this;
		
		while (tmp != null)
		{
			ops = tmp.getOperations();
			for(String op : ops)
			{
				if(op.compareTo(test) == 0)
					return true;
			}
		}
		return false;
	}
	
	public void inscriptionParticipant(int p)
		throws RemoteException
	{
		participants.add(new Integer(p));
		String op = "IB:" + p + ":" + this;
		fileOperations.add(op);
		System.out.println(op);
		if(voisins != null)
			for(NodeImpl v : voisins)
				v.receptionOperation(op);
	}
	
	public void creationBlock()
		throws RemoteException
	{
		String ash = "";
		LinkedList<String> ops = new LinkedList<String>();
		int i;
		Block b = new Block(this);
		for(i = 0; i < b.getCapacity();i++)
			ops.add(fileOperations.poll());
			
		b.setOperations(ops);
		if(b.getProfondeur() == 0)
		{
			ash = hachage(b);
			b.setHash(ash);
			racine = b;
			cur = racine;
		}
		else
		{
			ash = hachage(cur);
			b.setHash(ash);
			cur.setNext(b);
			cur = b ;
		}
		
		float val = 1 / participants.size();
		String op;
		for ( Integer p : participants)
		{
			op = "CB:" + val + ":" + p.toString();
			fileOperations.add(op);
			System.out.println(op);
			if(voisins != null)
				for(NodeImpl v : voisins)
				{
					v.receptionOperation(op);
					v.receptionBlock(this,b);
				}
		}
	}
	
	public void echangerBlock(int p1,int p2,float val)
		throws RemoteException
	{
		String op = "EB:" + val + ":" + p1 + ":" + p2;
		fileOperations.add(op);
		System.out.println(op);
		if(voisins != null)
			for(NodeImpl v : voisins)
				v.receptionOperation(op); 
	}
	
	public float possede(int p)
		throws RemoteException
	{
		String op = "PB:" + p + ":" + this;
		fileOperations.add(op);
		System.out.println(op);
		if(voisins != null)
			for(NodeImpl v : voisins)
				v.receptionOperation(op);
		
		Integer ip = new Integer(p);	
		Block tmp = racine;
		LinkedList<String> ops;
		String[] tab;
		float val = 0;
		while (tmp != null)
		{
			ops = tmp.getOperations();
			for(String op1 : ops)
			{
				tab = op1.split(":");
				if(tab[0].compareTo("CB") == 0 && tab[2].compareTo(ip.toString()) == 0 )
					val += Float.valueOf(tab[1]);
				if(tab[0].compareTo("EB") == 0 && tab[2].compareTo(ip.toString()) == 0 )
					val -= Float.valueOf(tab[1]);
				if(tab[0].compareTo("EB") == 0 && tab[3].compareTo(ip.toString()) == 0 )
					val += Float.valueOf(tab[1]);
			}
		}
		
		return val;
	}
	
	public void receptionOperation(String op)
		throws RemoteException
	{
		fileOperations.add(op);
		System.out.println(op);
	}
	
	public void receptionBlock(NodeImpl src , Block b)
		throws RemoteException
	{
		int profBlock = b.getProfondeur();
		if( profBlock == profAttendue)
		{
			if(profBlock == 0)
			{
				racine = b;
				cur = racine;
				profAttendue++;
				libereOperations(b);
			}
			else
			{
				cur.setNext(b);
				cur = b;
				profAttendue++;
				libereOperations(b);
			}
		}
		else if(profBlock > profAttendue)
		{
			//envoyer ce qui manque
			src.envoieBlock(this,profAttendue);
		}
		else if( profBlock < profAttendue)
		{
			int longueurChaine = 0;
			Block tmp = b;
			while(tmp.getNext() != null)
			{
				tmp = tmp.getNext();
				longueurChaine++;
			}
			longueurChaine++;
			
			if(profBlock + longueurChaine > profAttendue)
			{
				//prends la chaine de plus grande longueur
				cur.setNext(b);
				cur = tmp;
				profAttendue = profBlock + longueurChaine;
				libereOperations(b);
			}	
		}
	}
	
	public void envoieBlock(NodeImpl src , int prof)
		throws RemoteException
	{
		Block tmp = racine;
		while (tmp.getProfondeur() != prof)
		{
			tmp = tmp.getNext();
		}
		src.receptionBlock(this,tmp);
	}
	
	private String hachage(Block b)
	{
		String value = b.toString();
		return calculHash(value);
	}
	
	private String calculHash(String value)
	{
		String hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(value.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	private void libereOperations(Block b)
	{
		Block tmp = b;
		while(tmp != null)
			for(String op : tmp.getOperations() )
				for(String fop : fileOperations )
					if(fop.compareTo(op) == 0)
						fileOperations.remove(fop);
	}
}
