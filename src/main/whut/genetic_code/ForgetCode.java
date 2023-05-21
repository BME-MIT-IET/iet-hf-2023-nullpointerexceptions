package whut.genetic_code;

import whut.agent.Forget;
import whut.player.AgentUsable;
import whut.ui.control.MyRunnable;

//egy konkret genetikuskodert felel
public class ForgetCode extends GeneticCode
{
	//meghivja az os konstruktorat
	public ForgetCode() 
	{
		super();
	}

	//letrehozza a megfelelo agenst, es hozzaadja a parameterkent kapott agens hasznalonak
	//AgensUsable au - ezen entity fogja megkapni a letrehozott agenst
	@Override
	public void createAgens(AgentUsable au)
	{
		//ha ki tudta "fizetni" az agens hasznal�o az agens letrehozas dijat, akkor kap egyet
		if(au.getPacket().decreaseMaterial(cost))
			au.addAgens(new Forget());
		else
			MyRunnable.log("Not enough aminosav,nukleotid to create forget");
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("forget");
	}
	
	@Override
	public String toString() {
		return "forgetcode";
	}
	
}
