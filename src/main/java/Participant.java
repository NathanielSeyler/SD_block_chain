public class Participant {
	//on peut peut etre ajouter un identifiant a voir si c'est utile
	private int valeur ;
	
	public Participant(){
		valeur = 0;
	}
	
	public int getValeur()
	{
		return valeur;
	}
	
	public void debit(int v)
	{
		valeur += v;
	}
	
	public void credit(int v)
	{
		valeur -= v;
	}
}
