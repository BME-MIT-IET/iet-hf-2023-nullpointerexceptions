package whut;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Container extends JPanel{
    private ArrayList<Icon> icons = new ArrayList<Icon>();
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();




    private String containerText = new String();

    public Container(String cT){containerText = cT;}


    public void addIcon(Icon i)
    {
        icons.add(i);
    }
    
    public void addLabel(JLabel j)
    {
        labels.add(j);
    }

    public void draw()
    {

    	this.removeAll();
        //MyRunnable.addLogo(this);
    	JPanel c1=new JPanel(new FlowLayout());
    	JPanel c2=new JPanel(new FlowLayout());
    	JPanel c3=new JPanel(new FlowLayout());
    	this.setBackground(Color.CYAN);
    	c1.setBackground(Color.MAGENTA);
    	c2.setBackground(Color.WHITE);
    	c3.setBackground(Color.CYAN);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        c1.add(new JLabel(containerText));
        //utolso, max 5 icont felrakja
        for(int i = icons.size()-1;i>=0&&i>icons.size()-6;i--){
            c2.add(icons.get(i));
        }
        for(int i = labels.size()-1;i>=0&&i>labels.size()-6;i--){
            c3.add(labels.get(i));
        }
        this.add(c1);
        this.add(c2);
        this.add(c3);


    }


}
