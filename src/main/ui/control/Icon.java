package ui.control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Icon extends JButton{

    String[] command;

    String path = System.getProperty("user.dir")+"/src/main/resources/";

    public Icon(String[] c,String p){
        command = c;
        this.setIcon(new ImageIcon(path+p+".png"));
        this.addActionListener(new AfterTouchActionListener());
    }

    public class AfterTouchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                MyRunnable.getInputAfterTouch(command);
         }
    }
}
