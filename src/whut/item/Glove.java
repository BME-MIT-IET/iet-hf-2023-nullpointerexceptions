package whut.item;

import whut.ui.control.MyRunnable;
import whut.agent.Agens;
import whut.player.Virologus;

public class Glove extends Item {
	private int usedTime = 0;
	//r�keni az �gest a param�terk�nt kapott virol�gusra �gy, hogy az m�r ne tudja visszakenni
	//Virol�gus tamado - akire visszakeni az �genst
	//Agens a - az �gens, amit visszaken
	@Override
	public boolean fireBackEffect(Virologus tamado, Virologus hasznalo, Agens a) {
		if (tamado != null) {
			MyRunnable.log("v"+MyRunnable.getVirologusSzam(hasznalo) + " attacked back with " + a.toString());
			tamado.uRAttacked(a, null);
		}
		usedTime++;
		if (usedTime >= 3) {
			hasznalo.removeItem(this);
		}
		return true;
	}
	
	@Override
	public boolean check(String it) {
		return it.equals("glove");
	}
	
	public String toString() {
		return "glove";
	}
}
