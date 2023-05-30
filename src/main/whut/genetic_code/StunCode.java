package whut.genetic_code;

import whut.agent.Stun;
import whut.player.AgentUsable;
import whut.ui.control.MyRunnable;

//egy konkr�t genetikusk�d�rt felel
public class StunCode extends GeneticCode
{
	//megh�vja az �s konstruktor�t
	public StunCode() 
	{
		super();
	}
	
	//l�trehozza a megfelel� �genst, �s hozz�adja a param�terk�nt kapott �gens haszn�l�nak
	//AgensUsable au - ezen entity fogja megkapni a l�trehozott �genst
	@Override
	public void createAgent(AgentUsable agentUsable)
	{
		//ha ki tudta "fizetni" az �gens haszn�l� az �gens l�trehoz�s d�j�t, akkor kap egyet
		if(agentUsable.getPacket().decreaseMaterial(this.cost))
			agentUsable.addAgent(new Stun());
		else
			MyRunnable.log("Not enough AminoAcid, Nucleotide to create StunCode");
		
	}
	
	public boolean check(String geneticCodeType) {
		return geneticCodeType.equals(toString());
	}
	
	public String toString() {
		return "Stun";
	}
}
