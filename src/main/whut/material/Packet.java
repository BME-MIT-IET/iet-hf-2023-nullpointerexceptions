package whut.material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//Egy zseb, ami zabben l�v� anyagok kezel�s��rt felel
//ArrayList<Material> materials - a t�rolt anyagok list�ja
//int maxPerMaterial - a maximum t�rolhat� anyagmennyis�g az egyes anyagt�pusokb�l
public class Packet implements Serializable
{
	private ArrayList<Material> materials = new ArrayList<>();
	private int maxAmountOfEachMaterial;
	
	//Konstruktor, alapbol csak a zseb meretet allitja be
	public Packet()
	{
		maxAmountOfEachMaterial = 50;
	}
	
	//Torli a listaban kapott anyagmennyiseget ezen zsebbol
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebbol a zsebbol
	//visszateresi erteke azt mondja meg, hogy sikerult-e levonni ezen zsebbol a listaban kapott anyagmennyisegeket
	public boolean decreaseMaterial(List<Material> materials)
	{	
		int nucleotideNeeded = 0; //ennyi nukleotidot akarunk levonni
		int aminoAcidNeeded = 0; //ennyi aminosavat akarunk levonni
		for(Material m : materials) {
			if(m.sameAs(new Nucleotide()))
				nucleotideNeeded += m.getAmount();
			else
				aminoAcidNeeded += m.getAmount();
		}
		
		int nucleotideAmount = 0; //ennyi nukleotidunk van
		int aminoAcidAmount = 0; //ennyi aminosavunk van
		for (Material m : this.materials) {
			if(m.sameAs(new Nucleotide()))
				nucleotideAmount += m.getAmount();
			else
				aminoAcidAmount += m.getAmount();
		}
		
		if (nucleotideAmount < nucleotideNeeded || aminoAcidAmount < aminoAcidNeeded){
			return false;
		} else {
			int nucleotideRemaining = nucleotideAmount-nucleotideNeeded; //ennyi nukleotidunk marad a levon�s ut�n
			int aminoAcidRemaining = aminoAcidAmount-aminoAcidNeeded; //ennyi aminosavunk marad a levon�s ut�n
			this.materials.clear();
			AminoAcid amino = new AminoAcid();
			amino.setAmount(aminoAcidRemaining);
			this.materials.add(amino);
			Nucleotide nucleo = new Nucleotide();
			nucleo.setAmount(nucleotideRemaining);
			this.materials.add(nucleo);
			
			materials.clear();
			return true;
		}
	}
	
	//megvaltoztatjuk a maximum tarolhato anyagmennyiseget anyagonkont
	//int value - az ertek amivel megvaltoztatjuk a maximum tarolhato anyagmennyiseget
	public void changeMaxMaterial(int amountToAdd)
	{
		maxAmountOfEachMaterial += amountToAdd;
	}
	
	//lekezeli azt az esetet, hogy ha a zseb meretenek csokkentesekor anyagot is kellene kidobnunk a zsebbol
	//int value - az ertek amivel csokken a zseb max tarolasi kapacitasa
	public void handlePossibleLostMaterial(int value)
	{

		
		int nucleotideAmount = 0;  //mennyi nukleotidsavunk van
		int aminoAcidAmount = 0;  //mennyi aminosavunk van
		
		//vegig megyunk a zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : materials) {
			if(m.sameAs(new Nucleotide()))
				nucleotideAmount += m.getAmount();
			else
				aminoAcidAmount += m.getAmount();
		}
		
		//ha tobb aminosavunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(aminoAcidAmount > maxAmountOfEachMaterial -value)
		{
			AminoAcid amino = new AminoAcid();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			amino.setAmount(aminoAcidAmount- maxAmountOfEachMaterial +value);
			
			//a fuggv�ny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<>();  
			m.add(amino);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}
		
		
		//ha tobb nukleotidunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(nucleotideAmount > maxAmountOfEachMaterial -value)
		{
			Nucleotide nucleo = new Nucleotide();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			nucleo.setAmount(nucleotideAmount- maxAmountOfEachMaterial +value);
			
			//a fuggveny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<>();
			m.add(nucleo);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}

