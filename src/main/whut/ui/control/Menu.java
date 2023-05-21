package whut.ui.control;

import whut.player.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu extends JPanel implements Serializable{
	private static int players = 1;
	private static JFrame frame;
	private static Game game;
	public static void main(String[] args) {
		drawMenu();
	}
	
	public static void setFrame(JFrame frame) {
		Menu.frame = frame;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static void createGame(){
		game = new Game(players);
	}
	
	public static void drawMenu() {
		JTextField t;
		frame = new JFrame("");
		frame.setPreferredSize( new Dimension(300, 300));
		
		JPanel top = new JPanel(new FlowLayout());
		top.setBackground(Color.YELLOW);
		JPanel mid = new JPanel(new FlowLayout());
		mid.setBackground(Color.WHITE);
		JPanel bot = new JPanel(new FlowLayout());
		bot.setBackground(Color.YELLOW);
		t = new JTextField(Integer.toString(players));
		t.setEnabled(false);
		
		JButton start = new JButton("Start game");
		start.addActionListener(ae -> {createGame(); frame.dispose();});
		
		JButton minusButton = new JButton("-");
		minusButton.addActionListener(ae -> {
				  if(players > 1) {
						players--;
				  }
				  t.setText(Integer.toString(players));
					frame.pack();
				  });
		
		JButton plusButton = new JButton("+");
		plusButton.addActionListener(ae -> {
				  players++;
				  t.setText(Integer.toString(players));
					frame.pack();
				  });
		
		JButton loadButton = new JButton("Load game");
		loadButton.addActionListener(ae -> {
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
			        game = (Game)ois.readObject();
			        ois.close();
				} 
				catch(Exception ex) {
				        ex.printStackTrace();
				}
			    MyRunnable.setGame(game);
				game.notifyObservers();
			    frame.dispose();
			}
			
		});
		
		top.add(start);
		mid.add(minusButton);
		mid.add(t);
		mid.add(plusButton);
		bot.add(loadButton);
		
		frame.add(top, BorderLayout.NORTH);
		frame.add(mid, BorderLayout.CENTER);
		frame.add(bot, BorderLayout.SOUTH);
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
