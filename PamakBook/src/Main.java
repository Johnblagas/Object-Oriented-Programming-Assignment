import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<>();
		
		
		User u1 = new User("Makis", "iis1998@uom.edu.gr");
		User u2 = new User("Petros", "ics1924@uom.edu.gr");
		User u3 = new User("Maria", "iis2012@uom.edu.gr");
		User u4 = new User("Gianna", "iis19133@uom.edu.gr");
		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
		User u6 = new User("Babis", "ics19104@uom.edu.gr");
		User u7 = new User("Stella", "dai1827@uom.edu.gr");
		User u8 = new User("Athena", "ics20856@uom.edu.gr");
		
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");
		
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		users.add(u6);
		users.add(u7);
		users.add(u8);
		
		u1.BecomeFriend(u2);
		u1.BecomeFriend(u5);
		u5.BecomeFriend(u6);
		u3.BecomeFriend(u4);
		u3.BecomeFriend(u2);
		u4.BecomeFriend(u6);
		u5.BecomeFriend(u3);
		u1.BecomeFriend(u6);
		u5.BecomeFriend(u2);
		u7.BecomeFriend(u1);
		u8.BecomeFriend(u6);
		
		u5.CommonFriends(u4);
		u1.CommonFriends(u5);
		
		u1.PrintFriends();
		u3.PrintFriends();
		
		g1.BecomeMember(u4);
		g1.BecomeMember(u3);
		g1.BecomeMember(u2);
		
		g2.BecomeMember(u4);
		g2.BecomeMember(u5);
		g2.BecomeMember(u6);
		g2.BecomeMember(u5);
		
		u4.PrintGroups();
		g1.PrintMembers();
		g2.PrintMembers();
		
		u4.CoronaResearch(); 
		
		new LoginGUI(users);
	}

}
