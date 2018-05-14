import java.rmi.* ; 
import java.net.MalformedURLException ; 
import java.util.*; 

public class Participant {
	//on peut peut etre ajouter un identifiant a voir si c'est utile
	private static int id =-1 ;
	
	public Participant()
	{
		id++;
	}
	
	public void inscriptionNode(Node n)
	{
		try
		{
			n.inscriptionParticipant(id);
			System.out.println("je me suis inscrit a un noeud");
		}
	    catch (RemoteException re) { System.out.println(re) ; }
		
	}
	
	public void echangerBlock(Node n , int p ,float v)
	{
		try 
		{
			if(n.inscrit(id) && n.inscrit(p))
			{
				n.echangerBlock(id,p,v);
			}
			else
				System.out.println(p + " n'existe pas");
		}
		catch (RemoteException re) { System.out.println(re) ; }
	}
	
	public float possede(Node n)
	{
		float v = 0;
		try
		{
			v = n.possede(id);
		}
		catch (RemoteException re) { System.out.println(re) ; }
		System.out.println(v);
		return v;
	}
	
	 public Node connect(String [] args)
	{
		if (args.length != 2)
		{ 
			System.out.println("Usage : java Participant <machine du Serveur> <port du rmiregistry>") ;
			return null;
		}
		else
		{
			Node bn = null;
			try
				{
					bn = (Node) Naming.lookup("rmi://" + args[0] + ":" + args[1] + "/Node") ;
					System.out.println("connection a un noeud");
			}
			catch (NotBoundException re) { System.out.println(re) ; }
			catch (RemoteException re) { System.out.println(re) ; }
			catch (MalformedURLException e) { System.out.println(e) ; }
			
			return bn;
		}
  }
}
