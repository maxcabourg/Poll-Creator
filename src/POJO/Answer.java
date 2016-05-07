package POJO;

public class Answer {
	
	private int id;
	private String content;
	private int id_poll;
	
	public Answer(int id, String content, int id_poll) {
		super();
		this.id = id;
		this.content = content;
		this.id_poll = id_poll;
	}
	
	public Answer(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public Answer(String content) {
		super();
		this.id = -1;
		this.content = content;
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

	public int getId_poll() {
		return id_poll;
	}

	public void setId_poll(int id_poll) {
		this.id_poll = id_poll;
	}
	
	

}
