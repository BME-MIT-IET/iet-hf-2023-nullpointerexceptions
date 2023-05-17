package whut;

import java.util.ArrayList;

//egy konkr�t genetikusk�d�rt felel
public class ProtectionCode extends GeneticCode
{
	//megh�vja az �s konstruktor�t
	public ProtectionCode() 
	{
		super();
	}
	
	//l�trehozza a megfelel� �genst, �s hozz�adja a param�terk�nt kapott �gens haszn�l�nak
	//AgensUsable au - ezen entity fogja megkapni a l�trehozott �genst
	@Override
	public void createAgens(AgensUsable au)
	{
		//ha ki tudta "fizetni" az �gens haszn�l� az �gens l�trehoz�s d�j�t, akkor kap egyet
		//ArrayList<Material> matlist=new ArrayList<Material>();
		//matlist = (ArrayList<Material>)cost.clone();
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Protection());
		else
			MyRunnable.log("Not enough aminosav,nukleotid to create protection");
	}
	
	@Override
	public boolean check(String s) {
		if(s.equals("protection"))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "protectioncode";
	}
}
