package whut.item;

import whut.player.Virologist;

//Egy balta t�rgy�rt felel, a balt�val virol�gusokat lehet �lni, de egy haszn�lat ut�n kicsorbul, t�bbet nem lehet haszn�lni.
public class Axe extends Item{
	private boolean used = false;
	
	//A param�terk�nt kapott virol�gust meg�li (megh�vja rajta a die() f�ggv�nyt). Igazzal t�r vissza.
	@Override
	public boolean killEffect(Virologist v) {
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
