package whut.ui.container;

import whut.ui.control.MyRunnable;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoveContainer extends JPanel{
	private JLabel label = new JLabel("Move to: ");
	private JComboBox<String> cb = new JComboBox<>();
	private JButton button = new JButton();
	
	public MoveContainer(List<String> where) {
		for (String field : where)
			cb.addItem(field);
		button.setText("Move");
		button.setActionCommand("move");
		button.addActionListener(new MoveActionListener());
		this.setLayout(new FlowLayout());
		this.add(label);
		this.add(cb);
		this.add(button);
	}
	
	public MoveContainer() {
		
	}
	
	public void clearBox() {
		cb.removeAllItems();
	}
	
	public void draw(){
		this.removeAll();
		this.add(label);
		this.add(cb);
		this.add(button);
	}
	
	class MoveActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("move")) {
				String[] command = new String[2];
				command[0] = "move";
				command[1] = cb.getSelectedItem().toString();
				MyRunnable.setIsTouched(false);
				MyRunnable.setSelected(null);
				MyRunnable.getInputFirstAct(command);
			}
		}
	}

}
