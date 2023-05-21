package whut.item;

import whut.agent.Agent;
import whut.ui.control.MyRunnable;
import whut.player.Virologist;

public class Glove extends Item {
	private int timesUsed = 0;
	//r�keni az �gest a param�terk�nt kapott virol�gusra �gy, hogy az m�r ne tudja visszakenni
	//Virol�gus tamado - akire visszakeni az �genst
	//Agens a - az �gens, amit visszaken
	@Override
	public boolean fireBackEffect(Virologist attacker, Virologist defender, Agent agent) {
		if (attacker != null) {
			MyRunnable.log("v"+MyRunnable.getNumberOfVirologist(defender) + " attacked back with " + agent.toString());
			attacker.gotAttacked(agent, null);
		}
		timesUsed++;
		if (timesUsed >= 3) {
			defender.removeItem(this);
		}
		return true;
	}
	
	@Override
	public boolean check(String itemType) {
		return itemType.equals(toString());
	}
	
	public String toString() {
		return "Glove";
	}
}
