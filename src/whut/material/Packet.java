package whut.material;
import java.io.Serializable;
import java.util.ArrayList;


//Egy zseb, ami zabben lï¿½vï¿½ anyagok kezelï¿½sï¿½ï¿½rt felel
//ArrayList<Material> materials - a tï¿½rolt anyagok listï¿½ja
//int maxPerMaterial - a maximum tï¿½rolhatï¿½ anyagmennyisï¿½g az egyes anyagtï¿½pusokbï¿½l
public class Packet implements Serializable
{
	private ArrayList<Material> materials = new ArrayList<Material>();
	private int maxPerMaterial;
	
	//Konstruktor, alapbol csak a zseb meretet allitja be
	public Packet()
	{
		maxPerMaterial = 50;
	}
	
	//Torli a listaban kapott anyagmennyiseget ezen zsebbol
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebbol a zsebbol
	//visszateresi erteke azt mondja meg, hogy sikerult-e levonni ezen zsebbol a listaban kapott anyagmennyisegeket
	public boolean decreaseMaterial(ArrayList<Material> mats) 
	{	
		//System.out.println("decrease........................................");
		int nuklevon = 0; //ennyi nukleotidot akarunk levonni
		int aminolevon = 0; //ennyi aminosavat akarunk levonni
		for(Material m : mats) {
			if(m.isSame(new Nukleotid()))
				nuklevon += m.getValue();
			else
				aminolevon += m.getValue();
		}
		
		int nukSum = 0; //ennyi nukleotidunk van
		int aminoSum = 0; //ennyi aminosavunk van
		for (Material m : this.materials) {
			if(m.isSame(new Nukleotid()))
				nukSum += m.getValue();
			else
				aminoSum += m.getValue();
		}
		//System.out.println("nuk a fielden: "+nukSum);
		//System.out.println("amino a fielden: "+aminoSum);
		//System.out.println("nuk amit levonunk: "+nuklista);
		//System.out.println("amino amit levonunk: "+aminolista);
		
		if (nukSum < nuklevon || aminoSum < aminolevon){
			return false;
		} else {
			int nukMarad = nukSum-nuklevon; //ennyi nukleotidunk marad a levonás után
			int aminoMarad = aminoSum-aminolevon; //ennyi aminosavunk marad a levonás után
			materials.clear();
			//System.out.println("nukMarad: "+nukMarad);
			//System.out.println("aminoMarad: "+aminoMarad);
			Aminosav a = new Aminosav();
			a.setValue(aminoMarad);
			materials.add(a);
			Nukleotid n = new Nukleotid();
			n.setValue(nukMarad);
			materials.add(n);
			
			mats.clear();
			return true;
		}
		//le masoljuk a zseb tartalmat, hogy ha nem tudnank levonni akkor semmisse tudjuk tenni a muveletet
		/*ArrayList<Material> decreas = materials;
		
		
	        ArrayList<String> ss = new ArrayList<String>();
	        int nukNum = 0;
	        int aminoNum = 0;
	        for(Material m : mats){
	            if(m.isSame(new Nukleotid())){
	            	nukNum+= m.getValue();
	                //System.out.print(m.getValue());
	            }else{
	            	aminoNum+=m.getValue();
	                //System.out.print(m.getValue());
	            }
	            ss.add(m.toString());
	       	}
		//System.out.println("decrease DDDDDDDDDDDDD"+nukNum+"amino:"+aminoNum);
		
		//addig megyunk amig az egyik lista ki nem urul
		//ha a mats urul ki akkor meg van a kello anyag mennyiseg
		//ha a decreas urul ki akkor pedig nincs elegendo anyagunk
		while((mats.size() > 0) && (decreas.size() > 0)) {
			
			//int i = 0;
			//vegig megyÃ¼nk a kapott anyag listan
			//while(i < mats.size()) {
			for(int i = mats.size()-1; i >= 0; i--) {
				//int j = 0;
				System.out.println("kulsoooooooooooooo");
				//vegig megyunk a sajat listank masolatan
				//while(j < decreas.size()) {
				for(int j = decreas.size()-1; j >= 0; j--) {
					System.out.println(i+ "   size:" +mats.size());
					System.out.println(j+ "   size:" +decreas.size());
					//ha azonos tipusu anyagot talaltunk akkor tehetunk valamit
					if(mats.get(i).isSame(decreas.get(j)))
					{
						//ha ezen zseb eppen soron levo anyaganak mennyisegbol kevesebb van mint amennyi kellene
						if(decreas.get(j).getValue() - mats.get(i).getValue() <= 0) 
						{
							System.out.println("elvileg nincs eleg");
							//return false;
							mats.get(i).setValue(mats.get(i).getValue()-decreas.get(j).getValue());
							
							//toroljuk a zsebunkben levo anyagot mert elfogyott
							decreas.remove(j);
							//j--;
						}
						
						//ha elegendo van
						else
						{
							decreas.get(j).setValue(decreas.get(j).getValue()-mats.get(i).getValue());
							System.out.println("levonom");
							//toroljuk a kapott listabol az anyagot mert le lett vonva
							mats.get(i).setValue(0);
							System.out.println("levonom2");
						}
					}
					//j++;
				}
				//ha le tudtuk vonni ezen zsebbol a kapott listabeli anyagot, akkor a kapott lista anyagat is toroljuk a levonando anyagok kozul
				if(mats.get(i).getValue() == 0) {
					System.out.println("levonom3");
					mats.remove(i);
					System.out.println("levonom4");
				}
				i--;
				//else
					//i++;
			}
		}
		
		
		//megnezzuk hogy a legvegen maradt-e anyag a kapott listaban
		if(mats.size()==0) {
			materials = decreas;
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}*/
	}
	
