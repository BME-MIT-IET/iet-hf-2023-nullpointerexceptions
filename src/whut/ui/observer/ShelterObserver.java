package ui.observer;

import ui.control.Icon;
import ui.control.MyRunnable;
import field.Shelter;
import item.Item;
import ui.container.Container;
import ui.container.ContainerSuper;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class ShelterObserver implements Observer, Serializable{

    private Shelter shelter;
    public ShelterObserver(Shelter s){
    	shelter = s;
    }


    @Override
	public void update(){
        ArrayList<Item> items = shelter.getItems();
        ArrayList<String> strings = new ArrayList<>();
        for(Item i : items){
            strings.add(i.toString());
        }
        draw(strings);
    }
    public void draw(List<String> ss){
    	ContainerSuper cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Items:");
        for(String s: ss){
            String[] command = new String[2];
            command[0] = "pickup";
            command[1] = s;
            container.addIcon(new Icon(command,"resources/"+s));
        }
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }


}
