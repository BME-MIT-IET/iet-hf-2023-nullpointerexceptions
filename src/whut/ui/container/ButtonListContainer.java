package whut.ui.container;

import whut.ui.control.Menu;
import whut.ui.control.MyRunnable;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonListContainer extends JPanel {
	ArrayList<JButton> buttons = new ArrayList<>();
	
	public ButtonListContainer() {
		this.setLayout(new FlowLayout());
		this.setBackground(Color.PINK);
	}
	public void clearButtons() {
		buttons.clear();
	}
	
	public void addButton(String buttonT) {
		JButton tmp = new JButton();
		tmp.setText(buttonT);
		tmp.setActionCommand(buttonT);
		tmp.addActionListener(new ButtonActionListener());
		buttons.add(tmp);
	}
	
	public void draw() {
		for (JButton b : buttons) {
			this.add(b);
		}
	}
	
	class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Save")) {
				JFrame parentFrame = new JFrame();
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory( new File("C:\\"));
														
				chooser.setDialogTitle("Specify a file to save");   
				int userSelection = chooser.showSaveDialog(parentFrame);
				File fileToSave = chooser.getSelectedFile();
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    if(!fileToSave.exists()) {
					    try {
				            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave.getPath()));
				            oos.writeObject(MyRunnable.getGame());
				            oos.close();
				        }
						catch(Exception ex) {
				            ex.printStackTrace();
				        }
				    }
				    else {
				    	JOptionPane.showConfirmDialog(null,"Van mar ilyen nevu fajl!","Warning",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
				    }
				}
			}
			if (ae.getActionCommand().equals("New Game")) {
				MyRunnable.setSelected(null);
				MyRunnable.setTouched(false);
				MyRunnable.getFrame().dispose();
				Menu.drawMenu();
			}
			if (ae.getActionCommand().equals("Finishturn")) {
				MyRunnable.setLeft(3);
				MyRunnable.setSelected(null);
				MyRunnable.setTouched(false);
				String[] command = new String[1];
				command[0] = "finishturn";
				MyRunnable.getInputFirstAct(command);
			}
		}
	}

}
