package whut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Icon extends JButton{

    String[] command;





    public Icon(String[] c,String p){
    command = c;
    this.setIcon(new ImageIcon(p+".png"));
    this.addActionListener(new afterTouchActionListener());
    }

    public class afterTouchActionListener implements ActionListener {
      @Override
     public void actionPerformed(ActionEvent e) {
            MyRunnable.getInputAfterTouch(command);
     }
    }


}
