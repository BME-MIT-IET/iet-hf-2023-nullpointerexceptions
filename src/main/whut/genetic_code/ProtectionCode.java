package whut.genetic_code;

import whut.agent.Protection;
import whut.player.AgentUsable;
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
	public void createAgent(AgentUsable agentUsable)
	{
		if(agentUsable.getPacket().decreaseMaterial(this.cost))
			agentUsable.addAgent(new Protection());
		else
			MyRunnable.log("Not enough AminoAcid, Nucleotide to create ProtectionCode");
	}
	
	@Override
	public boolean check(String geneticCodeType) {
		return geneticCodeType.equals(toString());
	}
	
	@Override
	public String toString() {
		return "ProtectionCode";
	}
}
