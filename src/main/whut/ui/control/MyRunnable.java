package whut.ui.control;

import whut.agent.Agent;
import whut.field.*;
import whut.genetic_code.GeneticCode;
import whut.item.Item;
import whut.material.AminoAcid;
import whut.material.Material;
import whut.material.Nucleotide;
import whut.material.Packet;
import whut.player.AgentUsable;
import whut.player.Game;
import whut.player.Virologist;

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
	public static void increaseLeft(){thingsLeft++;}
	public static void decreaseLeft(){thingsLeft--;}

	public static int getLeft(){return thingsLeft;}

	private static JFrame frame;
	private static boolean isStarted = true;
	private static Game game;
	private static Scanner scanner;
	private static Virologist selectedVirologist;
	private static boolean isTouched = false;
	private static String badParameter = "Bad parameter!";
	private static String itemsString = "Items: ";
	private static String forgetString = "Forget";
	private static String protectionString = "Protection";
	private static Virologist currentVirologist;
	private static boolean isTestFromFile = false;



	private MyRunnable() {}
	
	public static void setIsTouched(boolean value) {
		isTouched = value;
	}
	
	public static boolean getIsTouched() {
		return isTouched;
	}
	
	
	public static Virologist getSelected() {
		return selectedVirologist;
	}
	
	public static void setSelected(Virologist virologist) {
		selectedVirologist =virologist;
	}
	
	public static void setStart(boolean value) {
		isStarted = value;
	}
	
	public static void setFrame(JFrame frame) {
		MyRunnable.frame = frame;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static boolean getStart() {
		return isStarted;
	}
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void setCurrentVirologus(Virologist v) {
		currentVirologist =v;
	}
	
	public static Game getGame() {return game;}
	
	public static String[] read() {
        if(!isTestFromFile) {
            Scanner sc = new Scanner(System.in);
            String read= sc.nextLine();
            return read.split(" ");
        } else {
            if(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                return line.split(" ");
            } else {
                scanner.close();
                isTestFromFile = false;
                String[] eof = new String[1];
                eof[0]="newtest";
                return eof;
            }

        }

    }
	
	//A stealitem bemenetet kezeli le
	public static void stealItem(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologist v = (Virologist)(game.getEntityAt(number-1));
				if(v==null) {
					log(badParameter);
					return;
				}
				Item it = v.getItem(input[2]);
				if(it!=null && currentVirologist.getField().getAgentUsableList().contains(v)) {
					currentVirologist.stealItem(v, it);
				} else
					log("This item cant be found at v"+ getNumberOfVirologist(v));
			}
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A stealmaterial bemenetet kezeli le
	public static void stealMaterial(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologist virologist = (Virologist)(game.getEntityAt(number-1));
				if(virologist==null) {
					log(badParameter);
					return;
				}
				Material mat = virologist.getPacket().getMaterial(input[2]);
				if(mat != null && currentVirologist.getField().getAgentUsableList().contains(virologist)) {
					currentVirologist.stealMaterial(virologist, mat);
				} else
					log("This materila cant be found at v"+ getNumberOfVirologist(virologist));
				
			}
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A useagens bemenetet kezeli le
	public static void useAgent(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologist virologist = (Virologist)(game.getEntityAt(number-1));
				Agent a = currentVirologist.getAgent(input[2]);
				if(virologist!=null && a != null && currentVirologist.getField().getAgentUsableList().contains(virologist)) {
					currentVirologist.useAgent(virologist,a);
				}
				
			}	
		}catch(NumberFormatException ex) {
			log(badParameter);
		}
	}
	
	//A move bemenetet kezeli le
	public static void moveTo(String destination) {
		try {
			boolean moved = false;
			String ch = destination.substring(1);
			int coord = Integer.parseInt(ch);
			Field f = game.getMap().getFields().get(coord-1);
			if(destination.charAt(0) == 'f') {
				List<Field> list = currentVirologist.getField().getNeighborhood();
				for(Field ff : list)
					if(ff == f) {
						currentVirologist.move(f);
						moved = true;
						log("v"+ getNumberOfVirologist(currentVirologist)+" moved");
					}
			}
			if(!moved)
				log(badParameter);
		} catch(NumberFormatException e) {
			log(badParameter);
		}
}
	
	//Visszaagja a param�terk�nt kapott virol�gus sz�m�t
	public static int getNumberOfVirologist(Virologist virologist) {
		for(int i = 0; i < game.getEntities().size(); i++)
			if(game.getEntityAt(i) == virologist)
				return i+1;
		return -1;
	}
	
	public static int getNumberOfField(Field field) {
		for(int i = 0; i < game.getMap().getSize(); i++)
			if(game.getMap().getField(i) == field)
				return i+1;
		return -1;
	}

	//A kill bemenetet kezeli le
	public static void kill(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologist v = (Virologist)(game.getEntityAt(number-1));
				if(v!=null&& currentVirologist.getField().getAgentUsableList().contains(v)) {
					currentVirologist.kill(v);
				}
			}
		}catch(NumberFormatException ex) {
			//nem kell
		}
	}
	
	//A learn bemenetet kezeli le
	public static void learn() {
		if(null != currentVirologist.getField().getGeneticCode()) {
			currentVirologist.learnGeneticCode(currentVirologist.getField().getGeneticCode());
			log("v"+ getNumberOfVirologist(currentVirologist)+" learned "+ currentVirologist.getField().getGeneticCode().toString());
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
				f1.setNeighbor(f2);
				f2.setNeighbor(f1);
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
				Virologist v = new Virologist();
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
		String kimenet = itemsString;
		for(Item it : currentVirologist.getItems()) {
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
		for(Material mat : currentVirologist.getPacket().getMaterials())
			kimenet = kimenet.concat(mat.getType()+", ");
		
		log(kimenet);
	}
	
	private static void logGeneticCodesOnVirologist () {
		String kimenet = "Genetik kodok: ";
		for(GeneticCode gc : currentVirologist.getGeneticCodes()) {
			if(gc.check(protectionString))
				kimenet = kimenet.concat("protectionCode, ");
			else if(gc.check(forgetString))
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
		for(Agent a : currentVirologist.getAgents()) {
			if(a.check(protectionString))
				kimenet = kimenet.concat("protection, ");
			else if(a.check(forgetString))
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
		for(Agent a : currentVirologist.getAppliedAgents()) {
			if(a.check(protectionString))
				kimenet = kimenet.concat("protection, ");
			else if(a.check(forgetString))
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
		Item it = currentVirologist.getField().getItem(sub);
		if(it!=null) {
			currentVirologist.pickUpItem(it);
			log(input[1] + " is added to v"+ getNumberOfVirologist(currentVirologist) +"'s inventory!" );
		}
	}
	
	//A collect bemenetet kezeli le
	public static void collect(String[] input)
	{
		boolean can = false;
		Material mm;
		if(input[1].equals("amino"))
			mm = new AminoAcid();
		else
			mm = new Nucleotide();
		
		if(currentVirologist.getField().getPacket() != null) {
			for(int i = 0; i < currentVirologist.getField().getPacket().getMaterials().size(); i++) {
				if(currentVirologist.getField().getPacket().getMaterials().get(i).sameAs(mm)) {
					can = true;
					currentVirologist.increaseMaterial(currentVirologist.getField().getPacket(), currentVirologist.getField().getPacket().getMaterials().get(i));
				}
			}
		} else
			log("Cant collect "+input[1]);
			
		if(can)
			log("v" + getNumberOfVirologist(currentVirologist) + " collected "+ input[1]);
	}
	
	//A leave bemenetet kezeli le
	public static void leave(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologist.getItem(sub);
		if(it!= null)
		{
			currentVirologist.leaveItem(it);
			log(input[1] + " has been removed from v"+ getNumberOfVirologist(currentVirologist) );
		}
		else
			MyRunnable.log("Bad parameter!");
	}
	
	//A touch bemenetet kezeli le
	public static void touch() {
		Field f = currentVirologist.getField();
		log("Field adatok:");
		if (f.getGeneticCode() != null)
			log("genetikai kodok: "+f.getGeneticCode());
		logItemsOnField(f);
		logMaterialsOnField(f);
		
		for (AgentUsable a : f.getAgentUsableList()) {
			Virologist v = (Virologist) a;
			log("v"+ getNumberOfVirologist(v));
			String vAnyagok = "Anyagok: ";
			List<Material> vml = v.getPacket().getMaterials();
			if (vml != null){
				for (Material m : vml)
					vAnyagok = vAnyagok.concat(m.toString()+" ");
			}
			log(vAnyagok);
			String vItemek = itemsString;
			List<Item> vil = v.getItems();
			for(Item i : vil)
				vItemek = vItemek.concat(i.toString()+" ");
			log(vItemek);
		}
	}
	
	private static void logItemsOnField(Field f) {
		String item;
		List<Item> il = f.getItems();
		if(il.isEmpty()){
			log(itemsString);
			return;
		}
		item = itemsString;
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
		decreaseLeft();
		switch(readed[0]) {
			case "idle":
				increaseLeft();
				break;
			case "info":
				getInfo();
				increaseLeft();
				break;
			case "touch":
				touch();
				increaseLeft();
				currentVirologist.getField().touching(currentVirologist);
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
		game.notifyObservers();
	}
	
	private static void create(String[] readed) {
		int elozo = currentVirologist.getAgents().size() ;
		if (readed.length == 2)
			currentVirologist.createAgent(readed[1]);
		else 
			log(badParameter);
		if(currentVirologist.getAgents().size() == elozo)
			log("");
		else
			log("v"+ getNumberOfVirologist(currentVirologist)+" created a "+readed[1]);
	}
	
	private static void getNextPlayer() {
		int ii;
		for(ii = 0; currentVirologist != game.getEntities().get(ii) && ii<game.getEntities().size(); ii++);
		if(ii < game.getEntities().size()) {
			if(ii+1 != game.getEntities().size()) {
				currentVirologist = (Virologist)game.getEntities().get(ii+1);
			}
			else {
				currentVirologist = (Virologist)game.getEntities().get(0);
			}
		}
		if (!currentVirologist.decreaseRound()) {
			setLeft(0);
		}
			
		log("player in row: v" + getNumberOfVirologist(currentVirologist));
	}
	
	private static void playerCanMoveTo() {
		List<Field> n = currentVirologist.getField().getNeighborhood();
		String kimenet = "Player can move to: ";
		for(Field f : n)
			for(int i = 0; i < game.getMap().getSize(); i++)
				if(f == game.getMap().getField(i))
					kimenet = kimenet.concat("f"+(i+1)+", ");
		log(kimenet);
	}
	
	private static void save(String[] readed) {
		increaseLeft();
		if (readed.length == 2) {
			ObjectOutputStream out;
			try {
				if(readed[1].contains(".")) {
					log(badParameter);
				} else {
					out = new ObjectOutputStream(new FileOutputStream(readed[1] + ".txt"));
					out.writeObject(game);
					log("v"+ getNumberOfVirologist(currentVirologist)+" saved the game!");
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
		decreaseLeft();
		while(justinfo > 0) {

			switch(readed[0]) {
				case "idle":
					increaseLeft();
					break;
				case "create":
					create(readed);
					break;
				case "info":
					increaseLeft();
					justinfo++;
					getInfo();
					break;
				case "stealitem":
					if (readed.length == 3) {
						stealItem(readed);     //prototipus
					}
					else {
						log(badParameter);
					}
					break;
				case "stealmaterial":
					if (readed.length == 3) {
						stealMaterial(readed);
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
						useAgent(readed);
					}
					else 
						log(badParameter);
					break;
				
				case "learn":
					if (readed.length == 1) {
						learn();
						currentVirologist.step();
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
		selectedVirologist = null;
		isTouched = false;
		game.notifyObservers();
	}
		
	public static void startInfo() {
		log("player in row: v" + getNumberOfVirologist(currentVirologist));
		getInfo();
		List<Field> n = currentVirologist.getField().getNeighborhood();
		String kimenet = "Player can move to: ";
		for(Field f : n)
			for(int i = 0; i < game.getMap().getSize(); i++)
				if(f == game.getMap().getField(i))
					kimenet = kimenet.concat("f"+(i+1)+", ");
		log(kimenet);
	}
	
	public static void setGame(Game gg) {
		game = gg;
		currentVirologist = (Virologist)game.getEntityAt(0);
	}
	
	public static Virologist getCurrentVir() {
		return currentVirologist;
	}


	public static void addLogo(JPanel panel){
		panel.setBackground(Color.CYAN);
		ImageIcon i = new ImageIcon("iitlogo.png");
		JLabel l = new JLabel();
		l.setIcon(i);
		panel.add(l);
	}
}
