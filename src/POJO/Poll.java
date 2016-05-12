package POJO;

import java.util.List;

/**
 * POJO representing a poll.
 * It is made of : <br>
 * <ul>
 * 	<li>an id</li>
 *  <li>the question of the poll</li>
 *  <li>an ArrayList containing the answers of the poll</li>
 *  <li>The creator of the poll</li>
 * </ul>
 * @author Max Cabourg
 *
 */
public class Poll {

	private int id;
	private String question;
	private List<Answer> answers;
	private User creator;
	
	public Poll(int id, String question, List<Answer> answers, User creator) {
		super();
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.creator = creator;
	}
	
	public Poll(int id, String question, List<Answer> answers) {
		super();
		this.id = id;
		this.question = question;
		this.answers = answers;
	}
	
	public Poll(String question, List<Answer> answers) {
		super();
		this.id = -1;
		this.question = question;
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	
}
