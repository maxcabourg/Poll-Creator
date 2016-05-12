package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Answer;
import POJO.Poll;

/**
 * DAO made to manage answers in the database
 * @author Max Cabourg
 *
 */
public class AnswerDAO extends DAO<Answer> {
	
	/**
	 * Basic constructor
	 */
	public AnswerDAO() {
		super();
	}

	/**
	 * Inserts an answer in the database
	 * @param answer the answer to insert
	 */
	@Override
	public void create(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Answer (content) VALUES (?)");
		stmt.setString(1, answer.getContent());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Deletes an answer in the database
	 * @param answer the answer to delete
	 * @throws SQLException
	 */
	public void delete(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Answer WHERE id_answer = ?");
		stmt.setInt(1,  answer.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Updates an answer in the database
	 * @param answer the answer to update
	 * @throws SQLException
	 */
	public void update(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Answer SET content = ? WHERE id_answer = ?");
		stmt.setString(1, answer.getContent());
		stmt.setInt(2, answer.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Finds an answer in the database thanks to its id
	 * @param id id of the answer to find
	 * @throws SQLException
	 */
	public Answer find(int id) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM Answer WHERE id_answer = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			int answer_id = rs.getInt("id_answer");
			String content = rs.getString("content");
			int id_poll = rs.getInt("id_poll");
			rs.close();
			stmt.close();
			return new Answer(answer_id, content, id_poll);
		}
		else
			return null;
	}
	
	/**
	 * Finds all the answer of a specific poll
	 * @param poll The poll we want the answer of
	 * @return an ArrayList containing all the answers
	 * @throws SQLException
	 */
	public ArrayList<Answer> findByPoll(Poll poll) throws SQLException {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		stmt = connect.prepareStatement("SELECT * FROM Answer WHERE id_poll = ?");
		stmt.setInt(1, poll.getId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			int answer_id = rs.getInt("id_answer");
			String content = rs.getString("content");
			int id_poll = rs.getInt("id_poll");			
			answers.add(new Answer(answer_id, content, id_poll));
		}
		stmt.close();
		rs.close();
		return answers;
	}

	@Override
	/**
	 * Get all the answers of the database
	 * @return an ArrayList containing all the answers of the database
	 * @throws SQLException
	 */
	public ArrayList<Answer> getAll() throws SQLException {
		ArrayList<Answer> listAnswers = new ArrayList<Answer>();
		stmt = connect.prepareStatement("SELECT COUNT(*) as count FROM Answer");
		ResultSet rs = stmt.executeQuery();
		int count = rs.getInt("count");
		for(int i = 1; i<=count; i++){
			listAnswers.add(find(i));
		}
		rs.close();
		stmt.close();
		return listAnswers;
	}

}
