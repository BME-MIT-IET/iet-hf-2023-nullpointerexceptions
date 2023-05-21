package whut.agent;
import whut.player.AgensUsable;
import whut.field.Field;
import whut.material.Packet;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


public class Vitusdance extends Agens{
	
	private Random rand = new SecureRandom();  
	//A k�r elej�n h�v�dik meg, a param�ter�l kapott virol�gust 3-szor egy random szomsz�dos mez�re mozgatja, majd kit�rli mag�t. 
	//Mindig igazzal t�r vissza, mert a hat�s ut�n m�g mozoghat.
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
		//abstracct oszt�lyt val�s�t meg ez�rt kell
	}
	
}
