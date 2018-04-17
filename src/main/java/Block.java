public class Block {
	
	private static int profondeur = -1;
	private Node createur;
	private String ash;
	
	//TODO implements here 
	//private ??? operations;
	
	public Block(Node n , String s){
		profondeur++;
		createur = n;
		ash = s;
	}
}
