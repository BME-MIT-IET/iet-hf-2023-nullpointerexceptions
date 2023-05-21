package whut.player;
import whut.genetic_code.*;
import whut.material.Material;
import whut.material.Packet;
import whut.ui.control.MyRunnable;
import whut.agent.Agent;

import java.util.ArrayList;
import java.util.List;

public class AgentUsable extends Entity{
	
	protected ArrayList<Agent> agens = new ArrayList<>();
	protected ArrayList<Agent> agentOnMe = new ArrayList<>();
	protected ArrayList<GeneticCode> geneticCode = new ArrayList<>();
	protected Packet materialPacket = new Packet();
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgens(Agent a) {
		agens.add(a);
	}
	public void addAgensOnMe(Agent ag) {
		agentOnMe.add(ag);
	}
	public void removeAgens(Agent ag) {
		agens.remove(ag);
	}
	public void removeAgensOnMe(Agent ag) {
		agentOnMe.remove(ag);
	}
	
	public Packet getPacket() {
		return materialPacket;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean roundDesc() {
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		for(int i = 0; i < agentOnMe.size(); i++){
			if(!agentOnMe.get(i).startTurnEffect(this)) {
				MyRunnable.getGame().myNotify();
				 return false;
			}
		}
		return true;
	}
	
	//megt�madjuk ezt az agensusable-t
	public void uRAttacked(Agent ag, Virologist v) {
		//k�ld�t�lk kit�rli az �genst
		v.removeAgens(ag);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		for(Agent a: agentOnMe){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		if(!isProtected) {
			addAgensOnMe(ag);
		}
		
		
	}
	
	//megtanul egy genetikk�dot
	public void learnGeneticCode(GeneticCode gc) {
		for (GeneticCode gc2 : geneticCode){
			if (gc2.check(gc.toString().substring(0, gc.toString().length()-4)))
				return;
		}
		geneticCode.add(gc);
	}
	
	//megk�rdezi a felhaszn�l�t melyik genetik k�dot szeretn� �genss� alak�tani �s azt megcsin�lja
	public void createAgens(String mit) {
		boolean created = false;
		int i = 0;
		while(!created && i < geneticCode.size()) {
			if(geneticCode.get(i).check(mit)) {
				geneticCode.get(i).createAgens(this);
				created = true;
				geneticCode.remove(i);
				switch(mit) {
				case "protection":
					geneticCode.add(new ProtectionCode());
					break;
				case "vitusdance":
					geneticCode.add(new ChoreaCode());
					break;
				case "stun":
					geneticCode.add(new StunCode());
					break;
				case "forget":
					geneticCode.add(new ForgetCode());
					break;
				default:
					break;
				}
			}
			i++;
		}
		
		if(!created)
			MyRunnable.log("Genetic code for " + mit + " not learned yet!");
	}
	//elfelejt minden genetikk�dot
	public void forgetAll() {
		geneticCode.clear();
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet p, Material m) {
		p.handleMaterialSeparate(m, this.materialPacket);
	}
	
	//ennek kene egy parameter, hogy melyik agenst hasznalja
	public void useAgens(Virologist v, Agent ag) {
		if (!MyRunnable.getGame().getMegy()) return;
		MyRunnable.getGame().bearAll();
		agens.remove(ag);
		v.uRAttacked(ag, (Virologist)this);
		MyRunnable.getGame().bearAll();
	}
	
	public void destroyMaterial(Packet p) {
		for(Agent a : agentOnMe) {
			a.destroyEffect(p);
		}
	}

	
	public List<GeneticCode> getGeneticCodeHave(){
		return geneticCode;
	}

	public Agent getAgens(String s) {
		for(Agent ag : agens) {
			if(ag.check(s))
				return ag;
		}
		return null;
	}

	public List<Agent> getAgensHave(){
		return agens;
	}
	
	public List<Agent> getAgensOnMe(){
		return agentOnMe;
	}
}
