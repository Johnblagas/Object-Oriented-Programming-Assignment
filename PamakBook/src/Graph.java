import java.util.ArrayList;

public class Graph {
	private int diameter = 0;
	
	
	public void Diameter(ArrayList<User> users){
		
		ArrayList<Node> nodes = CreateNodes(users);
		
		for(Node i : nodes)
		{
			User firstUser = i.GetUser();
			
			ArrayList<Node> visited = new ArrayList<>(); //OI ������� ��� ������������� ���
			ArrayList<Node> unvisited = new ArrayList<>(); //OI ������� ��� ��� ��������� �� ������������ �����
			
			
			//��������� ������ ������� �������� �� ������������ ���������� ��� ��� ����� �������� ������
			for(Node j : nodes)
			{
				if(!j.GetUser().equals(firstUser) && firstUser.IsFriend(j.GetUser()))
				{
					j.AddPath(1);
					visited.add(j);
				}
				else if(!j.GetUser().equals(firstUser))
					unvisited.add(j);
			}
			
			
			int visitedLength = visited.size();
			int unvisitedLength = unvisited.size();
			int j = 0;
			while(!unvisited.isEmpty())
			{
				while(j != visitedLength)
				{
					Node visitedNode = visited.get(j); //�� ��DE ��� ����������� ����� ��� ������ ��� �� �������������� ������������
					
					for(int k=0 ; k<unvisitedLength; k++)
					{
						Node unvisitedNode = unvisited.get(k); //�� ��DE ��� ����������� ����� ��� ������ ��� ��� �� ��������������
						int count = visitedNode.GetPath();
						
						
						if(visitedNode.GetUser().IsFriend(unvisitedNode.GetUser()))
						{
							count ++;
							if(count > diameter)
								diameter = count;
							
				
							unvisitedNode.AddPath(count);
							
							
							visited.add(unvisitedNode);
							unvisited.remove(unvisitedNode);
							
							
							
							if(unvisitedLength != unvisited.size())
							{
								unvisitedLength = unvisited.size();
								k = -1;
							}

						}	
					}
					
					visitedLength = visited.size();
					j++;
				}
			}
		}
		
		System.out.println("The diameter is: " + diameter);
		
		
		
	}
	
	
	
	private ArrayList<Node> CreateNodes(ArrayList<User> users){
		
		ArrayList<Node> nodes = new ArrayList<>();
		
		for(User i : users)
		{
			Node n = new Node(i);
			nodes.add(n);
		}
		
		return nodes;
	}
}
