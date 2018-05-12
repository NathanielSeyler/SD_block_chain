import java.util.LinkedList;
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.net.InetAddress.* ;
import java.net.* ;

public class NodeImpl 
	extends UnicastRemoteObject 
	implements Node
{
	
	private NodeImpl[] voisins;
	private LinkedList<String> fileOperations;
	private LinkedList<Participant> participants;
	
	private Block racine;
	private Block cur;
	
	public NodeImpl()
		throws RemoteException
	{
		super();
		voisins = null;
		fileOperations = new LinkedList<String>();
		participants = new LinkedList<Participant>();
		racine = null;
		cur = racine ;
	}
	
	public void setVoisins(NodeImpl[] tab)
		throws RemoteException
	{
		voisins = tab;
	}
	
	public boolean inscrit(Participant p)
		throws RemoteException
	{
		//TODO implements here
		return false;
	}
	
	public void inscriptionParticipant(Participant p)
		throws RemoteException
	{
		participants.add(p);
		fileOperations.add("IB : " + p + " à " + this);
		//transmition file
	}
	
	public void creationBlock()
		throws RemoteException
	{
		float val = 1 / participants.size();
		String op;
		for ( Participant p : participants)
		{
			op = "CB : " + val + " à " + p;
			fileOperations.add(op);
			for(NodeImpl v : voisins)
				v.receptionOperation(op);
		}
		//TODO implements here
		//calcul du hash + new block + ajout operations dans le block + transmition du block
		String ash = "";
		LinkedList<String> ops = new LinkedList<String>();
		Block b = new Block(this,ops);
		if(b.getProfondeur() == 0)
		{
			//ash = hachage(b);
			b.setHash(ash);
			racine = b;
			cur = racine;
		}
		else
		{
			//ash = hachage(cur);
			b.setHash(ash);
			cur.setNext(b);
			cur = b ;
		}
	}
	
	public void echangerBlock(Participant p1,Participant p2,float val)
		throws RemoteException
	{
		String op = "EB : " + val + " : " + p1 + " à " + p2;
		fileOperations.add(op);
		for(NodeImpl v : voisins)
			v.receptionOperation(op); 
	}
	
	public float possede(Participant p)
		throws RemoteException
	{
		String op = "PB : " + p + " à " + this;
		fileOperations.add(op);
		for(NodeImpl v : voisins)
			v.receptionOperation(op);
			
		//TODO implements here
		return 0;
	}
	
	public void receptionOperation(String op)
		throws RemoteException
	{
		fileOperations.add(op);
	}
	
	public void receptionBlock(NodeImpl src , Block b)
		throws RemoteException
	{
		
	}
}
