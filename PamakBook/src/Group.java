import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Group implements Serializable{
	protected String name;
	protected String description;
	protected ArrayList<User> groupUsers = new ArrayList<User>();
	
	
	public Group(String aName, String aDescription) {
		name = aName;
		description = aDescription;
	}
	
	
	public String GetName() {
		return name;
	}
	
	
	public boolean IsMember(User anUser) {
		
		for(User i : groupUsers)
		{
			if( i == anUser) return true;
		}
		
		return false;
	}
	
	
	public void BecomeMember(User anUser) {
		
		if(!IsMember(anUser))
		{
			groupUsers.add(anUser);
			anUser.EnrollInGroup(this);
			//System.out.println(anUser.GetName() + " has been successfully enrolled in group" + name);
			JOptionPane.showMessageDialog(null, anUser.GetName() + " has been successfully enrolled in group" + name);
		}
		else
			JOptionPane.showMessageDialog(null, anUser.GetName() + " has been already enrolled in group" + name);
	}
	
	
	public void PrintMembers() {
		int count = 0;
		
		System.out.println("**************************************");
		System.out.println("Members of group " + name);
		System.out.println("**************************************");
		
		for(User i : groupUsers) {
			count++;
			System.out.println(count + ": " + i.GetName());
		}
	}
}
