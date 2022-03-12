import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGUI extends JFrame {
	
	private JTextField usernameInputField;
	private JTextField emailInputField;
	private JButton enterUserButton;
	private JButton showPotentialButton;
	private JButton newUserButton;
	private JButton saveButton;
	private JPanel fullpanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private ArrayList<User> users;
	
	public MainGUI(ArrayList<User> allUsers, ArrayList<Group> allGroups, File platformData) { //LOGIN GUI
		
		users = allUsers;
		
		//GUI Panels
		fullpanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//GUI Layout
		BoxLayout fulllayout = new BoxLayout(fullpanel, BoxLayout.Y_AXIS);
		BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
		BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.X_AXIS);
		BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.X_AXIS);
		
		fullpanel.setLayout(fulllayout);
		panel1.setLayout(layout1);
		panel2.setLayout(layout2);
		panel3.setLayout(layout3);
		
		//GUI Components
		usernameInputField = new JTextField("Please enter your name...");
		emailInputField = new JTextField("Please enter your e-mail...");
		newUserButton = new JButton("New User");
		enterUserButton = new JButton("Enter User Page");
		showPotentialButton = new JButton("Show Potential Infections");
		saveButton = new JButton("Save PamakBook");
		
		
		//Add components to panels
		panel1.add(newUserButton);
		panel1.add(usernameInputField);
		panel1.add(emailInputField);
		panel2.add(enterUserButton);
		panel2.add(showPotentialButton);
		panel3.add(saveButton);
		
		fullpanel.add(panel1);
		fullpanel.add(panel2);
		fullpanel.add(panel3);
		

		this.setLayout(new FlowLayout());
		this.add(fullpanel);
		
		
		
		//Buttons' Listeners
		enterUserButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String username = usernameInputField.getText();
				
				
				if(CheckUser(username))
				{
					User theUser = FindUser(username);
					new UserPageGUI(theUser, allGroups, MainGUI.this, allUsers);
					MainGUI.this.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "The user " + username + " doesn't exist"); 
				
				}
			
		});
		
		showPotentialButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String username = usernameInputField.getText();

				
				if(CheckUser(username))
				{
					User theUser = FindUser(username);
					new CoronaGUI(theUser, MainGUI.this);
					MainGUI.this.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "The user " + username + " doesn't exist"); 
				}
			
		});
		
		newUserButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String username = usernameInputField.getText();
				String email = emailInputField.getText();
				

				if(!CheckUser(username))
				{
					while(!CheckMail(email)) 
					{
						email = JOptionPane.showInputDialog("Please enter the email in correct form");
						System.out.println(email);

						
						if(email == null)
							break;
					}
					
					if(email != null)
					{
						User newUser = new User(username, email);
						users.add(newUser);
					
						JOptionPane.showMessageDialog(null, "The user " + username + " created succesfully");
					}
					else if(email == null)
						JOptionPane.showMessageDialog(null, "The user creation canceled");
				}
				else
					JOptionPane.showMessageDialog(null, "The user " + username + " already exists");
			}
		});
		
		
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				try {
					FileOutputStream fileOut = new FileOutputStream(platformData);
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					
					out.writeObject(users);
					out.writeObject(allGroups);
					
					out.close();
					fileOut.close();
					
					System.out.println("Data exported");
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		this.setVisible(true);
		this.pack();
		this.setTitle("йЕМТЯИЙч сЕКъДА");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	public boolean CheckUser(String anUsername) { //екецвеи еам упаявеи вягстгс ле ема суцйейяилемо омола
		
		for(User i : users)
		{
			if(i.GetName().equals(anUsername)) return true;
		}
		
		return false;
	}

	
	public User FindUser(String anUsername) { //екецвеи еам упаявеи вягстгс ле ема суцйейяилемо омола йаи том епистяежеи
		
		for(User i : users)
		{
			if(i.GetName().equals(anUsername)) return i;
		}
		
		return null;
	}
	
	
	public boolean CheckMail(String anEmail) {
		
			Pattern pattern = Pattern.compile("ics[0-9]{3,5}@uom.edu.gr|iis[0-9]{3,5}@uom.edu.gr|dai[0-9]{3,5}@uom.edu.gr");
			Matcher matcher = pattern.matcher(anEmail);
			
			
			if(matcher.matches())
				return true;
			else
				return false;
	}
}
