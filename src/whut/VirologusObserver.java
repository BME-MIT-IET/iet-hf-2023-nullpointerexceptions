package whut;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VirologusObserver implements Observer, Serializable{
    private ContainerSuper csLeft;
    private ContainerSuper csRight;
    Virologus v;
    
    public VirologusObserver(Virologus _v) {
    	v = _v;
    }
    
    public void setVirologus(Virologus vir){v=vir;}
    
    @Override
	public void update() {
    	if(v.equals(MyRunnable.getCurrentVir())) {
    		updateLeft();
    	}
    	if(v.equals(MyRunnable.getSelected())) {
    		updateRight();
    		
    	}
    }

    public void updateLeft(){
        List<Item> items = v.getItemHave();
        ArrayList<String> is = new ArrayList<String>();
        for(Item i : items){
            is.add(i.toString());
        }
        List<Agens> agenses = v.getAgensHave();
        ArrayList<String> as = new ArrayList<String>();
        for(Agens a : agenses){
            as.add(a.toString());
        }
        List<GeneticCode> genetics = v.getGeneticCodeHave();
        ArrayList<String> gs = new ArrayList<String>();
        for(GeneticCode g : genetics){
            gs.add(g.toString());
        }
        
        ArrayList<Material> ms =  v.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<String>();
        int nukNum = 0;
        int aminoNum = 0;
        for(Material m : ms){
            if(m.isSame(new Nukleotid())){
            	nukNum+= m.getValue();
                //System.out.print(m.getValue());
            }else{
            	aminoNum+=m.getValue();
                //System.out.print(m.getValue());
            }
            ss.add(m.toString());
       	}
        
        List<Agens> agensesOn = v.getAgensOnMe();
        ArrayList<String> aos = new ArrayList<String>();
        for(Agens a : agensesOn){
            aos.add(a.toString());
        }
        drawLeft(is, as, gs, ss, aos, nukNum, aminoNum);
    }


    public void drawLeft(ArrayList<String> is,ArrayList<String> as,ArrayList<String> gs,ArrayList<String> ss,ArrayList<String> aos,int nukNum, int aminoNum){
    	
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
            c1.addIcon(new Icon(command,s));

            if(s.equals("axe")){
                String[] commando = new String[2];
                commando[0]="kill";
                commando[1]= "v"+ MyRunnable.getVirologusSzam(MyRunnable.getSelected());
                c1.addIcon(new Icon(commando,"kill"));
            }
        }
        csLeft.addContainer(c1);
        Container c2 = new Container("Agens:");
        for(String s : as){
            String[] command = new String[3];
            command[0] = "useagens";
            command[1] = "v"+MyRunnable.getVirologusSzam(MyRunnable.getSelected());
            command[2] = s;
            c2.addIcon(new Icon(command,s));
        }
        csLeft.addContainer(c2);

        Container c3 = new Container("GeneticCode:");
        for(String s : gs){
            String[] command = new String[2];
            command[0] = "create";
            command[1] = s.substring(0, s.length()-4);
            c3.addIcon(new Icon(command,s));
        }
        csLeft.addContainer(c3);

        Container c4 = new Container("Material:");
        String[] tmp1 = new String[1];
        String[] tmp2 = new String[1];
        tmp1[0]=tmp2[0]="idle"; //kell idle parancs ami nem csinál semmit, vagy disableelni kell ezt a buttont
        c4.addLabel(new JLabel(Integer.toString(nukNum)));
        c4.addLabel(new JLabel(Integer.toString(aminoNum)));
        c4.addIcon(new Icon(tmp2,"nukleotid"));
        c4.addIcon(new Icon(tmp1,"amino"));
        csLeft.addContainer(c4);
        
        Container c5 = new Container("UnderEffect:");
        for(String s : aos){
            String[] command = new String[1];
            command[0] = "idle";
            c5.addIcon(new Icon(command,s));
        }
        csLeft.addContainer(c5);
        
        csLeft.draw();
        frame.add(csLeft, BorderLayout.WEST);
    }




    public void updateRight() {
        ArrayList<Material> ms =  v.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<String>();
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
        List<Item> items = v.getItemHave();
        ArrayList<String> is = new ArrayList<String>();
        for(Item i : items){
            is.add(i.toString());
        }
        
        List<Agens> agensesOn = v.getAgensOnMe();
        ArrayList<String> aos = new ArrayList<String>();
        for(Agens a : agensesOn){
            aos.add(a.toString());
        }
        
        drawRight(ss, is, aos, nukNum,  aminoNum);
    }
    
    public void drawRight(ArrayList<String> ss, ArrayList<String> is, ArrayList<String> aos, int nukNum, int aminoNum){    	
        csRight = new ContainerSuper("V"+ MyRunnable.getVirologusSzam(v) +"player's stats:");
        
        Container c1 = new Container("Items:");
        for(String s : is){
            String[] command = new String[3];
            command[0] = "stealitem";
            command[1] = "v"+MyRunnable.getVirologusSzam(v);
            command[2] = s;
            c1.addIcon(new Icon(command,s));
        }
        csRight.addContainer(c1);

        Container c2 = new Container("Material:");
        String[] tmp1 = new String[3];
        String[] tmp2 = new String[3];
        tmp1[0]= tmp2[0]="stealmaterial";
        tmp1[1] = tmp2[1] = "v"+ MyRunnable.getVirologusSzam(v);
        tmp1[2] = "amino";
        tmp2[2]= "nukleotid";
        c2.addIcon(new Icon(tmp2,"nukleotid"));
        c2.addIcon(new Icon(tmp1,"amino"));
        c2.addLabel(new JLabel(Integer.toString(aminoNum)));
        c2.addLabel(new JLabel(Integer.toString(nukNum)));
        csRight.addContainer(c2);
        
        Container c3 = new Container("UnderEffect:");
        for(String s : aos){
            String[] command = new String[1];
            command[0] = "idle";
            c3.addIcon(new Icon(command,s));
        }
        csRight.addContainer(c3);
        
        csRight.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(csRight, BorderLayout.EAST);
    }

}
