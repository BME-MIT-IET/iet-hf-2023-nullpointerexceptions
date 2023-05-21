package whut.player;
import whut.item.Item;
import whut.material.Material;
import whut.material.Packet;
import whut.ui.control.MyRunnable;
import whut.ui.observer.VirologistObserver;
import whut.agent.Agent;
import whut.field.Field;

import java.util.ArrayList;
import java.util.List;

public class Virologist extends AgentUsable {
	private ArrayList<Item> items = new ArrayList<>();
	private String bearDance = "BearDance";

	public Virologist() {
		VirologistObserver observer = new VirologistObserver(this);
		this.attach(observer);
	}
	
	public int getItemCount() {
		return items.size();
	}
	
	
	//megkerdezi a felhasznalot, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealItem(Virologist virologist, Item itemToSteal) {
		virologist.stealItemAttempt(this,itemToSteal);
		
	}
	
	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealMaterial(Virologist virologist, Material materialToSteal) {
		virologist.stealMaterialAttempt(this,materialToSteal);
		
	}
	
	//megk�rdezi a felhaszn�l�t�l, hogy melyik t�rgyat akarja cse�lni, �s azt adja vissza
	public Item getItem(String getThis) {
		for(Item i : items) {
			if(i.check(getThis)) {
				return i;
			}
		}
		return null;
	}

	
	//ellen�rzi, hogy lehet-e t�le t�rgyat lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
	public void stealItemAttempt(Virologist virologist, Item itemToSteal) {
		boolean canSteal = false;
		for(Agent a : appliedAgents) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			if (virologist.getItemCount() == 3)
				removeItem(itemToSteal);
			else {
				removeItem(itemToSteal);
				virologist.addItem(itemToSteal);
				MyRunnable.log(itemToSteal.toString()+" was stolen");
			}
		} else
			MyRunnable.log("The item was not stolen");
	}
	
	//ellen�rzi, hogy lehet-e t�le anyagot lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
	public void stealMaterialAttempt(Virologist virologist, Material materialToSteal) {
		boolean canSteal = false;
		for(Agent a : appliedAgents) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			Packet p = virologist.getPacket();
			materialPacket.handleMaterialSeparate(materialToSteal, p);
			ArrayList<Material> temp = new ArrayList<>();
			temp.add(materialToSteal);
			getPacket().decreaseMaterial(temp);
			MyRunnable.log(materialToSteal.toString()+"was stolen");
		} else
			MyRunnable.log("This material was not stolen");
	}
	
	//lecser�l egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit lecser�l
	//Item mire - a t�rgy, amire cser�li
	public void changeItem(Item itemToRemove, Item itemToCollect) {
		this.removeItem(itemToRemove);
		this.addItem(itemToCollect);
		
	}
	
	//elt�vol�t egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit elveszt
	public void removeItem(Item itemToRemove) {
		items.remove(itemToRemove);
		itemToRemove.lostEffect(this);
	}
	
	//hozz�ad egy t�rgyat a n�la l�v� t�rgyakhoz
	//Item mit - a t�rgy, amit megkap
	public void addItem(Item itemToAdd) {
		items.add(itemToAdd);
		itemToAdd.pickUpEffect(this);
	}
	
	//felvesz egy t�rgyat, ha nincs helye, akkor kicser�li az egyiket
	//ArrayList<Item> is - a t�rgyak list�ja, amib�l kiv�lasztja, hogy melyiket szeretn�
	public void pickUpItem(Item itemToCollect) {
		if (items.size() >= 3) {
			Item itemToRemove = items.get(0);
			changeItem(itemToRemove, itemToCollect);
			field.addItem(itemToRemove);
		} else {
			addItem(itemToCollect);
		}
		field.removeItem(itemToCollect);
	}
	
	//egy t�rgyat otthagy ahol van
	public void leaveItem(Item itemToLeave) {
		field.addItem(itemToLeave);
		removeItem(itemToLeave);
	}
	
	
	//megt�madj�k az adott virol�gust
	@Override
	public void gotAttacked(Agent agent, Virologist virologist) {
		if (virologist == this) {
			applyAgent(agent);
		} else {
			canAddAgensToMe(agent, virologist);
		}
	}
	
	private void canAddAgensToMe(Agent agent, Virologist virologist) {
		//ellenorzi, hogy van-e vedve valami altal
		for(Agent a : appliedAgents)
			if(a.defendEffect())
				return;
		
		//mivel virologus, ezert vegigmegy az itemein is, hogy azok valamelyike megvedi-e
		for(Item it : items)
			if(!it.canCastEffect())
				return;
		
		//mivel virologus, ezert vegigmegy az itemein, hogy valamelyik visszakeni-e
		for(int i = items.size()-1; i>=0; i--)
			if(items.get(i).fireBackEffect(virologist, this, agent))
				 return;
			
		//ha vissza sem keni, akkor hozzaadja a rajt levo agensekhez
		addAgensToMe(agent);
	}
		
	private void addAgensToMe(Agent agent) {
		boolean isBear = false;
		for (Agent ag : appliedAgents) {
			if(ag.check(bearDance))
				isBear = true;
		}
		if (!isBear) {
				applyAgent(agent);
		}
		if (agent.check(bearDance)) {
			MyRunnable.setSelected(null);
			MyRunnable.setIsTouched(false);
			String[] command = new String[1];
			command[0] = "finishturn";
			MyRunnable.getGame().applyBearEffectToAll();
			MyRunnable.getInputFirstAct(command);
		}
	}
	
	public void kill(Virologist virologist) {
		if (virologist.equals(this)) return;
		boolean isKilled = false;
		for (Item item : items) {
			isKilled = item.killEffect(virologist);
			if (isKilled) {
				MyRunnable.log("An enemy has been slain!");
				break;
			}
		}
		if (!isKilled) {
			MyRunnable.log("You need an axe");
		}
	}
	
	public boolean isBear() {
		boolean isBear = false;
		for (Agent ag : appliedAgents) {
			if(ag.check("BearDance"))
				isBear = true;
		}
		return isBear;
	}
	
	public void die() {
		field.remove(this);
		MyRunnable.getGame().removePlayer(this);
	}
	
	public void touch() {
		field.touching(this);
	}
	
	@Override
	public void setField(Field field) {
		this.field = field;
	}
	
	public void setItem(Item item) {
		items.add(item);
	}
	
	
	@Override
	public void step() {
		MyRunnable.getGame().endGame(geneticCodes);
	}
	
	public List<Item> getItems(){
		return items;
	}
}
