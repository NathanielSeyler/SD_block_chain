public class Participant {
	//on peut peut etre ajouter un identifiant a voir si c'est utile
	private float valeur ;
	
	public Participant(){
		valeur = 0;
	}
	
	public float getValeur()
	{
		//TODO implements here
		return valeur;
	}
	
	public void debit(float v)
	{
		valeur += v;
	}
	
	public void credit(float v)
	{
		valeur -= v;
	}
}
