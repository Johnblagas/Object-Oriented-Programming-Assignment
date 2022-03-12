import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class User {
	private String name;
	private String email;
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Post> posts = new ArrayList<Post>();
	private Post lastpost;
	
	
	public User(String aName, String anEmail) {
		
		Scanner scan = new Scanner(System.in);
		
		if(CheckMail(anEmail))
		{
			name = aName;
			email = anEmail;
		}
		else
		{
			System.out.println("User " + aName + " has not been created. Email format is not acceptable.");
			do {
				System.out.println("Please give an email in correct form: ");
				email = scan.nextLine();
			}while(!CheckMail(email));
		}
			
		
		
	}
	
	
	private boolean CheckMail(String anEmail) {
		Pattern pattern = Pattern.compile("ics[0-9]{3,5}@uom.edu.gr|iis[0-9]{3,5}@uom.edu.gr|dai[0-9]{3,5}@uom.edu.gr");
		Matcher matcher = pattern.matcher(anEmail);
		
		if(matcher.matches())
			return true;
		else
			return false;
		
	}
	
	
	public void BecomeFriend(User anUser) {
		
		if(!IsFriend(anUser)  && !anUser.equals(this)) {
			friends.add(anUser);
			anUser.friends.add(this);
			System.out.println(name +  " and " + anUser.GetName() + " are now friends!");
		}
		else 
			System.out.println(name + " and " + anUser.GetName() + " are already friends.");
		
	}
	
	
	public void EnrollInGroup(Group aGroup) {
		groups.add(aGroup);
	}
	
	
	public boolean IsFriend(User anUser) {  //екецвеи ам 2 вягстес еимаи жикои, ежосом дем еимаи о идиос вягстгс
		
		if(!this.equals(anUser))
		{
			for(User i : friends)
			{
				if(i.equals(anUser)) return true;
			}
		}
			
		return false;
	}
	
	
	public String GetName() {
		return name;
	}
	
	
	public String GetEmail() {
		return email;
	}
	
	
	public void CommonFriends(User anUser) {
		int count = 0;
		
		System.out.println("**************************************");
		System.out.println("Common friends of " + name + " and " + anUser.GetName());
		System.out.println("**************************************");
		
		for(User i : friends)
		{
			if(anUser.IsFriend(i)) // екецвоуле еам емас жикос тоу вя╧стг поу лас ажояа е╨маи ж╨кос тоу деутеяоу вягстг
			{
				count++;
				System.out.println(count + ": " + i.GetName());
			}
		}
		
		System.out.println("--------------------------------------");
	}
	
	
	public void PrintFriends() {
		int count = 0;
		
		System.out.println("**************************************");
		System.out.println("Friends of " + name);
		System.out.println("**************************************");
		
		for(User i : friends)
		{
			count++;
			System.out.println(count + ": " + i.GetName());
		}
		
		System.out.println("--------------------------------------");
	}
	
	
	public void PrintGroups(){
		int count = 0;
		
		System.out.println("**************************************");
		System.out.println("Groups that " + name + " has been enrolled in");
		System.out.println("**************************************");
		
		for(Group i : groups)
		{
			count++;
			System.out.println(count + ": " + i.GetName());
		}
		
		System.out.println("--------------------------------------");
	}
	
	
	public JTextArea CoronaResearch() { //елжамифеи та пихама йяоуслата поу евоум еяхеи се епажг ле том вягстг "епибебаиылемо йяоусла"
		ArrayList<User> infected = new ArrayList<>();
		infected.addAll(friends);
		
		JTextArea text = new JTextArea("**************************************\n"
										+ name + " has been infected. The following users have to be tested\n"
										+ "**************************************\n");

			
		for(User i : infected)
		{
			text.append(i.GetName() + "\n");
		}
		
		for(int i=0; i<this.friends.size(); i++)
		{
			User myFriend = friends.get(i); 
			
			for(int j=0; j<myFriend.friends.size(); j++)
			{
				User friendsFriend = myFriend.friends.get(j);
				
				if(!friendsFriend.IsFriend(this) && !AlreadyInfected(infected, friendsFriend) && !friendsFriend.equals(this)) 
				{
					text.append(friendsFriend.GetName() + "\n");
					infected.add(friendsFriend);
				}
			}
		}
		
		
		text.append("--------------------------------------");
		return text;
	}
	
	
	private boolean AlreadyInfected(ArrayList<User> infected, User anUser) { //екецвеи еам йапоиос вягстгс евеи елжамистеи гдг стоус пяосбебкглемоус
		
		for(User i : infected)
		{
			if(i.equals(anUser)) return true;
		}
		
		return false;
	}
	
	
	public void AddPost(String text) {
		Post p = new Post(text, name);
		posts.add(p);
		lastpost = p;
		}
	
	
	public JTextArea PrintPosts() { //епистяежеи йеилемо JTEXT ока та пост тым жикым йаи тоу идиоу тоу вягстг ле вяомийг сеияа
		Set<Post> allPosts = new TreeSet<>(); //бафеи ока та пост се TREESET циа ма танимолгхоум
		JTextArea recentPosts = new JTextArea();
		
		for(Post p : posts)
		{
			allPosts.add(p);
		}
		
		for(User u : friends) 
		{
			for(Post p : u.posts)
			{
				allPosts.add(p);
			}			
		}
		
		for(Post i : allPosts)
		{
			recentPosts.append(i.GetUsername() + ", " + i.GetDate() + ", " + i.GetPost() + "\n");
		}
		
		return recentPosts;
	}
	
	
	public String GetLastPost() {
		return (lastpost.GetUsername() + ", " + lastpost.GetDate() + ", " + lastpost.GetPost() + "\n");
	}
	
	
	public JList SuggestedFriends() { // епистяежеи лиа киста ле тоус SUGGESTED FRIENDS
		ArrayList<User> alreadySuggested = new ArrayList<>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> suggestions = new JList<String>(model);
		
		
		for(User i : friends)
		{
			for(User j : i.friends)
			{
				if(!this.IsFriend(j) && !this.equals(j) && !alreadySuggested.contains(j))
				{
					model.addElement(j.GetName());
					alreadySuggested.add(j);
				}
			}
		}
		
		return suggestions;
	}
}
