package whut;
import java.io.Serializable;
import java.util.ArrayList;


public class Field extends View implements Serializable
{
	protected ArrayList<AgensUsable> au; // A mezőn található entity-k
	private ArrayList<Field> neighbor; //szomszédos mezők

	
	public Field()
	{
		au = new ArrayList<AgensUsable>(); //létrehozza a virológust
		neighbor = new ArrayList<Field>();
	}
	

	
	//visszaadja a virológusok listáját
	public ArrayList<AgensUsable> getVirologusok()
	{
		return au;
	}
	
	//törli a virológust a listából
	public void remove(Entity v)
	{
		au.remove(v);
	}
	
	//hozzáadja a virológust a listához
	public void accept(Entity v)
	{
		Virologus ag = (Virologus)v;
		au.add(ag);
		v.setField(this);
	}
	
	//mező érintés esetén hívódik meg
	public void touching(Virologus v)
	{

	}
	
	//szomszédos mezők beállítása
	public void setNeighbour(Field f){
		if(!f.equals(this)) {
		neighbor.add(f);
		f.addNeighbour(this);}
		//skeletonnál még kézzel
	}
	
	public void addNeighbour(Field f) {
		neighbor.add(f);
	}
	
	//visszaadja a szomszéd mezők listáját
	public ArrayList<Field> getNeighbourhood()
	{
		return neighbor;
	}

	public void removeItem(Item i) {
		MyRunnable.log("Cant pickup item from here!");
	}
	public void addItem(Item i) {
		MyRunnable.log("Cant leave item here!");
	}
	
	public GeneticCode codeHere() {
		return null;
	}
	
	public ArrayList<Item> getItems() {
		return null;
	}
	
	public Packet getPacket() {
		return null;
	}

	public Item getItem(String getThis) {
		MyRunnable.log("Cant pickup "+getThis+" from here!");
		return null;
	}
	
	public void setGeneticCode(GeneticCode gc) {
		MyRunnable.log("Ide nem rakhatsz!");
	}
	
	@Override
	public String toString() {
		return "field";
	}
	
}

