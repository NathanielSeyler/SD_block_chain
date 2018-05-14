import java.rmi.* ; 
import java.net.MalformedURLException ; 
import java.util.*; 

public class Participant {
	//on peut peut etre ajouter un identifiant a voir si c'est utile
	/*private float valeur ;
	
	public Participant(){
		valeur = 0;
	}
	
	public float getValeur()
	{
		//TODO implements here
		return valeur;
	}
	
	public void debit(float v)
	{
		valeur += v;
	}
	
	public void credit(float v)
	{
		valeur -= v;
	}*/
	
	public void inscriptionNode(Node n)
	{
		try
		{
			n.inscriptionParticipant(this);
			System.out.println("je me suis inscrit a un noeud");
		}
	    catch (RemoteException re) { System.out.println(re) ; }
		
	}
	
	public void echangerBlock(Node n , Participant p ,float v)
	{
		try 
		{
			if(n.inscrit(this) && n.inscrit(p))
			{
				n.echangerBlock(this,p,v);
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
			v = n.possede(this);
		}
		catch (RemoteException re) { System.out.println(re) ; }
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
