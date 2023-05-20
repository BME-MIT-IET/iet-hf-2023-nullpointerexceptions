package ui.observer;

import ui.control.Icon;
import ui.control.MyRunnable;
import agent.Agens;
import genetic_code.GeneticCode;
import item.Item;
import material.Material;
import material.Nukleotid;
import player.Virologus;
import ui.container.Container;
import ui.container.ContainerSuper;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VirologusObserver implements Observer, Serializable{
    private ContainerSuper csLeft;
    private String resources = "resources/";
   
    Virologus virologus;
    
    public VirologusObserver(Virologus v) {
    	virologus = v;
    }
    
    public void setVirologus(Virologus vir){virologus=vir;}
    
    @Override
	public void update() {
    	if(virologus.equals(MyRunnable.getCurrentVir())) {
    		updateLeft();
    	}
    	if(virologus.equals(MyRunnable.getSelected())) {
    		updateRight();
    		
    	}
    }

    public void updateLeft(){
        List<Item> items = virologus.getItemHave();
        ArrayList<String> is = new ArrayList<>();
        for(Item i : items){
            is.add(i.toString());
        }
        List<Agens> agenses = virologus.getAgensHave();
        ArrayList<String> as = new ArrayList<>();
        for(Agens a : agenses){
            as.add(a.toString());
        }
        List<GeneticCode> genetics = virologus.getGeneticCodeHave();
        ArrayList<String> gs = new ArrayList<>();
        for(GeneticCode g : genetics){
            gs.add(g.toString());
        }
        
        List<Material> ms =  virologus.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<>();
        int nukNum = 0;
        int aminoNum = 0;
        for(Material m : ms){
            if(m.isSame(new Nukleotid())){
            	nukNum+= m.getValue();
            }else{
            	aminoNum+=m.getValue();
            }
            ss.add(m.toString());
       	}
        
        List<Agens> agensesOn = virologus.getAgensOnMe();
        ArrayList<String> aos = new ArrayList<>();
        for(Agens a : agensesOn){
            aos.add(a.toString());
        }
        drawLeft(is, as, gs, aos, nukNum, aminoNum);
    }


    public void drawLeft(List<String> is, List<String> as, List<String> gs, List<String> aos, int nukNum, int aminoNum){
    	
    	JFrame frame = MyRunnable.getFrame();
    	if (csLeft != null) {
    		frame.remove(csLeft);
    	}
        csLeft = new ContainerSuper("V"+MyRunnable.getVirologusSzam(MyRunnable.getCurrentVir())+"player in row");
        Container c1 = new Container("Items:");
        for(String s : is){
            String[] command = new String[2];
            command[0] = "leave";
            command[1] = s;
            c1.addIcon(new Icon(command,resources+s));

            if(s.equals("axe")){
                String[] commando = new String[2];
                commando[0]="kill";
                commando[1]= "v"+ MyRunnable.getVirologusSzam(MyRunnable.getSelected());
                c1.addIcon(new Icon(commando,"resources/kill"));
            }
        }
        csLeft.addContainer(c1);
        Container c2 = new Container("Agens:");
        for(String s : as){
            String[] command = new String[3];
            command[0] = "useagens";
            command[1] = "v"+MyRunnable.getVirologusSzam(MyRunnable.getSelected());
            command[2] = s;
            c2.addIcon(new Icon(command,resources+s));
        }
        csLeft.addContainer(c2);

        Container c3 = new Container("GeneticCode:");
        for(String s : gs){
            String[] command = new String[2];
            command[0] = "create";
            command[1] = s.substring(0, s.length()-4);
            c3.addIcon(new Icon(command,resources+s));
        }
        csLeft.addContainer(c3);

        Container c4 = new Container("Material:");
        String[] tmp1 = new String[1];
        String[] tmp2 = new String[1];
        tmp1[0]=tmp2[0]="idle"; //kell idle parancs ami nem csinál semmit, vagy disableelni kell ezt a buttont
        c4.addLabel(new JLabel(Integer.toString(nukNum)));
        c4.addLabel(new JLabel(Integer.toString(aminoNum)));
        c4.addIcon(new Icon(tmp2,"resources/nukleotid"));
        c4.addIcon(new Icon(tmp1,"resources/amino"));
        csLeft.addContainer(c4);
        
        Container c5 = new Container("UnderEffect:");
        for(String s : aos){
            String[] command = new String[1];
            command[0] = "idle";
            c5.addIcon(new Icon(command,resources+s));
        }
        csLeft.addContainer(c5);
        
        csLeft.draw();
        frame.add(csLeft, BorderLayout.WEST);
    }




    public void updateRight() {
        List<Material> ms =  virologus.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<>();
        int nukNum = 0;
        int aminoNum = 0;
        for(Material m : ms){
        	if(m.isSame(new Nukleotid())){
                aminoNum+= m.getValue();
            }else{
                nukNum+=m.getValue();
            }
            ss.add(m.toString());
        }
        List<Item> items = virologus.getItemHave();
        ArrayList<String> is = new ArrayList<>();
        for(Item i : items){
            is.add(i.toString());
        }
        
        List<Agens> agensesOn = virologus.getAgensOnMe();
        ArrayList<String> aos = new ArrayList<>();
        for(Agens a : agensesOn){
            aos.add(a.toString());
        }
        
        drawRight(is, aos, nukNum,  aminoNum);
    }
    
    public void drawRight(List<String> is, List<String> aos, int nukNum, int aminoNum){    	
    	ContainerSuper csRight = new ContainerSuper("V"+ MyRunnable.getVirologusSzam(virologus) +"player's stats:");
        
        Container c1 = new Container("Items:");
        for(String s : is){
            String[] command = new String[3];
            command[0] = "stealitem";
            command[1] = "v"+MyRunnable.getVirologusSzam(virologus);
            command[2] = s;
            c1.addIcon(new Icon(command,resources+s));
        }
        csRight.addContainer(c1);

        Container c2 = new Container("Material:");
        String[] tmp1 = new String[3];
        String[] tmp2 = new String[3];
        tmp1[0]= tmp2[0]="stealmaterial";
        tmp1[1] = tmp2[1] = "v"+ MyRunnable.getVirologusSzam(virologus);
        tmp1[2] = "amino";
        tmp2[2]= "nukleotid";
        c2.addIcon(new Icon(tmp2,"resources/nukleotid"));
        c2.addIcon(new Icon(tmp1,"resources/amino"));
        c2.addLabel(new JLabel(Integer.toString(aminoNum)));
        c2.addLabel(new JLabel(Integer.toString(nukNum)));
        csRight.addContainer(c2);
        
        Container c3 = new Container("UnderEffect:");
        for(String s : aos){
            String[] command = new String[1];
            command[0] = "idle";
            c3.addIcon(new Icon(command,resources+s));
        }
        csRight.addContainer(c3);
        
        csRight.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(csRight, BorderLayout.EAST);
    }

}
