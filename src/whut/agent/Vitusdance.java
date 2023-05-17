package whut.agent;
import whut.player.AgensUsable;
import whut.field.Field;
import whut.material.Packet;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


public class Vitusdance extends Agens{
	
	private Random rand = new SecureRandom();  
	//A kör elején hívódik meg, a paraméterül kapott virológust 3-szor egy random szomszédos mezõre mozgatja, majd kitörli magát. 
	//Mindig igazzal tér vissza, mert a hatás után még mozoghat.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//kor elejen haromszor random lepteti
		Field all;
		List<Field> osszesSzomszed;
		int randomSzomsz;
		for(int i=0;i<3;i++) {
			all = au.getField();
			osszesSzomszed = all.getNeighbourhood();
			randomSzomsz = rand.nextInt(osszesSzomszed.size());
			au.move(osszesSzomszed.get(randomSzomsz));
		}
		//kitorli a virologuson levo agensek kozul
		au.removeAgensOnMe(this);
		//visszater igazzal, mert meg tud utana lopni
		return true;
	}
	
	@Override
	public boolean check(String s) {
		return s.equals("vitusdance");
	}
	
	public String toString() {
		return "vitusdance";
	}

	@Override
	public void destroyEffect(Packet p) {
		//abstracct osztályt valósít meg ezért kell
	}
	
}
