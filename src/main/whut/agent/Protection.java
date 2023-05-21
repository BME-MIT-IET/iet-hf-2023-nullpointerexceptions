package whut.agent;

import whut.player.AgentUsable;
import whut.material.Packet;

public class Protection extends Agent {
	
	private int effectTime;
	
	//be�ll�tja a kezdeti hat�sid�t
	public Protection() {
		effectTime = 3;
	}
	
	//El�sz�r is az �lettartalm�t cs�kkenti, ha pedig ez az �rt�k el�ri a 0-t akkor a param�ter�l kapott virol�gust�l kit�rli. 
	//Mindenk�pp igazzal t�r vissza, jelezve hogy m�g az adott k�rben mozoghat.
	public boolean startTurnEffect(AgentUsable agentUsable) {
		//cs�kkenti a hat�sid�t, �s ha lej�rt, akkor kit�rli a virol�gust�l
		if(effectTime < 0) {
			agentUsable.removeAppliedAgent(this);
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
	public boolean check(String agentType) {
		return agentType.equals(toString());
	}
	
	public String toString() {
		return "Protection";
	}

	@Override
	public void destroyEffect(Packet packet) {
		//abstracct oszt�lyt val�s�t meg ez�rt kell
	}
	
}
