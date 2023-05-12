package whut;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ShelterObserver implements Observer, Serializable{

    private ContainerSuper cs;

    private Shelter shelter;
    public ShelterObserver(Shelter s){
    	shelter = s;
    }


    @Override
	public void update(){
        ArrayList<Item> items = shelter.getItems();
        ArrayList<String> strings = new ArrayList<String>();
        for(Item i : items){
            strings.add(i.toString());
        }
        draw(strings);
    }
    public void draw(ArrayList<String> ss){
        cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Items:");
        for(String s: ss){
            String[] command = new String[2];
            command[0] = "pickup";
            command[1] = s;
            container.addIcon(new Icon(command,s));
        }
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }


}
