package whut.item;

import whut.material.Packet;
import whut.player.Virologist;

public class Sack extends Item{
	private int increase = 200;
	
	//megn�veli a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	@Override
	public void pickUpEffect(Virologist v) {
		Packet p = v.getPacket();
		p.changeMaxMaterial(increase);
	}
	
	//cs�kkenti a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	@Override
	public void lostEffect(Virologist v) {
		Packet p = v.getPacket();
		p.handlePossibleLostMaterial(increase);
	}
	
	@Override
	public boolean check(String it) {
		return it.equals("sack");
	}
	
	public String toString() {
		return "sack";
	}
}
