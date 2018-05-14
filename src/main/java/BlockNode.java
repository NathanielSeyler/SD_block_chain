import java.net.* ;
import java.rmi.* ;

public class BlockNode {
	//class qui rebind RMI
	//private LinkedList<String> fileOperations;
	//private LinkedList<Participant> participants;
	//TODO implements here
	public NodeImpl bind(String args)
	{
		NodeImpl ni = null;
		try
			{
				ni = new NodeImpl () ;
				Naming.rebind("rmi://localhost:" + args + "/Node",ni) ;
				System.out.println("Serveur Node pret") ;
			}
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
		return ni;
	}
}
