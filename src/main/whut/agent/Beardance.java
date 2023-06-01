package whut.agent;
import whut.player.AgentUsable;
import whut.material.Packet;
import whut.player.Virologist;

import java.util.List;

public class Beardance extends Agent {
	
	//A mezõn amin áll a paraméterül kapott virológus megfertõz minden virológust, majd a paraméterül kapott virológust egy random szomszédos mezõre mozgatja. 
	//Ezután az új mezõn is megfertõz mindenkit. 
	//Végül hamissal tér vissza, mivel a birtokos virológus mást nem csinálhat a körében.
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
	
	//A paraméterül kapott csomagnak lekéri a maximális értékét, majd a maximális értékébõl levonja ezt az értéket, ezzel nullára állítva.
	@Override
	public void destroyEffect(Packet packet) {
		packet.handlePossibleLostMaterial(packet.getMaxMaterial());
	}
	
	//A paraméterül kapott virológuslistából mindenkit megfertõz Beardance ágenssel az alábbi módon: 
	//Csinál egy Beardance ágenst, amit odaad a paraméterül kapott virológusnak. 
	//Ezután a lista elem virológust a viselõ virológus nevében a csinált ágenssel megtámadja.
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
