package whut.item;

import whut.material.Packet;
import whut.player.Virologist;

public class Sack extends Item{
	private int increaseAmount = 200;
	
	//megn�veli a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	@Override
	public void pickUpEffect(Virologist virologist) {
		Packet packet = virologist.getPacket();
		packet.changeMaxMaterial(increaseAmount);
	}
	
	//cs�kkenti a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	@Override
	public void lostEffect(Virologist virologist) {
		Packet packet = virologist.getPacket();
		packet.handlePossibleLostMaterial(increaseAmount);
	}
	
	@Override
	public boolean check(String itemType) {
		return itemType.equals(toString());
	}
	
	public String toString() {
		return "Sack";
	}
}
