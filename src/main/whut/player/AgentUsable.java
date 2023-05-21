package whut.player;
import whut.genetic_code.*;
import whut.material.Material;
import whut.material.Packet;
import whut.ui.control.MyRunnable;
import whut.agent.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentUsable extends Entity{
	
	protected ArrayList<Agent> agents = new ArrayList<>();
	protected ArrayList<Agent> appliedAgents = new ArrayList<>();
	protected ArrayList<GeneticCode> geneticCodes = new ArrayList<>();
	protected Packet materialPacket = new Packet();
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgent(Agent agent) {
		agents.add(agent);
	}
	public void applyAgent(Agent agent) {
		appliedAgents.add(agent);
	}
	public void removeAgent(Agent agent) {
		agents.remove(agent);
	}
	public void removeAppliedAgent(Agent agent) {
		appliedAgents.remove(agent);
	}
	
	public Packet getPacket() {
		return materialPacket;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean decreaseRound() {
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		for (Agent appliedAgent : appliedAgents) {
			if (!appliedAgent.startTurnEffect(this)) {
				MyRunnable.getGame().notifyObservers();
				return false;
			}
		}
		return true;
	}
	
	//megt�madjuk ezt az agensusable-t
	public void gotAttacked(Agent agent, Virologist virologist) {
		//k�ld�t�lk kit�rli az �genst
		virologist.removeAgent(agent);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		for(Agent a: appliedAgents){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		if(!isProtected) {
			applyAgent(agent);
		}
		
		
	}
	
	//megtanul egy genetikk�dot
	public void learnGeneticCode(GeneticCode geneticCode) {
		for (GeneticCode gc : geneticCodes){
			if (gc.check(geneticCode.toString().substring(0, geneticCode.toString().length()-4)))
				return;
		}
		geneticCodes.add(geneticCode);
	}
	
	//megk�rdezi a felhaszn�l�t melyik genetik k�dot szeretn� �genss� alak�tani �s azt megcsin�lja
	public void createAgent(String agentType) {
		boolean created = false;
		int i = 0;
		while(!created && i < geneticCodes.size()) {
			if(geneticCodes.get(i).check(agentType)) {
				geneticCodes.get(i).createAgent(this);
				created = true;
				geneticCodes.remove(i);
				switch(agentType) {
				case "Protection":
					geneticCodes.add(new ProtectionCode());
					break;
				case "Chorea":
					geneticCodes.add(new ChoreaCode());
					break;
				case "Stun":
					geneticCodes.add(new StunCode());
					break;
				case "Forget":
					geneticCodes.add(new ForgetCode());
					break;
				default:
					break;
				}
			}
			i++;
		}
		
		if(!created)
			MyRunnable.log("Genetic code for " + agentType + " not learned yet!");
	}
	//elfelejt minden genetikk�dot
	public void forgetAllGeneticCodes() {
		geneticCodes.clear();
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet packet, Material material) {
		packet.handleMaterialSeparate(material, this.materialPacket);
	}
	
	//ennek kene egy parameter, hogy melyik agenst hasznalja
	public void useAgent(Virologist virologist, Agent agent) {
		if (!MyRunnable.getGame().isRunning()) return;
		MyRunnable.getGame().applyBearEffectToAll();
		agents.remove(agent);
		virologist.gotAttacked(agent, (Virologist)this);
		MyRunnable.getGame().applyBearEffectToAll();
	}
	
	public void destroyMaterial(Packet packet) {
		for(Agent a : appliedAgents) {
			a.destroyEffect(packet);
		}
	}

	
	public List<GeneticCode> getGeneticCodes(){
		return geneticCodes;
	}

	public Agent getAgent(String agentType) {
		for(Agent ag : agents) {
			if(ag.check(agentType))
				return ag;
		}
		return null;
	}

	public List<Agent> getAgents(){
		return agents;
	}
	
	public List<Agent> getAppliedAgents(){
		return appliedAgents;
	}
}
