package whut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//A megvalositott genetikus kodok ososztalya
//ArrayList<Material> cost - az elkeszitesehez szukseges anyagok
public abstract class GeneticCode implements Serializable{
	protected ArrayList<Material> cost;
	
	//Konstruktor, melyben a cost-nak adok ertekeket
	protected GeneticCode() 
	{
		cost = new ArrayList<>();
		Material amc=new Aminosav();
		amc.setValue(15);
		cost.add(amc);
		
		Material nuk=new Nukleotid();
		nuk.setValue(15);
		cost.add(nuk);	
	}
	
	//ezen fuggvenyt meg kell valositania a leszarmazottaknak
	//letrehozza a megfelelo agenst, es hozzaadja a parameterkent kapott agens hasznalonak
	//AgensUsable au - ezen entity fogja megkapni a letrehozott agenst
	public abstract void createAgens(AgensUsable au);
	
	public List<Material> getCost(){
		return cost;
	}
	
	public abstract boolean check(String s);
}
