package POJO;

/**
 * POJO representing a comment.
 * It is composed of : <br>
 * <ul>
 * 	<li>an id</li>
 *  <li> the content of the comment </li>
 *  <li> the id of the user concerned</li>
 *  <li> the id of the poll concerned</li>
 * <ul>
 * @author Max Cabourg
 *
 */
public class Comment {

	private int id;
	private String content;
	private int id_user;
	private int id_poll;
	
	public Comment(int id, String content, int id_user, int id_poll) {
		super();
		this.id = id;
		this.content = content;
		this.id_user= id_user;
		this.id_poll = id_poll;
	}
	
	public Comment(String content, int id_user, int id_poll) {
		super();
		this.id = -1;
		this.content = content;
		this.id_user= id_user;
		this.id_poll = id_poll;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_poll() {
		return id_poll;
	}
	public void setId_poll(int id_poll) {
		this.id_poll = id_poll;
	}
	
}
