package whut.item;

import java.util.Random;

public class Cloak extends Item{
	private Random random = new Random();
	//visszaadja, hogy siker�lt-e kiv�deni az �gensken�st
	@Override
	public boolean canCastEffect() {
		int n = random.nextInt(1000);
		return n <= 823;
	}
	
	@Override
	public boolean check(String it) {
		return it.equals("cloak");
	}
	
	public String toString() {
		return "cloak";
	}
}
