package whut.agent;

import whut.player.AgensUsable;
import whut.material.Packet;

public class Forget extends Agens{

	//Megh�vja a param�ter�l kapott virol�gus forgetAll() f�ggv�ny�t, amivel a virol�gus elfelejti az �ltala ismert �sszes genetikai k�dot. 
	//Ezut�n kit�rli a param�ter�l kapott virol�guson hat� �gensek k�z�l.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//k�r elej�n elfelejtteti minden megtanult k�dj�t
		au.forgetAll();
		//kiot�rli a gazd�ja list�j�b�l mag�t
		au.removeAgensOnMe(this);
		//visszat�r igazzal, mert tud m�g mozogni
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
		//abstract oszatlyt val�s�t meg ez�rt kell
	}
}
