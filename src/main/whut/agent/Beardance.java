package whut.agent;
import whut.player.AgentUsable;
import whut.material.Packet;
import whut.player.Virologist;

import java.util.List;

public class Beardance extends Agent {
	
	//A mez�n amin �ll a param�ter�l kapott virol�gus megfert�z minden virol�gust, majd a param�ter�l kapott virol�gust egy random szomsz�dos mez�re mozgatja. 
	//Ezut�n az �j mez�n is megfert�z mindenkit. 
	//V�g�l hamissal t�r vissza, mivel a birtokos virol�gus m�st nem csin�lhat a k�r�ben.
	@Override
	public boolean startTurnEffect(AgentUsable agentUsable) {
		infectAll(agentUsable.getField().getAgentUsableList(),agentUsable);
		
		if (agentUsable.getField().getNeighborhood().size() > 3) {
			agentUsable.move(agentUsable.getField().getNeighborhood().get(2));
		} 
		else{
			agentUsable.move(agentUsable.getField().getNeighborhood().get(0));
		}
		infectAll(agentUsable.getField().getAgentUsableList(),agentUsable);
		return false;
	}
	
	//A param�ter�l kapott csomagnak lek�ri a maxim�lis �rt�k�t, majd a maxim�lis �rt�k�b�l levonja ezt az �rt�ket, ezzel null�ra �ll�tva.
	@Override
	public void destroyEffect(Packet packet) {
		packet.handlePossibleLostMaterial(packet.getMaxMaterial());
	}
	
	//A param�ter�l kapott virol�guslist�b�l mindenkit megfert�z Beardance �genssel az al�bbi m�don: 
	//Csin�l egy Beardance �genst, amit odaad a param�ter�l kapott virol�gusnak. 
	//Ezut�n a lista elem virol�gust a visel� virol�gus nev�ben a csin�lt �genssel megt�madja.
	public void infectAll(List<AgentUsable> usableList, AgentUsable attacker) {
		for(int i = usableList.size()-1; i >= 0; i--) {
			if(!usableList.get(i).equals(attacker)) {
				Beardance b = new Beardance();
				attacker.addAgent(b);
				Virologist v = (Virologist)usableList.get(i);
				attacker.useAgent(v, b);
			}
		}
	}
	
	@Override
	public boolean check(String agentType) {
		return agentType.equals(toString());
	}
	
	public String toString() {
		return "BearDance";
	}

}
