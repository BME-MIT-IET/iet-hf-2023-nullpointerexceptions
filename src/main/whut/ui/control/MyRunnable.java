package whut.ui.control;

import whut.agent.Agens;
import whut.field.*;
import whut.genetic_code.GeneticCode;
import whut.item.Item;
import whut.material.Aminosav;
import whut.material.Material;
import whut.material.Nukleotid;
import whut.material.Packet;
import whut.player.AgensUsable;
import whut.player.Game;
import whut.player.Virologus;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

//Ez a f� oszt�ly ez kezeli a tesztesetek �s a j�t�kok ind�t�s�t
public class MyRunnable {
	private static int thingsLeft = 2;
	public static void setLeft(int a){thingsLeft = a;}
	public static void incrLeft(){thingsLeft++;}
	public static void decrLeft(){thingsLeft--;}

	public static int getLeft(){return thingsLeft;}

	private static JFrame frame;
	private static boolean started = true;
	private static Game game;
	private static Scanner scanner;
	private static Virologus selectedVirologus;
	private static boolean touched = false;
	private static String badParameter = "Bad parameter!";
	private static String itemek = "Itemek: ";
	private static String forget = "forget";
	private static String protection = "protection";
	private static Virologus currentVirologus;
	private static boolean testfromFile = false;



	private MyRunnable() {}
	
	public static void setTouched(boolean t) {
		touched = t;
	}
	
	public static boolean getTouched() {
		return touched;
	}
	
	
	public static Virologus getSelected() {
		return selectedVirologus;
	}
	
	public static void setSelected(Virologus v) {
		selectedVirologus=v;
	}
	
	public static void setStart(boolean mire) {
		started = mire;
	}
	
