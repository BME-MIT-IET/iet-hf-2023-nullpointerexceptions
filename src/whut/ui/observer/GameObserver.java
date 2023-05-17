package whut.ui.observer;

import whut.ui.control.Menu;
import whut.ui.control.MyRunnable;
import whut.field.Field;
import whut.player.Game;
import whut.ui.container.ButtonListContainer;
import whut.ui.container.MoveContainer;
import whut.ui.container.TouchContainer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

import javax.swing.*;

public class GameObserver implements Observer, Serializable{
	
	private Game game;
	private ButtonListContainer blc = new ButtonListContainer();
	private TouchContainer tc;
	private JFrame frame;
	
	public GameObserver(Game g) {
		game = g;
		frame = new JFrame(); 
		setFrame();
		drawGame();
	}
		
	
	@Override
	public void update() {
		setFrame();
		drawGame();
	}
	
	public void setFrame() {
		frame.setPreferredSize( new Dimension(1000, 600));
		frame.getContentPane().setBackground(Color.GREEN);
		blc.setBackground(Color.PINK);
		MyRunnable.setFrame(frame);

		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public void drawGame() {
		if (!game.getMegy()) return;
		frame.getContentPane().removeAll();
		frame.repaint();
				
		blc = new ButtonListContainer();
		blc.addButton("Save");
		blc.addButton("New Game");
		blc.addButton("Finishturn");
		blc.draw();
		MyRunnable.getCurrentVir().myNotify();
		if(MyRunnable.getSelected() != null)
			MyRunnable.getSelected().myNotify();
		
		ArrayList<String> fields = new ArrayList<>();
		List<Field> fs = MyRunnable.getCurrentVir().getField().getNeighbourhood();
		for (Field f : fs) {
			fields.add("f"+MyRunnable.getFieldSzam(f));
		}

		JPanel p = new JPanel(new FlowLayout());
		p.setBackground(Color.PINK);

		MoveContainer mc = new MoveContainer();
		mc = new MoveContainer(fields);
		
		if(!MyRunnable.getTouched())
			tc = new TouchContainer();
		else {
			MyRunnable.getCurrentVir().getField().myNotify();
		}
		
		mc.setBackground(Color.PINK);
		tc.setBackground(Color.PINK);
		p.add(new JLabel("Remaining steps: "+Integer.toString(MyRunnable.getLeft())+"            "+"You are on: f"+MyRunnable.getFieldSzam(MyRunnable.getCurrentVir().getField())));
		p.add(mc);
		p.add(tc);
		frame.add(blc, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.NORTH);
		frame.revalidate();
	}
	
	public void drawEnd(String msg) {
		frame.dispose();
		frame = new JFrame();

		JButton b = new JButton("Back to menu");
		b.addActionListener(ae -> {MyRunnable.setSelected(null); MyRunnable.setTouched(false);frame.dispose(); Menu.drawMenu();});
		
		frame.add(new JLabel("                      "+msg), BorderLayout.CENTER);
		frame.add(b, BorderLayout.SOUTH);
		
		MyRunnable.setFrame(frame);
		frame.setPreferredSize( new Dimension(200, 200));
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}



}
