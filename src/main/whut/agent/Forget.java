package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

public class Forget extends Agent {

	//Megh�vja a param�ter�l kapott virol�gus forgetAll() f�ggv�ny�t, amivel a virol�gus elfelejti az �ltala ismert �sszes genetikai k�dot. 
	//Ezut�n kit�rli a param�ter�l kapott virol�guson hat� �gensek k�z�l.
	@Override
	public boolean startTurnEffect(AgentUsable agentUsable) {
		//k�r elej�n elfelejtteti minden megtanult k�dj�t
		agentUsable.forgetAllGeneticCodes();
		//kiot�rli a gazd�ja list�j�b�l mag�t
		agentUsable.removeAppliedAgent(this);
		//visszat�r igazzal, mert tud m�g mozogni
		return true;
	}
	
	@Override
	public boolean check(String agentType) {
		return agentType.equals(toString());
	}
	
	public String toString() {
		return "Forget";
	}

	@Override
	public void destroyEffect(Packet packet) {
		//abstract oszatlyt val�s�t meg ez�rt kell
	}
}
