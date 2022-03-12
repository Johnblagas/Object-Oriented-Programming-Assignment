import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

public class Post implements Comparable<Post>{
	private Date date;
	private String post;
	private String username;
	
	
	public Post(String aPost, String aUsername){
		date = new Date(System.currentTimeMillis());
		post = aPost;
		username = aUsername;
	}
	
	
	public String GetDate() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss a");  
		String strDate = dateformat.format(date);
		
		return strDate;
	}
	
	
	public String GetPost() {
		return post;
	}
	

	
	public String GetUsername() {
		return username;
	}
	
	
	public int compareTo(Post aPost) {
		return this.date.compareTo(aPost.date);
	}
}
