package whut.ui.container;

import whut.ui.control.MyRunnable;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerSuper extends JPanel{
    private ArrayList<Container> containers = new ArrayList<Container>();
    private String containerSuperText = new String();

    public ContainerSuper(String cST){containerSuperText = cST;}

    public void addContainer(Container container){
        containers.add(container);
    }
////
    public void draw(){

        this.removeAll();

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        MyRunnable.addLogo(this);
        this.add(new JLabel(containerSuperText));
        for(Container c : containers){
            this.add(c);
            c.draw();
        }
    }

}
