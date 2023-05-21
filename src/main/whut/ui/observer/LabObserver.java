package whut.ui.observer;

import whut.ui.control.Icon;
import whut.ui.control.MyRunnable;
import whut.field.Lab;
import whut.genetic_code.GeneticCode;
import whut.ui.container.Container;
import whut.ui.container.ContainerSuper;

import java.awt.BorderLayout;
import java.io.Serializable;

import javax.swing.JFrame;

public class LabObserver implements Observer, Serializable{

    private Lab lab;
    public void setLab(Lab l){lab = l;}
    
    public LabObserver(Lab l) {
    	lab=l;
    }

    @Override
	public void update(){
        GeneticCode g = lab.getGeneticCode();

        draw(g.toString());
    }

    public void draw(String name){
    	ContainerSuper cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Genetic Code:");
        String[] tmp = new String[1];
        tmp[0]= "learn";
        container.addIcon(new Icon(tmp,name));
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }

}
