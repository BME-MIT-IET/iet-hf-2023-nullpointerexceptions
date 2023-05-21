package whut.genetic_code;

import whut.agent.Protection;
import whut.player.AgensUsable;
import whut.ui.control.MyRunnable;

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
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Protection());
		else
			MyRunnable.log("Not enough aminosav,nukleotid to create protection");
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("protection");
	}
	
	@Override
	public String toString() {
		return "protectioncode";
	}
}
