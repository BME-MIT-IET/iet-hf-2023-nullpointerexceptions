package whut.item;

import whut.player.Virologus;

//Egy balta tárgyért felel, a baltával virológusokat lehet ölni, de egy használat után kicsorbul, többet nem lehet használni.
public class Axe extends Item{
	private boolean used = false;
	
	//A paraméterként kapott virológust megöli (meghívja rajta a die() függvényt). Igazzal tér vissza.
	@Override
	public boolean killEffect(Virologus v) {
		if (used) return false;
		v.die();
		used = true;
		return true;
	}

	@Override
	public boolean check(String s) {
		return s.equals("axe");
	}
	
	public String toString() {
		return "axe";
	}
	
}
