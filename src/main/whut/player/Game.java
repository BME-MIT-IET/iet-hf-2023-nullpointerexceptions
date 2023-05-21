package whut.player;
import whut.field.*;
import whut.genetic_code.*;
import whut.item.Axe;
import whut.item.Cloak;
import whut.item.Glove;
import whut.item.Sack;
import whut.material.AminoAcid;
import whut.material.Nucleotide;
import whut.ui.control.MyRunnable;
import whut.ui.control.View;
import whut.ui.observer.GameObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends View implements Serializable {
	private Map map;
	ArrayList<Entity> entities;
	ArrayList<GeneticCode> allGeneticCodes;
	ArrayList<? extends AgentUsable> agentUsables;
	private int players;
	private boolean isRunning = true;
	private Random random = new Random();

	
	public Entity getEntityAt(int index) {
		if(index< entities.size()&&index>=0) {
			return entities.get(index);
		}
		return null;
	}
	
	public Game(int numberOfPlayers)
	{
		players = numberOfPlayers;
		initGame();
	}
	
	public void initGame() //játék inicializálás
	{
		map = new Map();
		entities = new ArrayList<>();
		allGeneticCodes = new ArrayList<>();
		allGeneticCodes.add(new ForgetCode());
		allGeneticCodes.add(new StunCode());
		allGeneticCodes.add(new ProtectionCode());
		allGeneticCodes.add(new ChoreaCode());
		
		createGame();
		MyRunnable.setGame(this);
		attach(new GameObserver(this));
	}
	
	public void applyBearEffectToAll() {
		boolean hasNotInfected = false;
		for (Entity entity : entities) {
			Virologist virologist = (Virologist)entity;
			if (!virologist.isBear()) hasNotInfected = true;
		}
		
		if(!hasNotInfected) {
			isRunning = false;
			((GameObserver)observer.get(0)).drawEnd("Everybody lost!");
		}
	}

	//egy palyat general
	public void mapThird(){
		//A megadott virologusszamnak megfeleloen csinal virologuspeldanyokat
		for(int i = 0; i < players; i++)
			entities.add(new Virologist());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(1).setGeneticCode(new ChoreaCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(2).addItem(new Cloak());
		map.getField(2).addItem(new Cloak());
		map.getField(2).addItem(new Axe());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(4).setGeneticCode(new StunCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbor(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(5).setGeneticCode(new ProtectionCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbor(map.getField(0));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(6).setGeneticCode(new ForgetCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbor(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbor(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(7).getPacket().addMaterial(new Nucleotide());
		map.getField(7).getPacket().addMaterial(new AminoAcid());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbor(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbor(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(8).getPacket().addMaterial(new Nucleotide());
		map.getField(8).getPacket().addMaterial(new AminoAcid());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbor(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(9).addItem(new Glove());
		map.getField(9).addItem(new Axe());
		map.getField(9).addItem(new Axe());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).setNeighbor(map.getField(8));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new EvilLab());
		map.getField(10).setGeneticCode(new StunCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(3));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbor(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbor(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbor(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbor(map.getField(3));
		//A virologusokat felrakja a palyara (elosztva a mezokon)
		for(int i = 0; i < entities.size(); i++)
			map.getField(i % map.getSize()).accept(entities.get(i));
	}


	public void createGame() {
		MyRunnable.setLeft(2);
        int round = random.nextInt(3);

        if(round==0)
			mapFirst();

        if (round==1)
			mapSecond();

        if (round==2)
			mapThird();
	}
	
	public void oneRound() // egy kör, összes entity
	{
		for (Entity entity : entities) {
			entity.step();
		}
	}
	
	public void endGame(List<GeneticCode> allGeneticCodes) //játék végét ellenőrzi, genetikai kódókat hasonlít össze
	{
		boolean[] collected = {false,false,false,false};
		for(int i = 0;i<allGeneticCodes.size();++i)
		{
			for(int j = 0; j< allGeneticCodes.size(); j++)
				if(allGeneticCodes.get(i).toString().equals(allGeneticCodes.get(j).toString()))
					collected[i] = true;
		}
		
		boolean isGameEnded = true;
		for (boolean b : collected)
			if (!b) {
				isGameEnded = false;
				break;
			}
		
		if(isGameEnded) {
			MyRunnable.log("You won :)!");
			((GameObserver)observer.get(0)).drawEnd("You won!");
			isRunning = false;
			
		}
		
	}

	//Visszaadja, hogy megy-e meg a jatek
	public boolean isRunning() {
		return isRunning;
	}
	
	//eltavolit egy jatekost
	public void removePlayer(Virologist virologist) {
		entities.remove(virologist);
	}
	//hozzaad egy jatekost
	public void addPlayer(Virologist virologist) {
		entities.add(virologist);
	}
	//Visszaadja a mapot
	public Map getMap() {
		return map;
	}
	//visszaadja az osszes entity-t
	public List<Entity> getEntities(){
		return entities;
	}
	//general egy palyat
	public void mapFirst() {
		//a megadott jatekosszamnak megfeleloen general virologuspeldanyokat
		for(int i = 0; i < players; i++)
			entities.add(new Virologist());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new EvilLab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).addNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).addNeighbor(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbor(map.getField(0));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).addNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).addNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbor(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbor(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbor(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbor(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).addNeighbor(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).addNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbor(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbor(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbor(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbor(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbor(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).addNeighbor(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).addNeighbor(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).addNeighbor(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).addNeighbor(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbor(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbor(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbor(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbor(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbor(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).addNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbor(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbor(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbor(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbor(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).addNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).addNeighbor(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).addNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).addNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).addNeighbor(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).addNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbor(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbor(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbor(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbor(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbor(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).addNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).addNeighbor(map.getField(0));
		
		map.getField(2).setGeneticCode(new StunCode());
		map.getField(4).setGeneticCode(new ProtectionCode());
		map.getField(17).setGeneticCode(new ChoreaCode());
		map.getField(14).setGeneticCode(new StunCode());
		map.getField(10).setGeneticCode(new ForgetCode());
		map.getField(8).setGeneticCode(new ChoreaCode());
		
		map.getField(19).addItem(new Sack());
		map.getField(19).addItem(new Sack());

		map.getField(12).addItem(new Glove());
		map.getField(12).addItem(new Axe());
		map.getField(12).addItem(new Cloak());
		
		map.getField(15).addItem(new Cloak());
		map.getField(15).addItem(new Sack());
		
		map.getField(18).getPacket().addMaterial(new AminoAcid());
		map.getField(18).getPacket().addMaterial(new Nucleotide());
		map.getField(13).getPacket().addMaterial(new AminoAcid());
		map.getField(13).getPacket().addMaterial(new Nucleotide());
		map.getField(5).getPacket().addMaterial(new AminoAcid());
		map.getField(5).getPacket().addMaterial(new Nucleotide());
		
		int field;
		for (Entity entity : entities) {
			field = random.nextInt(20);
			map.getField(field).accept(entity);
		}
	}
	
	public void mapSecond() {
		for(int i = 0; i < players; i++)
			entities.add(new Virologist());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(0).setGeneticCode(new ChoreaCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(2).addItem(new Sack());
		map.addField(new Storage());
		map.getField(3).getPacket().addMaterial(new AminoAcid());
		map.getField(3).getPacket().addMaterial(new Nucleotide());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(4).addItem(new Axe());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(5).getPacket().addMaterial(new AminoAcid());
		map.getField(5).getPacket().addMaterial(new Nucleotide());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//E
		map.getField(6).setGeneticCode(new ForgetCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(9).setGeneticCode(new ProtectionCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(11).addItem(new Glove());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new EvilLab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(13).getPacket().addMaterial(new AminoAcid());
		map.getField(13).getPacket().addMaterial(new Nucleotide());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(14).addItem(new Glove());

		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(16).getPacket().addMaterial(new AminoAcid());
		map.getField(16).getPacket().addMaterial(new Nucleotide());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.getField(18).setGeneticCode(new StunCode());
		map.addField(new Shelter());
		map.getField(19).addItem(new Cloak());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(20).addItem(new Axe());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(21).setGeneticCode(new ForgetCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(22).getPacket().addMaterial(new AminoAcid());
		map.getField(22).getPacket().addMaterial(new Nucleotide());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(24).getPacket().addMaterial(new AminoAcid());
		map.getField(24).getPacket().addMaterial(new Nucleotide());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbor(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbor(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbor(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbor(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbor(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).setNeighbor(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).setNeighbor(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).setNeighbor(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbor(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbor(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbor(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbor(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbor(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbor(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbor(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).setNeighbor(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbor(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbor(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbor(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbor(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbor(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbor(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbor(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbor(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbor(map.getField(20));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).setNeighbor(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).setNeighbor(map.getField(21));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).setNeighbor(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).setNeighbor(map.getField(22));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).setNeighbor(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).setNeighbor(map.getField(23));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).setNeighbor(map.getField(24));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(20).setNeighbor(map.getField(21));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(21).setNeighbor(map.getField(22));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(22).setNeighbor(map.getField(23));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(23).setNeighbor(map.getField(24));


		
		for(int i = 0; i < entities.size(); i++)
			map.getField(i % map.getSize()).accept(entities.get(i));
		
	}
	
}


