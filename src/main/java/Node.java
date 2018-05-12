import java.rmi.Remote ; 
import java.rmi.RemoteException ;

public interface Node extends Remote {
	public void setVoisins(NodeImpl[] tab)
		throws RemoteException;
	public void inscriptionParticipant(Participant p)
		throws RemoteException;
	public void creationBlock()
		throws RemoteException;
	public void echangerBlock(Participant p1,Participant p2,float val)
		throws RemoteException;
	public float possede(Participant p)
		throws RemoteException;
	public boolean inscrit(Participant p)
		throws RemoteException;
	/*public void envoieBlock(NodeImpl src,Block b)
		throws RemoteException;*/
	public void receptionBlock(NodeImpl src,Block b)
		throws RemoteException;
	public void receptionOperation(String op)
		throws RemoteException;
}
