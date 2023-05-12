package whut;

import java.util.Random;

public class Cloak extends Item{
	//visszaadja, hogy siker�lt-e kiv�deni az �gensken�st
	public boolean canCastEffect() {
		Random random = new Random();
		int n = random.nextInt(1000);
		if (n <= 823) return false;
		return true;
	}
	
	public boolean Check(String it) {
		if(it.equals("cloak"))
			return true;
		return false;
	}
	
	public String toString() {
		return "cloak";
	}
}
