package whut;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
	private static ArrayList<String> logFile;
	private static boolean started = true;
	private static Game game;
	private static Scanner scanner;
	private static Virologus selectedVirologus;
	private static boolean touched = false;
	
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
		//System.out.println(s);
	}
	
	/*public void Main() {
		logFile = new ArrayList<String>();
		//game = new Game();
		if(!started) {
			currentVirologus = (Virologus)game.getEntity().get(0);
			log("player in row: v" + getVirologusSzam(currentVirologus));
			getInfo();
			ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
			String kimenet = "Player can move to: ";
			for(Field f : n)
				for(int i = 0; i < game.getMap().getSize(); i++)
					if(f == game.getMap().getField(i))
						kimenet = kimenet.concat("f"+(i+1)+", ");
			log(kimenet);
			getInputFirstAct();
		}
		else
			start();
	}*/
	
	private static Virologus currentVirologus;
	public static void setCurrentVirologus(Virologus v) {
		currentVirologus=v;
	}
	
	public static Game getGame() {return game;}
	
	public static String[] read() {
        if(!testfromFile) {
            Scanner in = new Scanner(System.in);
            String read= in.nextLine();
            String[] readed = read.split(" ");
            return readed;
        } else {
            if(scanner.hasNextLine()) {
                String read= scanner.nextLine();
                String[] readed = read.split(" ");
                return readed;
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
					log("Bad parameter!");
					return;
				}
				Item it = v.getItem(input[2]);
				if(it!=null && currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.stealItem(v, it);
				} else
					log("This item cant be found at v"+ getVirologusSzam(v));
			}
		}catch(NumberFormatException ex) {
			log("Bad parameter!");
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
					log("Bad parameter!");
					return;
				}
				Material mat = v.getPacket().getMaterial(input[2]);
				if(mat != null && currentVirologus.getField().getVirologusok().contains(v)) {
					currentVirologus.stealMaterial(v, mat);
				} else
					log("This materila cant be found at v"+ getVirologusSzam(v));
				
			}
		}catch(NumberFormatException ex) {
			log("Bad parameter!");
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
			log("Bad parameter!");
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
				ArrayList<Field> list = currentVirologus.getField().getNeighbourhood();
				for(Field ff : list)
					if(ff == f) {
						currentVirologus.move(f);
						moved = true;
						log("v"+getVirologusSzam(currentVirologus)+" moved");
					}
			}
			if(!moved)
				log("Bad parameter!");
		} catch(NumberFormatException e) {
			log("Bad parameter!");
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
			
		}
	}
	
	//A learn bemenetet kezeli le
	public static void learn() {
		if(null != currentVirologus.getField().codeHere()) {
			currentVirologus.learnGeneticCode(currentVirologus.getField().codeHere());
			log("v"+getVirologusSzam(currentVirologus)+" learned "+currentVirologus.getField().codeHere().toString());
		}
		else
			log("Bad parameter!");
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
					log("Bad parameter!");
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
			
		}
	}
	
	//A info bemenetet kezeli le
	public static void getInfo() {
		String kimenet = "Anygok: ";
		for(Material mat : currentVirologus.getPacket().getMaterials())
			kimenet = kimenet.concat(mat.getType()+", ");
		
		log(kimenet);

		kimenet = "Itemek: ";
		for(Item it : currentVirologus.getItemHave()) {
			if(it.Check("axe"))
				kimenet = kimenet.concat("axe, ");
			else if(it.Check("cloak"))
				kimenet = kimenet.concat("cloak, ");
			else if(it.Check("glove"))
				kimenet = kimenet.concat("glove, ");
			else
				kimenet = kimenet.concat("sack, ");
		}
		log(kimenet);
		
		kimenet = "Genetik kodok: ";
		for(GeneticCode gc : currentVirologus.getGeneticCodeHave()) {
			if(gc.Check("protection"))
				kimenet = kimenet.concat("protectionCode, ");
			else if(gc.Check("forget"))
				kimenet = kimenet.concat("forgetCode, ");
			else if(gc.Check("stun"))
				kimenet = kimenet.concat("stunCode, ");
			else 
				kimenet = kimenet.concat("vitusdanceCode, ");
		}
		log(kimenet);
		
		kimenet ="Agensek: ";
		for(Agens a : currentVirologus.getAgensHave()) {
			if(a.Check("protection"))
				kimenet = kimenet.concat("protection, ");
			else if(a.Check("forget"))
				kimenet = kimenet.concat("forget, ");
			else if(a.Check("stun"))
				kimenet = kimenet.concat("stun, ");
			else if(a.Check("vitusdance"))
				kimenet = kimenet.concat("vitusdance, ");
			else
				kimenet = kimenet.concat("beardance, ");
		}
		log(kimenet);
		
		kimenet = "Hato agensek: ";
		for(Agens a : currentVirologus.getAgensOnMe()) {
			if(a.Check("protection"))
				kimenet = kimenet.concat("protection, ");
			else if(a.Check("forget"))
				kimenet = kimenet.concat("forget, ");
			else if(a.Check("stun"))
				kimenet = kimenet.concat("stun, ");
			else if(a.Check("vitusdance"))
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
	
	//A add bemenetet kezeli le
	private static void addSomething(String[] readed) {
		int szam = Character.getNumericValue(readed[2].charAt(1)) - 1;  //virologus jelzo utani azonosito(hanyadik virologusra hasznaljuk a dolgot);
		//az add utani parancs alapjan folytatodik
		switch(readed[1]) {
		case "cloak":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Cloak());
				log("A cloak has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Cloak());
				log("A cloak has been added to "+readed[2]);
			}
		break;
		case "glove":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Glove());
				log("A glove has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Glove());
				log("A glove has been added to "+readed[2]);
			}
		break;
		case "sack":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Sack());
				log("A sack has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Sack());
				log("A sack has been added to "+readed[2]);
			}
		break;
		case "axe":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Axe());
				log("A axe has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Axe());
				log("A axe has been added to "+readed[2]);
			}
		break;
		case "stuncode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new StunCode()); //setGeneticCodeot a Fieldbe is berakni ott nem csinal semmit
				log("A stuncode has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new StunCode());
				log("A stuncode has been added to "+readed[2]);
			}
		break;
		case "vitusdancecode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new VitusdanceCode());
				log("A vitusdancecode has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new VitusdanceCode());
				log("A vitusdancecode has been added to "+readed[2]);
			}
		break;
		case "forgetcode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new ForgetCode());
				log("A forgetcode has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new ForgetCode());
				log("A forgetcode has been added to "+readed[2]);
			}
		break;
		case "protectioncode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new ProtectionCode());
				log("A protectioncode has been added to "+readed[2]);
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new ProtectionCode());
				log("A protectioncode has been added to "+readed[2]);
			}
		break;
		
		case "stun":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Stun());
				log("A stun has been added to "+readed[2]);
			}
		break;
		case "vitusdance":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Vitusdance());
				log("A vitusdance has been added to "+readed[2]);
			}
		break;
		case "protection":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Protection());
				log("A protection has been added to "+readed[2]);
			}
		break;
		case "forget":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Forget());
				log("A forget has been added to "+readed[2]);
			}
		break;
		case "aminosav":					
			if(readed[2].charAt(0) == 'f') { 
				if(game.getMap().getField(szam).getPacket() != null) {
					game.getMap().getField(szam).getPacket().addMaterial(new Aminosav());  //fieldnek egy addMaterialt kell letrehozni.
					log("A aminosav has been added to "+readed[2]);
				}
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(new Aminosav());
				log("A aminosav has been added to "+readed[2]);
			}
		break;
		case "nukleotid":
			if(readed[2].charAt(0) == 'f') {   
				if(game.getMap().getField(szam).getPacket() != null) {
					game.getMap().getField(szam).getPacket().addMaterial(new Nukleotid());  //fieldnek egy addMaterialt kell letrehozni.
					log("A nukleotid has been added to "+readed[2]);
				}
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(new Nukleotid());
				log("A nukleotid has been added to "+readed[2]);
			}
		break;
		case "packet":
			Nukleotid n = new Nukleotid();
			Aminosav a = new Aminosav();
			n.setValue(500);
			a.setValue(500);
			if(readed[2].charAt(0) == 'f') {   
				if(game.getMap().getField(szam).getPacket() != null) {
					game.getMap().getField(szam).getPacket().addMaterial(a);
					game.getMap().getField(szam).getPacket().addMaterial(n);
					log("A packet has been added to "+readed[2]);
				}
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(a);
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(n);
				log("A packet has been added to "+readed[2]);
			}
			break;
		default:
			log("Bad parameter!");
			break;
		}
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
		String itemek = "Itemek: ";
		ArrayList<Item> il = f.getItems();
		if (il == null) 
			itemek = itemek.concat("-");
		else {
			for (Item i : il) {
				itemek = itemek.concat(i.toString()+" ");
			}
		}
		log(itemek);
		String anyagok = "Anyagok: ";
		Packet p = f.getPacket();
		if (p == null)
			anyagok = anyagok.concat("-");
		else {
			ArrayList<Material> ml = p.getMaterials();
			if (ml == null)
				anyagok = anyagok.concat("-");
			else {
				for (Material m : ml) {
					anyagok = anyagok.concat(m.toString()+" ");
				}
			}
		}
		log(anyagok);
		
		for (AgensUsable a : f.getVirologusok()) {
			Virologus v = (Virologus) a;
			log("v"+ getVirologusSzam(v));
			String vAnyagok = "Anyagok: ";
			ArrayList<Material> vml = v.getPacket().getMaterials();
			if (vml != null){
				for (Material m : vml)
					vAnyagok = vAnyagok.concat(m.toString()+" ");
			}
			log(vAnyagok);
			String vItemek = "Itemek: ";
			ArrayList<Item> vil = v.getItemHave();
			for(Item i : vil)
				vItemek = vItemek.concat(i.toString()+" ");
			log(vItemek);
		}
	}
	
	//private static int steps; 
	private static boolean tartKor;
	public static void getInputFirstAct(String[] readed) {
		//String[] readed;
		if(getLeft()<=0)return;
		decrLeft();
		tartKor = true;
		//while(tartKor) {
			//readed = read();
			switch(readed[0]) {
				/*case "newtest" :
					started = true;
					game = new Game();
					start();
					break;*/
				case "idle":
					//steps++;
					incrLeft();
					break;
				case "info":
					//steps++;
					getInfo();
					incrLeft();
					break;
				case "touch":
					//if(steps > 0) {
						touch();
						incrLeft();
						currentVirologus.getField().touching(currentVirologus);
					//}
					break;
				case "move":
					//if(steps > 0) {
						if (readed.length == 2) {
							moveTo(readed[1]);
							
						}
						else log("Bad parameter!");
					//}
					break;
				case "create":
					//if(steps > 0) {
						int elozo = currentVirologus.getAgensHave().size() ;
						if (readed.length == 2)
							currentVirologus.createAgens(readed[1]);
						else 
							log("Bad parameter! rossz");
						if(currentVirologus.getAgensHave().size() == elozo)
							log("");
						else
							log("v"+getVirologusSzam(currentVirologus)+" created a "+readed[1]);
					//}
					break;
				case "finishturn" : 
					//steps = 0;
					tartKor = false;
					int ii;
					for(ii = 0; currentVirologus != game.getEntity().get(ii) && ii<game.getEntity().size(); ii++);
					if(ii < game.getEntity().size()) {
						if(ii+1 != game.getEntity().size()) {
							currentVirologus = (Virologus)game.getEntity().get(ii+1);
							//System.out.print("EEEELOTTTEEEEEEE");
							//currentVirologus.myNotify();
							//System.out.print("VEEEGGGEEEEEEEEE");

						}
						else {
							currentVirologus = (Virologus)game.getEntity().get(0);
							//currentVirologus.myNotify();
						}
					}
					
					if (!currentVirologus.roundDesc()) {
						setLeft(0);
					}
						
					
					log("player in row: v" + getVirologusSzam(currentVirologus));
					getInfo();
					ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
					String kimenet = "Player can move to: ";
					for(Field f : n)
						for(int i = 0; i < game.getMap().getSize(); i++)
							if(f == game.getMap().getField(i))
								kimenet = kimenet.concat("f"+(i+1)+", ");
					log(kimenet);
					break;
				case "save":
					incrLeft();
					if (readed.length == 2) {
						ObjectOutputStream out;
						try {
							if(readed[1].contains(".")) {
								log("Bad parameter!");
							} else {
								out = new ObjectOutputStream(new FileOutputStream(readed[1] + ".txt"));
								out.writeObject(game);
								log("v"+getVirologusSzam(currentVirologus)+" saved the game!");
								out.close();
							}
						} catch(Exception e) {
							e.printStackTrace();
							log("Bad parameter!");
						}
					}
					else 
						log("Bad parameter!");
					break;
				
				default : 
					log("Bad parameter!");
					break;
			}
			game.myNotify();
			//steps--;
		//}
	}
	
	public static void getInputAfterTouch(String[] readed) {
		int justinfo = 1;
		if(getLeft()<=0)return;
		decrLeft();
		while(justinfo > 0) {

			switch(readed[0]) {
				/*case "newtest" :
					started = true;
					game = new Game();
					start();
					break;*/
				case "idle":
					//steps++;
					incrLeft();
					break;
				case "create":
					//if(steps > 0) {
						int elozo = currentVirologus.getAgensHave().size() ;
						if (readed.length == 2)
							currentVirologus.createAgens(readed[1]);
						else 
							log("Bad parameter! rossz");
						if(currentVirologus.getAgensHave().size() == elozo)
							log("");
						else
							log("v"+getVirologusSzam(currentVirologus)+" created a "+readed[1]);
					//}
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
					else 
						log("Bad parameter!");
					break;
				case "stealmaterial":
					if (readed.length == 3) {
						stealmaterial(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "kill":
					if (readed.length == 2) {
						kill(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "useagens":
					if (readed.length == 3) {
						useagens(readed);
					}
					else 
						log("Bad parameter!");
					break;
				
				case "learn":
					if (readed.length == 1) {
						learn();
						currentVirologus.step();
					}
					else 
						log("Bad parameter!");
					break;
				case "collect":
					if (readed.length == 2) {
						collect(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "pickup":
					if (readed.length == 2) {
						pickup(readed);
					}
					else
						log("Bad parameter!");
					break;
				case "leave":
					if (readed.length == 2) {
						leave(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "finishturn" :

					justinfo = 0;
					//steps = 0;
					tartKor = false;
					startInfo();
					break;
				default : 
					log("Bad parameter!");
					break;
			}
			selectedVirologus = null;
			touched = false;
			justinfo--;
			game.myNotify();
		}
	}
	
	private static boolean testfromFile = false;;
	
	//init commands
	/*public static void start() {
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {
			case "test" :
                if(readed.length == 2) {
            int testNum;
			try{
			testNum = Integer.parseInt(readed[1]);
			}catch(NumberFormatException ex){break;}
			if(testNum<1 || testNum>44||testNum==4||testNum==32)break;

                    testfromFile = true;
                    String path;
                    try {
                        path = new java.io.File(".").getCanonicalPath();
                        path = path.replace("\\", "\\\\");
                        File file = new File(path+"\\\\test"+readed[1]+".txt");
                        try {
                            scanner = new Scanner(file);
                        } catch (FileNotFoundException e) {

                        }
                    } catch (IOException e1) {

                    }



                }
                break;
			case "load":
				if (readed.length == 2) {
					ObjectInputStream in;
					try {
						in = new ObjectInputStream(new FileInputStream(readed[1]));
						game = (Game)in.readObject();
						log("New map added!");
						game.run();
						in.close();
					} catch(FileNotFoundException fe) {
						log("Bad parameter!");
					} catch (IOException e) {
						log("Bad file!");
					} catch (ClassNotFoundException e) {
						log("Bad file!");
					}
				}
				else 
					log("Bad parameter!");
				break;
			case "createfield":
				if (readed.length == 1 || readed.length == 2) {
					createField(readed);
					log("A field has been created!");
				}
				else 
					log("Bad parameter!");
				break;
			case "setneighbour":
				if (readed.length == 3) {
					setNeighbour(readed);
				}
				else 
					log("Bad parameter!");
				break;
			case "add":
				if (readed.length == 3) {
					addSomething(readed);
				}
				else 
					log("Bad parameter!");
				break;
			case "startgame":
				if (readed.length == 1) {
					log("Game started!");
					currentVirologus = (Virologus)game.getEntityAt(0);
					if (currentVirologus == null)
						log("There's no virologus in play");
					else {
						startInfo();
						game.run();
					}
				}
				else 
					log("Bad parameter!");
				break;
			case "placevirologus":
				if (readed.length == 2) {
					placeVir(readed);
				}
				else 
					log("Bad parameter!");
				break;
			case "newtest":
				started = true;
				game = new Game();
				start();
				megy = false;
				break;
			case "close":
				megy=false;
				break;
			default : 
				log("Bad parameter!");
				break;
			}
		}
	}*/
	
	public static void startInfo() {
		log("player in row: v" + getVirologusSzam(currentVirologus));
		getInfo();
		ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
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
