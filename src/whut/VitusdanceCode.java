package whut;
//egy konkr�t genetikusk�d�rt felel
public class VitusdanceCode extends GeneticCode
{
	//megh�vja az �s konstruktor�t
	public VitusdanceCode() 
	{
		super();
	}
	
	//l�trehozza a megfelel� �genst, �s hozz�adja a param�terk�nt kapott �gens haszn�l�nak
	//AgensUsable au - ezen entity fogja megkapni a l�trehozott �genst
	@Override
	public void createAgens(AgensUsable au)
	{
		//ha ki tudta "fizetni" az �gens haszn�l� az �gens l�trehoz�s d�j�t, akkor kap egyet
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Vitusdance());
		else
			MyRunnable.log("Not enough aminosav,nukleotid to create vitusdance");
	}
	
	public boolean check(String s) {
		return s.equals("vitusdance");
	}
	
	public String toString() {
		return "vitusdancecode";
	}
}
