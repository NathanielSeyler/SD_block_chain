import java.util.LinkedList;

public class Block {
	
	private static int profondeur = -1;
	private Node createur;
	private String ash;
	private Block next;
	private int capacity;
	
	//TODO implements here 
	private LinkedList<String> operations;
	
	public Block(Node n , LinkedList<String> op){
		profondeur++;
		createur = n;
		ash = "Ash is OP";
		next = null;
		operations = op;
	}
	
	public void setNext(Block b)
	{
		next = b;
	}
	
	public int getProfondeur()
	{
		return profondeur;
	}
	
	public void setHash(String hash)
	{
		ash = hash;
	}
	
	public Block getNext()
	{
		return next;
	}
}