		//atalitjuk a zseb meretet
		this.changeMaxMaterial(-value);
	}
	
	//Mindig meghivodik amikor felveszunk egy anyagot
	//abban az esetben ha egy anyagnak a merete nagyobb lenne mint amennyit fel tudnunk venni azt le is kezeli
	//Material materialToPickUp - azon anyag amit fel akar venni a jatekos
	//Packet packet - azon jatekos zsebe aki fel akarja venni az anyagot
	public void handleMaterialSeparate(Material materialToPickUp, Packet packet) //pac virologuse sajat
	{
		
		int nucleotideInPacket = 0;  //mennyi nukleotidsavunk van a pac-ban
		int aminoAcidInPacket = 0;  //mennyi aminosavunk van a pac-ban
		int currentNucleotideAmount = 0;  //mennyi nukleotidsavunk van a this-ben
		int currentAminoAcidCount = 0;  //mennyi aminosavunk van a this-ben
		
		//vegig megyenk a kapott zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : packet.getMaterials())
		{
			if(m.sameAs(new Nucleotide()))
				nucleotideInPacket += m.getAmount();
			else
				aminoAcidInPacket += m.getAmount();
		}
		
		for(Material m : this.getMaterials()) 
		{
			if(m.sameAs(new Nucleotide()))
				currentNucleotideAmount += m.getAmount();
			else
				currentAminoAcidCount += m.getAmount();
		}
		
		//ebbe az anyagba fog felezodni a kapott anyag ha kell
		Material material;
		
		
		//ha olyan mennyiseget adna meg amennyi nincs is a zsebben
		if(materialToPickUp.sameAs(new Nucleotide()) && materialToPickUp.getAmount() > currentNucleotideAmount)
			return;
	
		if(materialToPickUp.sameAs(new AminoAcid()) && materialToPickUp.getAmount() > currentAminoAcidCount)
			return;
		
		
		//amennyiben nukleotidrol van szo
		if(materialToPickUp.sameAs(new Nucleotide()))
		{
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(nucleotideInPacket + materialToPickUp.getAmount() > maxAmountOfEachMaterial)
			{
				//megfelezzuk az anyagot
				material = new Nucleotide();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				material.setAmount(maxAmountOfEachMaterial - nucleotideInPacket);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				packet.addMaterial(material);
				
				//az anyag erteket pedig lecsokkentjuk
				materialToPickUp.setAmount(materialToPickUp.getAmount()-(maxAmountOfEachMaterial -nucleotideInPacket));
				this.addMaterial(materialToPickUp);
				
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				//hozzaadjuk a kapott zsebhez az anyagot
				packet.addMaterial(materialToPickUp);
			}
		}
		//amennyiben aminosavrol van szo
		else
		{	
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(aminoAcidInPacket + materialToPickUp.getAmount() > maxAmountOfEachMaterial)
			{
				material = new AminoAcid();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				material.setAmount(maxAmountOfEachMaterial -aminoAcidInPacket);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				packet.addMaterial(material);
				
				//az anyag erteket pedig lecsokkentjuk
				materialToPickUp.setAmount(materialToPickUp.getAmount()-(maxAmountOfEachMaterial -aminoAcidInPacket));
				this.addMaterial(materialToPickUp);
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				//hozzaadjuk a kapott zsebhez az anyagot
				packet.addMaterial(materialToPickUp);
			}
		}
			
	
		//kelleni fog majd az anyag kiv�tel�hez, a decreasMateral() fuggveny parametere miatt
		ArrayList<Material> materialList = new ArrayList<>();
		
		//a megfelezett anyagot hozzaadjuk
		materialList.add(materialToPickUp);
		
		// a megfelezett anyagot levonjuk ezen zsebbol
		this.decreaseMaterial(materialList);
	}
	
	//vissza adja a zseb anyaglistajat
	public List<Material> getMaterials() 
	{
		return materials;
	}
	
	public Material getMaterial(String materialType) {
		for(Material m: materials) {
			if(m.check(materialType)) {
				return m;
			}
		}
		return null;
	}
	
	//A parameterkent kapott anyagot hozzaadja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material material)
	{		
		if(material.sameAs(new AminoAcid())) {
			Material materialAm=new AminoAcid();
			materialAm.setAmount(material.getAmount());
			this.materials.add(materialAm);		
		}else{
			Material materialNuk=new Nucleotide();
			materialNuk.setAmount(material.getAmount());
			this.materials.add(materialNuk);	
		}
	}
	
	public int getMaxMaterial() {
		return maxAmountOfEachMaterial;
	}

}
