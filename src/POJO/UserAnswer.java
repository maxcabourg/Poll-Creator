package POJO;

public class UserAnswer {
	
	private int id_user;
	private int id_poll;
	private int id_answer;
	
	public UserAnswer(int id_user, int id_poll, int id_answer) {
		super();
		this.id_user = id_user;
		this.id_poll = id_poll;
		this.id_answer = id_answer;
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

	public int getId_answer() {
		return id_answer;
	}

	public void setId_answer(int id_answer) {
		this.id_answer = id_answer;
	}
	
	

}
