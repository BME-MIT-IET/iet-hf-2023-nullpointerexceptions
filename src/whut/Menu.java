package whut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JPanel implements Serializable{
	private static int players = 1;
	private static JFrame f;
	private static JTextField t;
	private static Game gg;
	public static void main(String args[]) {
		drawMenu();
	}
	
	public static void setFrame(JFrame frame) {
		f = frame;
	}
	
	public static JFrame getFrame() {
		return f;
	}
	
	public static void createGame(){
		gg = new Game(players);
	}
	
	public static void drawMenu() {
		
		f = new JFrame("");
		f.setPreferredSize( new Dimension(300, 300));
		
		JPanel top = new JPanel(new FlowLayout());
		top.setBackground(Color.YELLOW);
		JPanel mid = new JPanel(new FlowLayout());
		mid.setBackground(Color.WHITE);
		JPanel bot = new JPanel(new FlowLayout());
		bot.setBackground(Color.YELLOW);
		t = new JTextField(Integer.toString(players));
		t.setEnabled(false);
		
		JButton start = new JButton("Start game");
		start.addActionListener(ae -> {createGame(); f.dispose();});
		
		JButton minus = new JButton("-");
		minus.addActionListener(ae -> { 
				  if(players > 1)
						players--;
				  t.setText(Integer.toString(players));
					f.pack();
				  });
		
		JButton plus = new JButton("+");
		plus.addActionListener(ae -> { 
				  players++;
				  t.setText(Integer.toString(players));
					f.pack();
				  });
		
		JButton load = new JButton("Load game");
		load.addActionListener(ae -> {
			int userSelection;
			JFileChooser chooser = new JFileChooser();
			JFrame parentFrame = new JFrame();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory( new File("C:\\"));
			chooser.setDialogTitle("Specify a file to load");   
			userSelection = chooser.showOpenDialog(parentFrame);
			
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = chooser.getSelectedFile();
			    
			    try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToSave.getPath()));
			        gg = (Game)ois.readObject();
			        ois.close();
				} 
				catch(Exception ex) {
				        ex.printStackTrace();
				}
			    MyRunnable.setGame(gg);
				gg.myNotify();
			    f.dispose();
			}
			
		});
		
		top.add(start);
		mid.add(minus);
		mid.add(t);
		mid.add(plus);
		bot.add(load);
		
		f.add(top, BorderLayout.NORTH);
		f.add(mid, BorderLayout.CENTER);
		f.add(bot, BorderLayout.SOUTH);
		f.pack();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
}
