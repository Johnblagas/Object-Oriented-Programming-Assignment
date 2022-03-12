import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JFrame {
	
	private JTextField usernameInputField;
	private JButton enterUserButton;
	private JButton showPotentialButton;
	private JPanel panel;
	
	public LoginGUI(ArrayList<User> users) { //LOGIN GUI
		
		//Components Setaup
		usernameInputField = new JTextField("Please enter your name...");
		enterUserButton = new JButton("Enter User Page");
		showPotentialButton = new JButton("Show Potential Infections");
		
		//Panel and layout Setup
		panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		
		//Set components to layout
		usernameInputField.setAlignmentX(Component.CENTER_ALIGNMENT);
		enterUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		showPotentialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Add components to panels
		panel.add(usernameInputField);
		panel.add(enterUserButton);
		panel.add(showPotentialButton);

		this.setLayout(new FlowLayout());
		this.add(panel);
		
		
		
		//Buttons' Listeners
		enterUserButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String username = usernameInputField.getText();
				
				
				if(CheckUser(users, username))
				{
					User theUser = FindUser(users, username);
					new UserPageGUI(theUser, LoginGUI.this);
					LoginGUI.this.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "The user " + username + " doesn't exist"); 
				
				}
			
		});
		
		showPotentialButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String username = usernameInputField.getText();

				
				if(CheckUser(users, username))
				{
					User theUser = FindUser(users, username);
					new CoronaGUI(theUser, LoginGUI.this);
					LoginGUI.this.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "The user " + username + " doesn't exist"); 
				}
			
		});
		
		
		
		this.setVisible(true);
		this.setSize(300, 300);
		this.setTitle("еъСОДОР ВЯчСТГ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	private boolean CheckUser(ArrayList<User> users, String anUsername) { //екецвеи еам упаявеи вягстгс ле ема суцйейяилемо омола
		
		for(User i : users)
		{
			if(i.GetName().equals(anUsername)) return true;
		}
		
		return false;
	}
	
private User FindUser(ArrayList<User> users, String anUsername) { //екецвеи еам упаявеи вягстгс ле ема суцйейяилемо омола йаи том епистяежеи
		
		for(User i : users)
		{
			if(i.GetName().equals(anUsername)) return i;
		}
		
		return null;
	}
}