	//megvaltoztatjuk a maximum tarolhato anyagmennyiseget anyagonkont
	//int value - az ertek amivel megvaltoztatjuk a maximum tarolhato anyagmennyiseget
	public void changeMaxMaterial(int value) 
	{
		maxPerMaterial += value;
	}
	
	//lekezeli azt az esetet, hogy ha a zseb meretenek csokkentesekor anyagot is kellene kidobnunk a zsebbol
	//int value - az ertek amivel csokken a zseb max tarolasi kapacitasa
	public void handlePossibleLostMaterial(int value)
	{

		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//vegig megyunk a zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ha tobb aminosavunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(matsMaterialADb > maxPerMaterial-value)
		{
			Aminosav a = new Aminosav();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			a.setValue(matsMaterialADb-maxPerMaterial+value);
			
			//a fuggvï¿½ny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();  
			m.add(a);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}
		
		
		//ha tobb nukleotidunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(matsMaterialNDb > maxPerMaterial-value)
		{
			Nukleotid a = new Nukleotid();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			a.setValue(matsMaterialNDb-maxPerMaterial+value);
			
			//a fuggveny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}

		//atalitjuk a zseb meretet
		this.changeMaxMaterial(-value);
	}
	
	//Mindig meghivodik amikor felveszunk egy anyagot
	//abban az esetben ha egy anyagnak a merete nagyobb lenne mint amennyit fel tudnunk venni azt le is kezeli
	//Material mat - azon anyag amit fel akar venni a jatekos
	//Packet pac - azon jatekos zsebe aki fel akarja venni az anyagot
	public void handleMaterialSeparate(Material mat, Packet pac) //pac virologuse sajat
	{
		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van a pac-ban
		int matsMaterialADb = 0;  //mennyi aminosavunk van a pac-ban
		int thismatsMaterialNDb = 0;  //mennyi nukleotidsavunk van a this-ben
		int thismatsMaterialADb = 0;  //mennyi aminosavunk van a this-ben
		
		//vegig megyenk a kapott zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : pac.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		for(Material m : this.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				thismatsMaterialNDb += m.getValue();
			else
				thismatsMaterialADb += m.getValue();
		}
		
		//ebbe az anyagba fog felezodni a kapott anyag ha kell
		Material m = null;
		
		
		//ha olyan mennyiseget adna meg amennyi nincs is a zsebben
		if(mat.isSame(new Nukleotid()) && mat.getValue() > thismatsMaterialNDb)
			return;
	
		if(mat.isSame(new Aminosav()) && mat.getValue() > thismatsMaterialADb)
			return;
		
		
		//amennyiben nukleotidrol van szo
		if(mat.isSame(new Nukleotid())) 
		{
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(matsMaterialNDb + mat.getValue() > maxPerMaterial) 
			{
				//megfelezzuk az anyagot
				m = new Nukleotid();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				m.setValue(maxPerMaterial - matsMaterialNDb);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag erteket pedig lecsokkentjuk
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialNDb));
				this.addMaterial(mat);
				
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				//hozzaadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
		//amennyiben aminosavrol van szo
		else
		{	
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(matsMaterialADb + mat.getValue() > maxPerMaterial) 
			{
				m = new Aminosav();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialADb);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag erteket pedig lecsokkentjuk
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialADb));
				this.addMaterial(mat);
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				//hozzaadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
			
	
		//kelleni fog majd az anyag kivï¿½telï¿½hez, a decreasMateral() fuggveny parametere miatt
		ArrayList<Material> material = new ArrayList<Material>();
		
		//a megfelezett anyagot hozzaadjuk
		material.add(mat);
		
		// a megfelezett anyagot levonjuk ezen zsebbol
		this.decreaseMaterial(material);	
	}
	
	//vissza adja a zseb anyaglistajat
	public ArrayList<Material> getMaterials() 
	{
		return materials;
	}
	public Material getMaterial(String s) {
		for(Material m: materials) {
			if(m.Check(s)) {
				return m;
			}
		}
		return null;
	}
	
	//A parameterkent kapott anyagot hozzaadja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material mat)
	{
		//System.out.println(mat.getValue()+"aaaaaaaaaaaaaaaaaaaaaaaaa");
		
		if(mat.isSame(new Aminosav())) {
			Material materialAm=new Aminosav();
			materialAm.setValue(mat.getValue());
			this.materials.add(materialAm);		
		}else{
			Material materialNuk=new Nukleotid();
			materialNuk.setValue(mat.getValue());
			this.materials.add(materialNuk);	
		}
	}
	
	public int getMaxMaterial() {
		return maxPerMaterial;
	}

}
