import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPageGUI extends JFrame{
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel fullpanel;
	private JTextField usernameField;
	private JTextField emailField;
	private JButton backToLoginButton;
	private JTextArea postField;
	private JButton postButton;
	private JTextArea recentPostsArea;
	private JList suggestedFriendsList;
	private JLabel recentPostsLabel;
	private JLabel suggestedFriendsLabel;
	private JButton addFriendButton;
	private JTextField addFriendField;
	private JLabel groupsLabel;
	private JList groupList;
	private JButton enrollGroupButton;
	
	
	public UserPageGUI(User user, ArrayList<Group> allGroups, MainGUI PrevGUI, ArrayList<User> allUsers) {
		
		//GUI Panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		fullpanel = new JPanel();
		
		//GUI Layout
		BoxLayout fullLayout = new BoxLayout(fullpanel, BoxLayout.Y_AXIS);
		BoxLayout layout5 = new BoxLayout(panel5, BoxLayout.X_AXIS);
		BoxLayout layout4 = new BoxLayout(panel4, BoxLayout.X_AXIS);
		BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.X_AXIS);
		BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.X_AXIS);
		BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
		
		panel1.setLayout(layout1);
		panel2.setLayout(layout2);
		panel3.setLayout(layout3);
		panel4.setLayout(layout4);
		panel5.setLayout(layout5);
		fullpanel.setLayout(fullLayout);
		
		//GUI Components
		usernameField = new JTextField();
		emailField = new JTextField();
		backToLoginButton = new JButton("Back to Login Screen");
		postField = new JTextArea("Write a post...",10, 10);
		postField.setBorder(BorderFactory.createLineBorder(Color.gray));
		postButton = new JButton("Post");
		recentPostsArea = new JTextArea(20, 20);
		suggestedFriendsList = new JList<String>();
		recentPostsLabel = new JLabel("Recent Posts");
		suggestedFriendsLabel = new JLabel("Suggested Friends");
		addFriendButton = new JButton("Add Friend");
		addFriendField = new JTextField("New friend's name");
		groupsLabel = new JLabel("Groups");
		enrollGroupButton = new JButton("Enroll in");
		
	
		
		//User's Info Line
		usernameField.setText(user.GetName());
		usernameField.setEditable(false);
		emailField.setText(user.GetEmail());
		emailField.setEditable(false);
		
		//Recent Posts Line
		recentPostsArea = user.PrintPosts();
		
		//Suggested Friends Line
		suggestedFriendsList = user.SuggestedFriends();
		
		//Groups Line
		DefaultListModel<String> model = new DefaultListModel<String>();
		groupList = new JList<String>(model);
		
		for(Group i : allGroups)
		{
			model.addElement(i.GetName());
		}
		
		//Add components to panels
		panel1.add(usernameField);
		panel1.add(emailField);
		panel1.add(backToLoginButton);
		panel2.add(postField);
		panel2.add(postButton);
		panel3.add(recentPostsLabel);
		panel3.add(recentPostsArea);
		panel4.add(suggestedFriendsLabel);
		panel4.add(suggestedFriendsList);
		panel4.add(addFriendField);
		panel4.add(addFriendButton);
		panel5.add(groupsLabel);
		panel5.add(groupList);
		panel5.add(enrollGroupButton);
		
		fullpanel.add(panel1);
		fullpanel.add(panel2);
		fullpanel.add(panel3);
		fullpanel.add(panel4);
		fullpanel.add(panel5);
		
		this.setLayout(new FlowLayout());
		this.add(fullpanel);
		
		
		
		//Buttons' Listeners
		backToLoginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				UserPageGUI.this.setVisible(false);
				PrevGUI.setVisible(true);	
			}	
		});
		
		postButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				user.AddPost(postField.getText());
				recentPostsArea.append(user.GetLastPost());
				postField.setText(null);
			}
			
		});
		
		addFriendButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String newFriendsname = addFriendField.getText();
				
				if(PrevGUI.CheckUser(newFriendsname))
				{
					User newFriend = PrevGUI.FindUser(newFriendsname);
					user.BecomeFriend(newFriend);
					new Graph().Diameter(allUsers);
					
				}
				else
					JOptionPane.showMessageDialog(null, "There's no user named " + newFriendsname);
			}
			
		});
		
		enrollGroupButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String selection = "";
				
				if(groupList.getSelectedIndex() != -1)
					selection = (String) groupList.getSelectedValue();
				else
					JOptionPane.showMessageDialog(null, "No group selected");
				
				for(Group i : allGroups)
				{
					if(i.GetName().equals(selection))
						i.BecomeMember(user);
				}
			}
			
		});
		
		this.setVisible(true);
		this.setSize(600, 400);
		this.setTitle(" Σελίδα Χρήστη");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
