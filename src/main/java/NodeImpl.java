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
		float v = 1 / participants.size();
		for ( Participant p : participants)
		{
			//p.debit(v);
			fileOperations.add("CB : " + v + " à " + p);
		}
		//TODO implements here
		//calcul du hash + new block + ajout operations dans le block + transmition du block
		String ash = "";
		LinkedList<String> op = new LinkedList<String>();
		Block b = new Block(this,ash,op);
		if(b.getProfondeur() == 0)
		{
			racine = b;
			cur = racine;
		}
		else
		{
			cur.setNext(b);
			cur = b ;
		}
	}
	
	public void echangerBlock(Participant p1,Participant p2,float v)
		throws RemoteException
	{
		//p1.credit(v);
		//TODO implements here
		//p2.debit(v);
		fileOperations.add("EB : " + v + " : " + p1 + " à " + p2);
		//transmition file 
	}
	
	public float possede(Participant p)
		throws RemoteException
	{
		fileOperations.add("PB : " + p + " à " + this);
		//transmition file
		//TODO implements here
		return 0;
	}
}
