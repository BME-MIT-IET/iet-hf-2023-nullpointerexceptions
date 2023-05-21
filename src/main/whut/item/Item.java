package whut.item;

import whut.agent.Agent;
import whut.player.Virologist;

import java.io.Serializable;

public class Item implements Serializable{
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) a Itemek. 
	//Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: alapb�l nem v�d semmi, ez�rt a canCastEffect true)
	
	public boolean canCastEffect() {
		return true;
	}

	public void pickUpEffect(Virologist virologist) {
		//leszarmazott feluldefinialja ha kell
	}

	public void lostEffect(Virologist virologist) {
		//leszarmazott feluldefinialja ha kell
	}

	public boolean fireBackEffect(Virologist attacker, Virologist defender, Agent agent) {
		return false;
	}
	
	public boolean killEffect(Virologist virologist) {
		return false;
	}
	
	public boolean check(String itemType) {
		return false;
	}
}
