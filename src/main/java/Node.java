import java.util.LinkedList;

public class Node {
	
	private Node[] voisins;
	private LinkedList<String> fileOperations;
	private LinkedList<Participant> participants;
	
	private Block racine;
	private Block cur;
	
	public Node(){
		voisins = null;
		fileOperations = new LinkedList<String>();
		participants = new LinkedList<Participant>();
		racine = null;
		cur = racine ;
	}
	
	public void setVoisins(Node[] tab)
	{
		voisins = tab;
	}
	
	
	public void inscriptionParticipant(Participant p)
	{
		participants.add(p);
		fileOperations.add("IB : " + p + " à " + this);
		//TODO implements here
		//transmission aux voisins
	}
	
	//TODO implements here
}
