package whut;
import java.util.ArrayList;
import java.util.Random;

public class Vitusdance extends Agens{
	
	
	//A kör elején hívódik meg, a paraméterül kapott virológust 3-szor egy random szomszédos mezõre mozgatja, majd kitörli magát. 
	//Mindig igazzal tér vissza, mert a hatás után még mozoghat.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//kï¿½r elejï¿½n hï¿½romszor random lï¿½pteti
		Field all;
		ArrayList<Field> osszesSzomszed = new ArrayList<Field>();
		int randomSzomsz;
		Random rand = new Random();
		for(int i=0;i<3;i++) {
			all = au.getField();
			osszesSzomszed = all.getNeighbourhood();
			randomSzomsz = rand.nextInt(osszesSzomszed.size());
			au.move(osszesSzomszed.get(randomSzomsz));
		}
		//kitï¿½rli a virolï¿½guson lï¿½vï¿½ ï¿½gensek kï¿½zï¿½l
		au.removeAgensOnMe(this);
		//visszatï¿½r igazzal, mert mï¿½g tud utï¿½na lï¿½pni
		return true;
	}
	
	public boolean Check(String s) {
		if(s.equals("vitusdance"))
			return true;
		return false;
	}
	
	public String toString() {
		return "vitusdance";
	}
	
}
