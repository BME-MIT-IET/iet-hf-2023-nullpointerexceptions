package whut.agent;

import whut.player.AgensUsable;
import whut.material.Packet;

public class Stun extends Agens{

	private int effectTime;
	
	//beï¿½llï¿½tja a kezdï¿½ hatï¿½sï¿½rtï¿½ket
	public Stun() {
		effectTime = 3;
	}
	
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//csï¿½kkenti a hatï¿½sidï¿½t, ï¿½s ha lejï¿½rt, akkor kitï¿½rli a virolï¿½gustï¿½l
		if(--effectTime < 0) {
			au.removeAgensOnMe(this);
			//ha lejï¿½rt akkor mï¿½r tud mozogni
			return true;
		}
		
		//ha nem jï¿½rt le mï¿½g akkor nem tud mozogni
		return false;
	}
	
	//stunnolt, tehï¿½t lehet lopni tï¿½le
	@Override
	public boolean canStealEffect() {
		return true;
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("stun");
	}
	
	public String toString() {
		return "stun";
	}

	@Override
	public void destroyEffect(Packet p) {
		//abstracct osztályt valósít meg ezért kell
	}
}
