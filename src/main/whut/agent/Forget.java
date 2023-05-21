package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

public class Forget extends Agent {

	//Meghívja a paraméterül kapott virológus forgetAll() függvényét, amivel a virológus elfelejti az általa ismert összes genetikai kódot. 
	//Ezután kitörli a paraméterül kapott virológuson ható ágensek közül.
	@Override
	public boolean startTurnEffect(AgentUsable agentUsable) {
		//kï¿½r elejï¿½n elfelejtteti minden megtanult kï¿½djï¿½t
		agentUsable.forgetAllGeneticCodes();
		//kiotï¿½rli a gazdï¿½ja listï¿½jï¿½bï¿½l magï¿½t
		agentUsable.removeAppliedAgent(this);
		//visszatï¿½r igazzal, mert tud mï¿½g mozogni
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
		//abstract oszatlyt valósít meg ezért kell
	}
}
