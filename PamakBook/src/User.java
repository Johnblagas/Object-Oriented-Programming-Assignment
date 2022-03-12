import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String name;
	private String email;
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	
	
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
	
	
	public void CoronaResearch() { //елжамифеи та пихама йяоуслата поу евоум еяхеи се епажг ле том вягстг "епибебаиылемо йяоусла"
		ArrayList<User> infected = friends;
		
		System.out.println("**************************************");
		System.out.println(name + " has been infected. The following users have to be tested");
		System.out.println("**************************************");
			
		for(User i : friends)
		{
			System.out.println(i.GetName());
			
			for(User j : i.friends)
			{
				if(!j.IsFriend(this) && !AlreadyInfected(infected, j)) System.out.println(j.GetName());
			}
		}
		
		System.out.println("--------------------------------------");
		
	}
	
	
	private boolean AlreadyInfected(ArrayList<User> infected, User anUser) { //екецвеи еам йапоиос вягстгс евеи елжамистеи гдг стоус пяосбебкглемоус
		
		for(User i : infected)
		{
			if(i == anUser) return false;
		}
		
		return true;
	}
}
