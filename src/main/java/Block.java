import java.util.LinkedList;

public class Block {
	
	private static int profondeur = -1;
	private Node createur;
	private String ash;
	private Block next;
	private int capacity;
	
	//TODO implements here 
	private LinkedList<String> operations;
	
	public Block(Node n){
		profondeur++;
		createur = n;
		ash = "Ash is OP";
		next = null;
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
	
	public void setOperations(LinkedList<String> op)
	{
		operations = op;
	}
	
	public Block getNext()
	{
		return next;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public String toString()
	{
		return Integer.toString(profondeur) + createur.toString() + ash + Integer.toString(capacity);
	}
	
	public LinkedList<String> getOperations()
	{
		return operations;
	}
}
