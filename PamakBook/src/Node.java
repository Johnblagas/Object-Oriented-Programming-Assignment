
public class Node {
	private User user;
	private int path = 0; //� �������� ��� ��� �������� ������ ��� ����������
	
	
	public Node(User user)
	{
		this.user = user;
	}
	
	
	public void AddPath(int path) {
		this.path = path;
	}
	
	
	public int GetPath() {
		return path;
	}
	
	
	public User GetUser() {
		return user;
	}
}
