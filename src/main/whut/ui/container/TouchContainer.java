package whut.ui.container;

import whut.ui.control.MyRunnable;
import whut.player.AgentUsable;
import whut.player.Virologist;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;

public class TouchContainer extends JPanel {
	private JLabel label = new JLabel("Players you touched: ");
	private JComboBox<String> cb = new JComboBox<>();
	private JButton button = new JButton();
	private String touch = "touch";
	
	public TouchContainer() {
	
		button.setText("Touch");
		button.setActionCommand(touch);
		button.addActionListener(new TouchActionListener()); 
		cb.addActionListener(new ItemActionListener());
		this.setLayout(new FlowLayout());
		this.add(label);
		this.add(cb);
		this.add(button);
	}
	
	public void draw(){
		this.removeAll();
		this.add(button);
		this.add(label);
		this.add(cb);
	}
	
	class ItemActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(MyRunnable.getTouched()) {
				String valami=cb.getSelectedItem().toString();
				String sub = valami.substring(1);
				MyRunnable.setSelected((Virologist)MyRunnable.getGame().getEntityAt(Integer.parseInt(sub)-1)); //szep.
				MyRunnable.getGame().myNotify();
			}
		}
	}
	
	class TouchActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {

			if (ae.getActionCommand().equals(touch) && !MyRunnable.getTouched()) {
				cb.removeAllItems();
				ArrayList<String> players = new ArrayList<>();
				List<AgentUsable> vs =  MyRunnable.getCurrentVir().getField().getVirologists();
				for (AgentUsable a : vs) {
					Virologist v = (Virologist)a;
					players.add("v"+MyRunnable.getVirologusSzam(v));
				}
				for (String player : players)
					cb.addItem(player);
				MyRunnable.setSelected((Virologist)vs.get(0));
				String[] command = new String[1];
				command[0] = touch;
				MyRunnable.setTouched(true);
				MyRunnable.getInputFirstAct(command);
				MyRunnable.getGame().myNotify();
			}
			
		}
	}
	
	

}
