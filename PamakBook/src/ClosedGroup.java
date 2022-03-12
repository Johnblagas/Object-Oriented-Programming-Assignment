import javax.swing.JOptionPane;

public class ClosedGroup extends Group {
	
	
	public ClosedGroup(String aName, String aDescription) {
		super(aName, aDescription);
	}
	
	
	public void BecomeMember(User anUser) {
		
		if(groupUsers.size() == 0 )
			super.BecomeMember(anUser);
		
		else if(CheckFriendship(anUser))
			super.BecomeMember(anUser);
		
		else
			//System.out.println("FAILED: " + anUser.GetName() + " cannot be enrolled in group" + name);
			JOptionPane.showMessageDialog(null, "FAILED: " + anUser.GetName() + " cannot be enrolled in group" + name);
	}
	
	
	private boolean CheckFriendship(User anUser) {
		
		for(User i: groupUsers)
		{
			if(i.IsFriend(anUser))
				return true;
		}
		
		return false;
	}
}
