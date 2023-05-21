package whut.ui.control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Icon extends JButton{

    String[] command;

    String path = System.getProperty("user.dir")+"/src/main/resources/";

    public Icon(String[] command, String iconName){
        this.command = command;
        this.setIcon(new ImageIcon(path+iconName+".png"));
        this.addActionListener(new AfterTouchActionListener());
    }

    public class AfterTouchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                MyRunnable.getInputAfterTouch(command);
         }
    }
}