	public static void setFrame(JFrame f) {
		frame = f;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static boolean getStart() {
		return started;
	}
	
	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void setCurrentVirologus(Virologus v) {
		currentVirologus=v;
	}
	
	public static Game getGame() {return game;}
	
	public static String[] read() {
        if(!testfromFile) {
            Scanner in = new Scanner(System.in);
            String read= in.nextLine();
            return read.split(" ");
        } else {
            if(scanner.hasNextLine()) {
                String read= scanner.nextLine();
                return read.split(" ");
            } else {
                scanner.close();
                testfromFile = false;
                String[] eof = new String[1];
                eof[0]="newtest";
                return eof;
            }

        }

    }
	
	//A stealitem bemenetet kezeli le
	public static void stealitem(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v==null) {
					log(badParameter);
					return;
				}
				Item it = v.getItem(input[2]);
				if(it!=null && currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.stealItem(v, it);
				} else
					log("This item cant be found at v"+ getVirologusSzam(v));
			}
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A stealmaterial bemenetet kezeli le
	public static void stealmaterial(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v==null) {
					log(badParameter);
					return;
				}
				Material mat = v.getPacket().getMaterial(input[2]);
				if(mat != null && currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.stealMaterial(v, mat);
				} else
					log("This materila cant be found at v"+ getVirologusSzam(v));
				
			}
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A useagens bemenetet kezeli le
	public static void useagens(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				Agens a = currentVirologus.getAgens(input[2]);
				if(v!=null && a != null && currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.useAgens(v,a);
				}
				
			}	
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A move bemenetet kezeli le
	public static void moveTo(String hova) {
		try {
			boolean moved = false;
			String ch = hova.substring(1);
			int melyik = Integer.parseInt(ch);
			Field f = game.getMap().getFields().get(melyik-1);
			if(hova.charAt(0) == 'f') {
				List<Field> list = currentVirologus.getField().getNeighbourhood();
				for(Field ff : list)
					if(ff == f) {
						currentVirologus.move(f);
						moved = true;
						log("v"+getVirologusSzam(currentVirologus)+" moved");
					}
			}
			if(!moved)
				log(badParameter);
		} catch(NumberFormatException e) {
			log(badParameter);
		}
}
	
	//Visszaagja a param�terk�nt kapott virol�gus sz�m�t
	public static int getVirologusSzam(Virologus v) {
		for(int i = 0; i < game.getEntity().size(); i++)
			if(game.getEntityAt(i) == v)
				return i+1;
		return -1;
	}
	
	public static int getFieldSzam(Field f) {
		for(int i = 0; i < game.getMap().getSize(); i++)
			if(game.getMap().getField(i) == f)
				return i+1;
		return -1;
	}

	//A kill bemenetet kezeli le
	public static void kill(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v!=null&&currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.kill(v);
				}
			}
		}catch(NumberFormatException ex) {
			//nem kell
		}
	}
	
	//A learn bemenetet kezeli le
	public static void learn() {
		if(null != currentVirologus.getField().codeHere()) {
			currentVirologus.learnGeneticCode(currentVirologus.getField().codeHere());
			log("v"+getVirologusSzam(currentVirologus)+" learned "+currentVirologus.getField().codeHere().toString());
		}
		else
			log(badParameter);
	}
	
	//A createfield bemenetet kezeli le
	public static void createField(String[] input) {
		if (input.length == 1) {
			game.getMap().addField(new Field());
		}
		else {
			switch(input[1]) {
				case "shelter":
					game.getMap().addField(new Shelter());
					break;
				case "storage":
					game.getMap().addField(new Storage());
					break;
				case "lab":
					game.getMap().addField(new Lab());
					break;
				case "evillab":
					game.getMap().addField(new EvilLab());
					break;
				default:
					log(badParameter);
			}
		}
	}
	
	////A setneighbour bemenetet kezeli le
	public static void setNeighbour(String[] input) {
		String sub = input[1].substring(1);
		String sub2 = input[2].substring(1);
		try {
			int number = Integer.parseInt(sub);
			int number2 = Integer.parseInt(sub2);
			if(input[1].charAt(0)=='f') {
				Field f1 = game.getMap().getField(number-1);
				Field f2 = game.getMap().getField(number2-1);
				f1.setNeighbour(f2);
				f2.setNeighbour(f1);
				log(input[1]+" is now connected to "+input[2]+ "!");
			}
		} catch (NumberFormatException ex) {
			//nem kell
		}
	}
	
	//A placevirologus bemenetet kezeli le
	public static void placeVir(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='f') {
				Virologus v = new Virologus();
				game.addPlayer(v);
				game.getMap().getField(number-1).accept(v);
				log("A Virologus has been added to "+input[1]+"!");
			}
			
		} catch (NumberFormatException ex) {
			//nem kell
		}
	}
	
	//A info bemenetet kezeli le
	public static void getInfo() {
		logMaterialsOnVirologist();
		logItemsOnVirologist();
		logGeneticCodesOnVirologist();
		logAgensOnVirologist();
		logEffectiveAgensOnVirologist();
	}
	
	private static void logItemsOnVirologist() {
		String kimenet = itemek;
		for(Item it : currentVirologus.getItemHave()) {
			if(it.check("axe"))
				kimenet = kimenet.concat("axe, ");
			else if(it.check("cloak"))
				kimenet = kimenet.concat("cloak, ");
			else if(it.check("glove"))
				kimenet = kimenet.concat("glove, ");
			else
				kimenet = kimenet.concat("sack, ");
		}
		log(kimenet);
	}
	
	private static void logMaterialsOnVirologist() {
		String kimenet = "Anygok: ";
		for(Material mat : currentVirologus.getPacket().getMaterials())
			kimenet = kimenet.concat(mat.getType()+", ");
		
		log(kimenet);
	}
	
	private static void logGeneticCodesOnVirologist () {
		String kimenet = "Genetik kodok: ";
		for(GeneticCode gc : currentVirologus.getGeneticCodeHave()) {
			if(gc.check(protection))
				kimenet = kimenet.concat("protectionCode, ");
			else if(gc.check(forget))
				kimenet = kimenet.concat("forgetCode, ");
			else if(gc.check("stun"))
				kimenet = kimenet.concat("stunCode, ");
			else 
				kimenet = kimenet.concat("vitusdanceCode, ");
		}
		log(kimenet);
	}
	
	private static void logAgensOnVirologist(){
		String kimenet ="Agensek: ";
		for(Agens a : currentVirologus.getAgensHave()) {
			if(a.check(protection))
				kimenet = kimenet.concat("protection, ");
			else if(a.check(forget))
				kimenet = kimenet.concat("forget, ");
			else if(a.check("stun"))
				kimenet = kimenet.concat("stun, ");
			else if(a.check("vitusdance"))
				kimenet = kimenet.concat("vitusdance, ");
			else
				kimenet = kimenet.concat("beardance, ");
		}
		log(kimenet);
	}
	
	private static void logEffectiveAgensOnVirologist() {
		String kimenet = "Hato agensek: ";
		for(Agens a : currentVirologus.getAgensOnMe()) {
			if(a.check(protection))
				kimenet = kimenet.concat("protection, ");
			else if(a.check(forget))
				kimenet = kimenet.concat("forget, ");
			else if(a.check("stun"))
				kimenet = kimenet.concat("stun, ");
			else if(a.check("vitusdance"))
				kimenet = kimenet.concat("vitusdance, ");
			else 
				kimenet = kimenet.concat("beardance, ");
		}
		log(kimenet);
	}
	
	//A pickup bemenetet kezeli le
	public static void pickup(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getField().getItem(sub);
		if(it!=null) {
			currentVirologus.pickUpItem(it);
			log(input[1] + " is added to v"+ getVirologusSzam(currentVirologus) +"'s inventory!" );
		}
	}
	
	//A collect bemenetet kezeli le
	public static void collect(String[] input)
	{
		boolean can = false;
		Material mm;
		if(input[1].equals("amino"))
			mm = new Aminosav();
		else
			mm = new Nukleotid();
		
		if(currentVirologus.getField().getPacket() != null) {
			for(int i = 0; i < currentVirologus.getField().getPacket().getMaterials().size(); i++) {
				if(currentVirologus.getField().getPacket().getMaterials().get(i).isSame(mm)) {
					can = true;
					currentVirologus.increaseMaterial(currentVirologus.getField().getPacket(), currentVirologus.getField().getPacket().getMaterials().get(i));
				}
			}
		} else
			log("Cant collect "+input[1]);
			
		if(can)
			log("v" + getVirologusSzam(currentVirologus) + " collected "+ input[1]);
	}
	
	//A leave bemenetet kezeli le
	public static void leave(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getItem(sub);
		if(it!= null)
		{
			currentVirologus.leaveItem(it);
			log(input[1] + " has been removed from v"+ getVirologusSzam(currentVirologus) );
		}
		else
			MyRunnable.log("Bad parameter!");
	}
	
	//A touch bemenetet kezeli le
	public static void touch() {
		Field f = currentVirologus.getField();
		log("Field adatok:");
		if (f.codeHere() != null)
			log("genetikai kodok: "+f.codeHere());
		logItemsOnField(f);
		logMaterialsOnField(f);
		
		for (AgensUsable a : f.getVirologusok()) {
			Virologus v = (Virologus) a;
			log("v"+ getVirologusSzam(v));
			String vAnyagok = "Anyagok: ";
			List<Material> vml = v.getPacket().getMaterials();
			if (vml != null){
				for (Material m : vml)
					vAnyagok = vAnyagok.concat(m.toString()+" ");
			}
			log(vAnyagok);
			String vItemek = itemek;
			List<Item> vil = v.getItemHave();
			for(Item i : vil)
				vItemek = vItemek.concat(i.toString()+" ");
			log(vItemek);
		}
	}
	
	private static void logItemsOnField(Field f) {
		String item;
		List<Item> il = f.getItems();
		if(il.isEmpty()){
			log(itemek);
			return;
		}
		item = itemek;
		for (Item i : il) {
			item = item.concat(i.toString()+" ");
		}
		log(item);
	}
	
	private static void logMaterialsOnField(Field f) {
		String anyagok = "Anyagok: ";
		Packet p = f.getPacket();
		if (p == null)
			anyagok = anyagok.concat("-");
		else {
			List<Material> ml = p.getMaterials();
			if (ml == null)
				anyagok = anyagok.concat("-");
			else {
				for (Material m : ml) {
					anyagok = anyagok.concat(m.toString()+" ");
				}
			}
		}
		log(anyagok);
	}
	
	
	public static void getInputFirstAct(String[] readed) {
		if(getLeft()<=0)
			return;
		decrLeft();
		switch(readed[0]) {
			case "idle":
				incrLeft();
				break;
			case "info":
				getInfo();
				incrLeft();
				break;
			case "touch":
				touch();
				incrLeft();
				currentVirologus.getField().touching(currentVirologus);
				break;
			case "move":
				if (readed.length == 2)
					moveTo(readed[1]);
				else 
					log(badParameter);
				break;
			case "create":
				create(readed);
				break;
			case "finishturn" : 
				getNextPlayer();
				getInfo();
				playerCanMoveTo();
				break;
			case "save":
				save(readed);
				break;
			
			default : 
				log(badParameter);
				break;
		}
		game.myNotify();
	}
	
	private static void create(String[] readed) {
		int elozo = currentVirologus.getAgensHave().size() ;
		if (readed.length == 2)
			currentVirologus.createAgens(readed[1]);
		else 
			log(badParameter);
		if(currentVirologus.getAgensHave().size() == elozo)
			log("");
		else
			log("v"+getVirologusSzam(currentVirologus)+" created a "+readed[1]);
	}
	
	private static void getNextPlayer() {
		int ii;
		for(ii = 0; currentVirologus != game.getEntity().get(ii) && ii<game.getEntity().size(); ii++);
		if(ii < game.getEntity().size()) {
			if(ii+1 != game.getEntity().size()) {
				currentVirologus = (Virologus)game.getEntity().get(ii+1);
			}
			else {
				currentVirologus = (Virologus)game.getEntity().get(0);
			}
		}
		if (!currentVirologus.roundDesc()) {
			setLeft(0);
		}
			
		log("player in row: v" + getVirologusSzam(currentVirologus));
	}
	
	private static void playerCanMoveTo() {
		List<Field> n = currentVirologus.getField().getNeighbourhood();
		String kimenet = "Player can move to: ";
		for(Field f : n)
			for(int i = 0; i < game.getMap().getSize(); i++)
				if(f == game.getMap().getField(i))
					kimenet = kimenet.concat("f"+(i+1)+", ");
		log(kimenet);
	}
	
	private static void save(String[] readed) {
		incrLeft();
		if (readed.length == 2) {
			ObjectOutputStream out;
			try {
				if(readed[1].contains(".")) {
					log(badParameter);
				} else {
					out = new ObjectOutputStream(new FileOutputStream(readed[1] + ".txt"));
					out.writeObject(game);
					log("v"+getVirologusSzam(currentVirologus)+" saved the game!");
					out.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				log(badParameter);
			}
		}
		else 
			log(badParameter);
	}
	
	public static void getInputAfterTouch(String[] readed) {
		int justinfo = 1;
		if(getLeft()<=0)
			return;
		decrLeft();
		while(justinfo > 0) {

			switch(readed[0]) {
				case "idle":
					incrLeft();
					break;
				case "create":
					create(readed);
					break;
				case "info":
					incrLeft();
					justinfo++;
					getInfo();
					break;
				case "stealitem":
					if (readed.length == 3) {
						stealitem(readed);     //prototipus
					}
					else {
						log(badParameter);
					}
					break;
				case "stealmaterial":
					if (readed.length == 3) {
						stealmaterial(readed);
					}
					else 
						log(badParameter);
					break;
				case "kill":
					if (readed.length == 2) {
						kill(readed);
					}
					else 
						log(badParameter);
					break;
				case "useagens":
					if (readed.length == 3) {
						useagens(readed);
					}
					else 
						log(badParameter);
					break;
				
				case "learn":
					if (readed.length == 1) {
						learn();
						currentVirologus.step();
					}
					else 
						log(badParameter);
					break;
				case "collect":
					if (readed.length == 2) {
						collect(readed);
					}
					else 
						log(badParameter);
					break;
				case "pickup":
					if (readed.length == 2) {
						pickup(readed);
					}
					else
						log(badParameter);
					break;
				case "leave":
					if (readed.length == 2) {
						leave(readed);
					}
					else 
						log(badParameter);
					break;
				case "finishturn" :
					justinfo = 0;
					startInfo();
					break;
				default : 
					log(badParameter);
					break;
			}
			initForNextRound();
			justinfo--;
		}
	}
	
	private static void initForNextRound() {
		selectedVirologus = null;
		touched = false;
		game.myNotify();
	}
		
	public static void startInfo() {
		log("player in row: v" + getVirologusSzam(currentVirologus));
		getInfo();
		List<Field> n = currentVirologus.getField().getNeighbourhood();
		String kimenet = "Player can move to: ";
		for(Field f : n)
			for(int i = 0; i < game.getMap().getSize(); i++)
				if(f == game.getMap().getField(i))
					kimenet = kimenet.concat("f"+(i+1)+", ");
		log(kimenet);
	}
	
	public static void setGame(Game gg) {
		game = gg;
		currentVirologus = (Virologus)game.getEntityAt(0);
	}
	
	public static Virologus getCurrentVir() {
		return currentVirologus;
	}


	public static void addLogo(JPanel panel){
		panel.setBackground(Color.CYAN);
		ImageIcon i = new ImageIcon("iitlogo.png");
		JLabel l = new JLabel();
		l.setIcon(i);
		panel.add(l);
	}
}
