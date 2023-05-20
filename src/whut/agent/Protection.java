package agent;

import whut.player.AgensUsable;
import whut.material.Packet;

public class Protection extends Agens{
	
	private int effectTime;
	
	//beï¿½llï¿½tja a kezdeti hatï¿½sidï¿½t
	public Protection() {
		effectTime = 3;
	}
	
	//Elõször is az élettartalmát csökkenti, ha pedig ez az érték eléri a 0-t akkor a paraméterül kapott virológustól kitörli. 
	//Mindenképp igazzal tér vissza, jelezve hogy még az adott körben mozoghat.
	public boolean startTurnEffect(AgensUsable au) {
		//csï¿½kkenti a hatï¿½sidï¿½t, ï¿½s ha lejï¿½rt, akkor kitï¿½rli a virolï¿½gustï¿½l
		if(effectTime < 0) {
			au.removeAgensOnMe(this);
		}
		
		//mï¿½g tud mozogni ezert igazzal ter vissza
		return true;
	}
	
	//Mindenképp igazzal tér vissza, jelezve, hogy védve van.
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
		//abstracct osztályt valósít meg ezért kell
	}
	
}
