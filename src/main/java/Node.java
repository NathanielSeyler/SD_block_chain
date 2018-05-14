import java.rmi.Remote ; 
import java.rmi.RemoteException ;

public interface Node extends Remote {
	public void setVoisins(NodeImpl[] tab)
		throws RemoteException;
	public void inscriptionParticipant(int p)
		throws RemoteException;
	public void creationBlock()
		throws RemoteException;
	public void echangerBlock(int p1,int p2,float val)
		throws RemoteException;
	public float possede(int p)
		throws RemoteException;
	public boolean inscrit(int p)
		throws RemoteException;
	public void envoieBlock(NodeImpl src,int prof)
		throws RemoteException;
	public void receptionBlock(NodeImpl src,Block b)
		throws RemoteException;
	public void receptionOperation(String op)
		throws RemoteException;
}
