package whut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

//A megvalositott genetikus kodok ososztalya
//ArrayList<Material> cost - az elkeszitesehez szukseges anyagok
public abstract class GeneticCode implements Serializable{
	protected ArrayList<Material> cost;
	
	//Konstruktor, melyben a cost-nak adok ertekeket
	public GeneticCode() 
	{
		cost = new ArrayList<Material>();
		Material amc=new Aminosav();
		amc.setValue(15);
		cost.add(amc);
		
		Material nuk=new Nukleotid();
		nuk.setValue(15);
		cost.add(nuk);
		Random rand = new Random();
		//maximum ket anyagba kerülhet
		/*
		int costDb = rand.nextInt(1);
		for(int i = 0; i <= costDb; i++)
		{
			//general egy random erteket mely szerint aminosavat vagy nukleotidot hozunk letre
			boolean melyiket = rand.nextBoolean();
			Material mat;
			if(melyiket)
				mat = new Aminosav();
			else
				mat = new Nukleotid();
			mat.setValue(1);
			cost.add(mat);
		}
		*/	
	}
	
	//ezen fuggvenyt meg kell valositania a leszarmazottaknak
	//letrehozza a megfelelo agenst, es hozzaadja a parameterkent kapott agens hasznalonak
	//AgensUsable au - ezen entity fogja megkapni a letrehozott agenst
	public abstract void createAgens(AgensUsable au);
	
	public ArrayList<Material> getCost(){
		return cost;
	}
	
	public boolean Check(String s) {
		return false;
	}
}
