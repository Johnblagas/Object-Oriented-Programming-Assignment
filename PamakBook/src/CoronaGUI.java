import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CoronaGUI extends JFrame{
	
	private JPanel panel;
	private JTextArea text;
	private JButton backToLoginButton;
	
	public CoronaGUI(User user, MainGUI PrevGUI) {
		
		panel = new JPanel();
		text = new JTextArea();
		backToLoginButton = new JButton("Back to Login Screen");
		
		//Text Component
		text = user.CoronaResearch();
		text.setEditable(false);
		
		//Panel Setup
		panel.add(text);
		panel.add(backToLoginButton);
		
		this.setContentPane(panel);
		
		
		//Button Listener
		backToLoginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CoronaGUI.this.setVisible(false);
				PrevGUI.setVisible(true);	
			}	
		});
		
		
		
		this.setVisible(true);
		this.setSize(500, 300);
		this.setTitle("Πιθανή Μετάδοση Ιού");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
