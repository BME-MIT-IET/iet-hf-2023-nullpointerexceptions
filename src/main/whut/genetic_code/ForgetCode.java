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
	public void createAgent(AgentUsable agentUsable)
	{
		//ha ki tudta "fizetni" az agens hasznal�o az agens letrehozas dijat, akkor kap egyet
		if(agentUsable.getPacket().decreaseMaterial(cost))
			agentUsable.addAgent(new Forget());
		else
			MyRunnable.log("Not enough AminoAcid, Nucleotide to create ForgetCode");
	}
	
	@Override
	public boolean check(String geneticCodeType) {
		return geneticCodeType.equals(toString());
	}
	
	@Override
	public String toString() {
		return "ForgetCode";
	}
	
}
