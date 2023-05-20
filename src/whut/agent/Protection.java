package agent;

import whut.player.AgensUsable;
import whut.material.Packet;

public class Protection extends Agens{
	
	private int effectTime;
	
	//be�ll�tja a kezdeti hat�sid�t
	public Protection() {
		effectTime = 3;
	}
	
	//El�sz�r is az �lettartalm�t cs�kkenti, ha pedig ez az �rt�k el�ri a 0-t akkor a param�ter�l kapott virol�gust�l kit�rli. 
	//Mindenk�pp igazzal t�r vissza, jelezve hogy m�g az adott k�rben mozoghat.
	public boolean startTurnEffect(AgensUsable au) {
		//cs�kkenti a hat�sid�t, �s ha lej�rt, akkor kit�rli a virol�gust�l
		if(effectTime < 0) {
			au.removeAgensOnMe(this);
		}
		
		//m�g tud mozogni ezert igazzal ter vissza
		return true;
	}
	
	//Mindenk�pp igazzal t�r vissza, jelezve, hogy v�dve van.
	@Override
	public boolean defendEffect() {
		return true;
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("protection");
	}
	
	public String toString() {
		return "protection";
	}

	@Override
	public void destroyEffect(Packet p) {
		//abstracct oszt�lyt val�s�t meg ez�rt kell
	}
	
}
