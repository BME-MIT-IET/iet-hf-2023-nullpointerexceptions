package material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//Egy zseb, ami zabben l�v� anyagok kezel�s��rt felel
//ArrayList<Material> materials - a t�rolt anyagok list�ja
//int maxPerMaterial - a maximum t�rolhat� anyagmennyis�g az egyes anyagt�pusokb�l
public class Packet implements Serializable
{
	private ArrayList<Material> materials = new ArrayList<>();
	private int maxPerMaterial;
	
	//Konstruktor, alapbol csak a zseb meretet allitja be
	public Packet()
	{
		maxPerMaterial = 50;
	}
	
	//Torli a listaban kapott anyagmennyiseget ezen zsebbol
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebbol a zsebbol
	//visszateresi erteke azt mondja meg, hogy sikerult-e levonni ezen zsebbol a listaban kapott anyagmennyisegeket
	public boolean decreaseMaterial(List<Material> mats) 
	{	
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
		
		if (nukSum < nuklevon || aminoSum < aminolevon){
			return false;
		} else {
			int nukMarad = nukSum-nuklevon; //ennyi nukleotidunk marad a levon�s ut�n
			int aminoMarad = aminoSum-aminolevon; //ennyi aminosavunk marad a levon�s ut�n
			materials.clear();
			Aminosav a = new Aminosav();
			a.setValue(aminoMarad);
			materials.add(a);
			Nukleotid n = new Nukleotid();
			n.setValue(nukMarad);
			materials.add(n);
			
			mats.clear();
			return true;
		}
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
			
			//a fuggv�ny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<>();  
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
			ArrayList<Material> m = new ArrayList<>();
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
			
	
		//kelleni fog majd az anyag kiv�tel�hez, a decreasMateral() fuggveny parametere miatt
		ArrayList<Material> material = new ArrayList<>();
		
		//a megfelezett anyagot hozzaadjuk
		material.add(mat);
		
		// a megfelezett anyagot levonjuk ezen zsebbol
		this.decreaseMaterial(material);	
	}
	
	//vissza adja a zseb anyaglistajat
	public List<Material> getMaterials() 
	{
		return materials;
	}
	
	public Material getMaterial(String s) {
		for(Material m: materials) {
			if(m.check(s)) {
				return m;
			}
		}
		return null;
	}
	
	//A parameterkent kapott anyagot hozzaadja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material mat)
	{		
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
