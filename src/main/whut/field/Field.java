package whut.field;
import whut.genetic_code.GeneticCode;
import whut.item.Item;
import whut.material.Packet;
import whut.player.AgentUsable;
import whut.player.Entity;
import whut.player.Virologist;
import whut.ui.control.MyRunnable;
import whut.ui.control.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Field extends View implements Serializable
{
	protected ArrayList<AgentUsable> agentUsableList; // A mezőn található entity-k
	private final ArrayList<Field> neighbors; //szomszédos mezők

	
	public Field()
	{
		agentUsableList = new ArrayList<>(); //létrehozza a virológust
		neighbors = new ArrayList<>();
	}
	

	
	//visszaadja a virológusok listáját
	public List<AgentUsable> getAgentUsableList()
	{
		return agentUsableList;
	}
	
	//törli a virológust a listából
	public void remove(Entity entity)
	{
		agentUsableList.remove(entity);
	}
	
	//hozzáadja a virológust a listához
	public void accept(Entity entity)
	{
		entity.setField(this);
	}
	
	//mező érintés esetén hívódik meg
	public void touching(Virologist virologist){
		//leszarmazott felulirja
	}
	
	//szomszédos mezők beállítása
	public void setNeighbor(Field field){
		if(!field.equals(this)) {
		neighbors.add(field);
		field.addNeighbor(this);}
		//skeletonnál még kézzel
	}
	
	public void addNeighbor(Field field) {
		neighbors.add(field);
	}
	
	//visszaadja a szomszéd mezők listáját
	public List<Field> getNeighborhood(){
		return neighbors;
	}

	public void removeItem(Item item) {
		MyRunnable.log("Cant pickup item from here!");
	}
	public void addItem(Item item) {
		MyRunnable.log("Cant leave item here!");
	}
	
	public GeneticCode getGeneticCode() {
		return null;
	}
	
	public List<Item> getItems() {
		return new ArrayList<>();
	}
	
	public Packet getPacket() {
		return null;
	}

	public Item getItem(String itemType) {
		MyRunnable.log("Cant pickup "+itemType+" from here!");
		return null;
	}

	public void addVirologist(Virologist virologist){
		agentUsableList.add(virologist);
	}
	
	public void setGeneticCode(GeneticCode geneticCode) {
		MyRunnable.log("Ide nem rakhatsz!");
	}
	
	@Override
	public String toString() {
		return "Field";
	}
	
}

