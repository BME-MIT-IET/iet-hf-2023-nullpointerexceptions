package whut.item;

import whut.agent.Agens;
import whut.player.Virologus;

import java.io.Serializable;

public class Item implements Serializable{
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) a Itemek. 
	//Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: alapb�l nem v�d semmi, ez�rt a canCastEffect true)
	
	public boolean canCastEffect() {
		return true;
	}

	public void pickUpEffect(Virologus v) {
		//leszarmazott feluldefinialja ha kell
	}

	public void lostEffect(Virologus v) {
		//leszarmazott feluldefinialja ha kell
	}

	public boolean fireBackEffect(Virologus tamado, Virologus hasznalo, Agens a) {
		return false;
	}
	
	public boolean killEffect(Virologus v) {
		return false;
	}
	
	public boolean check(String s) {
		return false;
	}
}
