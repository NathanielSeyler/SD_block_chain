import java.util.LinkedList;

public class Block {
	
	private static int profondeur = -1;
	private Node createur;
	private String ash;
	private Block next;
	
	//TODO implements here 
	private LinkedList<String> operations;
	
	public Block(Node n , String hash , LinkedList<String> op){
		profondeur++;
		createur = n;
		ash = hash;
		next = null;
		operations = op;
	}
	
	public void setNext(Block b)
	{
		next = b;
	}
}
