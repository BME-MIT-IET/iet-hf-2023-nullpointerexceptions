package whut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends View implements Serializable {
	private Map map;
	ArrayList<Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	ArrayList<? extends AgensUsable> au;
	private int players;
	private boolean megy = true;
	private Random rand = new Random();

	
	public Entity getEntityAt(int index) {
		if(index<entity.size()&&index>=0) {
			return entity.get(index);
		}
		return null;
	}
	
	public Game(int playerNum)
	{
		players = playerNum;
		initGame();
	}
	
	public void initGame() //játék inicializálás
	{
		map = new Map();
		entity = new ArrayList<>();
		allGeneticCode = new ArrayList<>();
		allGeneticCode.add(new ForgetCode());
		allGeneticCode.add(new StunCode());
		allGeneticCode.add(new ProtectionCode());
		allGeneticCode.add(new VitusdanceCode());
		
		createGame();
		MyRunnable.setGame(this);
		attach(new GameObserver(this));
	}
	
	public void bearAll() {
		boolean vanJozan = false;
		for (Entity e : entity) {
			Virologus v = (Virologus)e;
			if (!v.isBear()) vanJozan = true;
		}
		
		if(!vanJozan) {
			megy = false;
			((GameObserver)observer.get(0)).drawEnd("Everybody lost!");
		}
	}

	//egy palyat general
	public void mapThird(){
		//A megadott virologusszamnak megfeleloen csinal virologuspeldanyokat
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(1).setGeneticCode(new VitusdanceCode());
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
		map.getField(0).setNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbour(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(5).setGeneticCode(new ProtectionCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbour(map.getField(0));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(6).setGeneticCode(new ForgetCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbour(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbour(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(7).getPacket().addMaterial(new Nukleotid());
		map.getField(7).getPacket().addMaterial(new Aminosav());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbour(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbour(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(8).getPacket().addMaterial(new Nukleotid());
		map.getField(8).getPacket().addMaterial(new Aminosav());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbour(map.getField(4));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(9).addItem(new Glove());
		map.getField(9).addItem(new Axe());
		map.getField(9).addItem(new Axe());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).setNeighbour(map.getField(8));
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new EvilLab());
		map.getField(10).setGeneticCode(new StunCode());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(3));
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
		map.getField(11).setNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbour(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbour(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbour(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbour(map.getField(3));
		//A virologusokat felrakja a palyara (elosztva a mezokon)
		for(int i = 0; i < entity.size(); i++)
			map.getField(i % map.getSize()).accept(entity.get(i));
	}


	public void createGame() {
		MyRunnable.setLeft(2);
        int kor=rand.nextInt(3);
        if(kor==0) mapFirst();
        if (kor==1) mapSecond();
        if (kor==2) mapThird();

	}
	
	public void oneRound() // egy kör, összes entity
	{
		for(int i = 0;i<entity.size();++i)
		{
			  entity.get(i).step();
		}
	}
	
	public void endGame(List<GeneticCode> all) //játék végét ellenőrzi, genetikai kódókat hasonlít össze
	{
		boolean[] megvannak = {false,false,false,false};
		for(int i = 0;i<all.size();++i)
		{
			for(int j = 0; j<allGeneticCode.size(); j++)
				if(all.get(i).toString().equals(allGeneticCode.get(j).toString()))
					megvannak[i] = true;
		}
		
		boolean nem = true;
		for (int i = 0; i< megvannak.length; i++)
			if(!megvannak[i])
				nem = false;
		
		if(nem) {
			MyRunnable.log("You won :)!");
			((GameObserver)observer.get(0)).drawEnd("You won!");
			megy = false;
			
		}
		
	}

	//Visszaadja, hogy megy-e meg a jatek
	public boolean getMegy() {
		return megy;
	}
	
	//eltavolit egy jatekost
	public void removePlayer(Virologus v) {
		entity.remove(v);
	}
	//hozzaad egy jatekost
	public void addPlayer(Virologus v) {
		entity.add(v);
	}
	//Visszaadja a mapot
	public Map getMap() {
		return map;
	}
	//visszaadja az osszes entity-t
	public List<Entity> getEntity(){
		return entity;
	}
	//general egy palyat
	public void mapFirst() {
		//a megadott jatekosszamnak megfeleloen general virologuspeldanyokat
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
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
		map.getField(0).addNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).addNeighbour(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbour(map.getField(0));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).addNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).addNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).addNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbour(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).addNeighbour(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbour(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).addNeighbour(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).addNeighbour(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).addNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbour(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbour(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).addNeighbour(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbour(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).addNeighbour(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).addNeighbour(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).addNeighbour(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).addNeighbour(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).addNeighbour(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbour(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbour(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).addNeighbour(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbour(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbour(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).addNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).addNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbour(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbour(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbour(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).addNeighbour(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).addNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).addNeighbour(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).addNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).addNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).addNeighbour(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).addNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbour(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbour(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).addNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbour(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbour(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).addNeighbour(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).addNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).addNeighbour(map.getField(0));
		
		map.getField(2).setGeneticCode(new StunCode());
		map.getField(4).setGeneticCode(new ProtectionCode());
		map.getField(17).setGeneticCode(new VitusdanceCode());
		map.getField(14).setGeneticCode(new StunCode());
		map.getField(10).setGeneticCode(new ForgetCode());
		map.getField(8).setGeneticCode(new VitusdanceCode());
		
		map.getField(19).addItem(new Sack());
		map.getField(19).addItem(new Sack());

		map.getField(12).addItem(new Glove());
		map.getField(12).addItem(new Axe());
		map.getField(12).addItem(new Cloak());
		
		map.getField(15).addItem(new Cloak());
		map.getField(15).addItem(new Sack());
		
		map.getField(18).getPacket().addMaterial(new Aminosav());
		map.getField(18).getPacket().addMaterial(new Nukleotid());
		map.getField(13).getPacket().addMaterial(new Aminosav());
		map.getField(13).getPacket().addMaterial(new Nukleotid());
		map.getField(5).getPacket().addMaterial(new Aminosav());
		map.getField(5).getPacket().addMaterial(new Nukleotid());
		
		int field;
		for(int i = 0; i < entity.size(); i++) {
			field = rand.nextInt(20);
			map.getField(field).accept(entity.get(i));
		}
	}
	
	public void mapSecond() {
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Lab());
		map.getField(0).setGeneticCode(new VitusdanceCode());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(2).addItem(new Sack());
		map.addField(new Storage());
		map.getField(3).getPacket().addMaterial(new Aminosav());
		map.getField(3).getPacket().addMaterial(new Nukleotid());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(4).addItem(new Axe());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(5).getPacket().addMaterial(new Aminosav());
		map.getField(5).getPacket().addMaterial(new Nukleotid());
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
		map.getField(13).getPacket().addMaterial(new Aminosav());
		map.getField(13).getPacket().addMaterial(new Nukleotid());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Shelter());
		map.getField(14).addItem(new Glove());

		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(16).getPacket().addMaterial(new Aminosav());
		map.getField(16).getPacket().addMaterial(new Nukleotid());
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
		map.getField(22).getPacket().addMaterial(new Aminosav());
		map.getField(22).getPacket().addMaterial(new Nukleotid());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Field());
		//Elkeszit egy mezot es hozzaadja a map(terkep)-hez..
		map.addField(new Storage());
		map.getField(24).getPacket().addMaterial(new Aminosav());
		map.getField(24).getPacket().addMaterial(new Nukleotid());
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbour(map.getField(1));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(0).setNeighbour(map.getField(5));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbour(map.getField(2));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(1).setNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbour(map.getField(3));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(2).setNeighbour(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).setNeighbour(map.getField(4));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(3).setNeighbour(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(4).setNeighbour(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbour(map.getField(6));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(5).setNeighbour(map.getField(10));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbour(map.getField(7));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(6).setNeighbour(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbour(map.getField(8));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(7).setNeighbour(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbour(map.getField(9));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(8).setNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(9).setNeighbour(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(11));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(10).setNeighbour(map.getField(15));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbour(map.getField(12));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(11).setNeighbour(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbour(map.getField(13));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(12).setNeighbour(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbour(map.getField(14));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(13).setNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(14).setNeighbour(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbour(map.getField(16));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(15).setNeighbour(map.getField(20));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).setNeighbour(map.getField(17));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(16).setNeighbour(map.getField(21));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).setNeighbour(map.getField(18));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(17).setNeighbour(map.getField(22));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).setNeighbour(map.getField(19));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(18).setNeighbour(map.getField(23));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(19).setNeighbour(map.getField(24));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(20).setNeighbour(map.getField(21));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(21).setNeighbour(map.getField(22));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(22).setNeighbour(map.getField(23));
		//ket mezo kozott beallit egy szomszedsagot. Ezt eleg egyiranyba megtenni, mivel az addNeighbour() fuggveny lekezeli az oda-vissza kapcsolast...
		map.getField(23).setNeighbour(map.getField(24));


		
		for(int i = 0; i < entity.size(); i++)
			map.getField(i % map.getSize()).accept(entity.get(i));
		
	}
	
}


