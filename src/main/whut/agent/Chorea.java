package whut.agent;
import whut.player.AgentUsable;
import whut.field.Field;
import whut.material.Packet;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


public class Chorea extends Agent {
	
	private Random rand = new SecureRandom();  
	//A k�r elej�n h�v�dik meg, a param�ter�l kapott virol�gust 3-szor egy random szomsz�dos mez�re mozgatja, majd kit�rli mag�t. 
	//Mindig igazzal t�r vissza, mert a hat�s ut�n m�g mozoghat.
	@Override
	public boolean startTurnEffect(AgentUsable agentUsable) {
		//kor elejen haromszor random lepteti
		Field currentField;
		List<Field> allNeighbors;
		int randomNeighborIndex;
		for(int i=0;i<3;i++) {
			currentField = agentUsable.getField();
			allNeighbors = currentField.getNeighborhood();
			randomNeighborIndex = rand.nextInt(allNeighbors.size());
			agentUsable.move(allNeighbors.get(randomNeighborIndex));
		}
		//kitorli a virologuson levo agensek kozul
		agentUsable.removeAppliedAgent(this);
		//visszater igazzal, mert meg tud utana lopni
		return true;
	}
	
	@Override
	public boolean check(String agentType) {
		return agentType.equals(toString());
	}
	
	public String toString() {
		return "Chorea";
	}

	@Override
	public void destroyEffect(Packet packet) {
		//abstracct oszt�lyt val�s�t meg ez�rt kell
	}
}
