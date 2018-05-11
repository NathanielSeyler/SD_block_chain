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
	
	 public static void main(String [] args)
	{
		if (args.length != 1) 
			System.out.println("Usage : java Participant <machine du Serveur> <port du rmiregistry>") ;
		else
		{
	    try
			{
				Node bn = (Node) Naming.lookup("rmi://" + args[0] + ":" + args[1] + "/Node") ;
	
	    }
	    catch (NotBoundException re) { System.out.println(re) ; }
	    catch (RemoteException re) { System.out.println(re) ; }
	    catch (MalformedURLException e) { System.out.println(e) ; }
		}
  }
}
