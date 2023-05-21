package whut.agent;

import whut.player.AgensUsable;
import whut.material.Packet;

public class Forget extends Agens{

	//Meghívja a paraméterül kapott virológus forgetAll() függvényét, amivel a virológus elfelejti az általa ismert összes genetikai kódot. 
	//Ezután kitörli a paraméterül kapott virológuson ható ágensek közül.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//kï¿½r elejï¿½n elfelejtteti minden megtanult kï¿½djï¿½t
		au.forgetAll();
		//kiotï¿½rli a gazdï¿½ja listï¿½jï¿½bï¿½l magï¿½t
		au.removeAgensOnMe(this);
		//visszatï¿½r igazzal, mert tud mï¿½g mozogni
		return true;
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("forget");
	}
	
	public String toString() {
		return "forget";
	}

	@Override
	public void destroyEffect(Packet p) {
		//abstract oszatlyt valósít meg ezért kell
	}
}
