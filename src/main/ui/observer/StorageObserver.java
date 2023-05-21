package ui.observer;

import ui.control.Icon;
import ui.control.MyRunnable;
import field.Storage;
import material.Material;
import material.Nukleotid;
import ui.container.Container;
import ui.container.ContainerSuper;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StorageObserver implements Observer, Serializable {
    private Storage storage;
    public StorageObserver(Storage s){
    	storage=s;
    }

    @Override
	public void update(){
       List<Material> ms =  storage.getPacket().getMaterials();
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
       draw(nukNum,aminoNum);
    }
    public void draw(int nukNum,int aminoNum){
    	ContainerSuper cs = new ContainerSuper("Field stat:");
        Container container = new Container("Material:");
        String[] tmp1 = new String[2];
        String[] tmp2 = new String[2];
        tmp1[0]= tmp2[0]="collect";
        tmp1[1] = "nukleotid";
        tmp2[1]= "amino";
        container.addIcon(new Icon(tmp1,"nukleotid"));
        container.addIcon(new Icon(tmp2,"amino"));
        container.add(new JLabel(Integer.toString(nukNum)));
        container.add(new JLabel(Integer.toString(aminoNum)));
        container.addLabel(new JLabel(Integer.toString(nukNum)));
        container.addLabel(new JLabel(Integer.toString(aminoNum)));
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }

}
