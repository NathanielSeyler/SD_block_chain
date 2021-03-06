import java.rmi.* ; 
import java.net.MalformedURLException ; 
import java.util.*;

public class Simulation {
	public static void main(String[] args){
		//TODO implements here
		if (args.length != 2)
		{ 
			System.out.println("Usage : java Simulation <machine du Serveur> <port du rmiregistry>") ;
			System.exit(1);
		}
		try {
			
			Participant p = new Participant();
			BlockNode bn = new BlockNode();
			NodeImpl ni = bn.bind(args[1]);
			Node n = p.connect(args);
			p.inscriptionNode(n);
			n.creationBlock();
			p.possede(n);
		
			System.exit(0);
		}
		catch (RemoteException re) { System.out.println(re) ; }
	} 
}
