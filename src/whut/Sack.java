package whut;

public class Sack extends Item{
	private int increase = 200;
	
	//megn�veli a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	public void pickUpEffect(Virologus v) {
		Packet p = v.getPacket();
		p.changeMaxMaterial(increase);
	}
	
	//cs�kkenti a virol�gus anyag t�rol�kapacit�s�t
	//Virologus v - a virol�gus, akin kifejti hat�s�t
	public void lostEffect(Virologus v) {
		Packet p = v.getPacket();
		p.handlePossibleLostMaterial(increase);
	}
	
	public boolean Check(String it) {
		if(it.equals("sack"))
			return true;
		return false;
	}
	
	public String toString() {
		return "sack";
	}
}
