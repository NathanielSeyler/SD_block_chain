public class Block {
	
	private static int profondeur = -1;
	private Node createur;
	private String ash;
	private Block next;
	
	//TODO implements here 
	private String operations;
	
	public Block(Node n , String s){
		profondeur++;
		createur = n;
		ash = s;
		next = null;
	}
	
	public void setNext(Block b)
	{
		next = b;
	}
}
