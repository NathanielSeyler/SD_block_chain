import java.net.* ;
import java.rmi.* ;

public class BlockNode {
	//class qui rebind RMI
	//private LinkedList<String> fileOperations;
	//private LinkedList<Participant> participants;
	//TODO implements here
	public static void main(String [] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage : java Serveur <port du rmiregistry>") ;
			System.exit(0) ;
		}
		try
			{
				NodeImpl objLocal = new NodeImpl () ;
				Naming.rebind("rmi:://localhost:" + args[0] + "/Node",objLocal) ;
				System.out.println("Serveur Node pret") ;
			}
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
	}
}
