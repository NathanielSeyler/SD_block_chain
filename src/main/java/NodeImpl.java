import java.util.LinkedList;

public class NodeImpl implements Node{
	
	private NodeImpl[] voisins;
	private LinkedList<String> fileOperations;
	private LinkedList<Participant> participants;
	
	private Block racine;
	private Block cur;
	
	public NodeImpl(){
		voisins = null;
		fileOperations = new LinkedList<String>();
		participants = new LinkedList<Participant>();
		racine = null;
		cur = racine ;
	}
	
	public void setVoisins(NodeImpl[] tab)
	{
		voisins = tab;
	}
	
	
	public void inscriptionParticipant(Participant p)
	{
		participants.add(p);
		fileOperations.add("IB : " + p + " à " + this);
	}
	
	public void creationBlock()
	{
		float v = 1 / participants.size();
		for ( Participant p : participants)
		{
			p.debit(v);
			fileOperations.add("CB : " + v + " à " + p);
		}
		//TODO implements here
		//calcul du hash + new block + ajout operations dans le block + transmition du block
	}
	
	public void echangerBlock(Participant p1,Participant p2,float v)
	{
		p1.credit(v);
		p2.debit(v);
		fileOperations.add("EB : " + v + " : " + p1 + " à " + p2);
	}
	
	public float possede(Participant p)
	{
		fileOperations.add("PB : " + p + " à " + this);
		return p.getValeur();
	}
}
