package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Answer;
import POJO.Poll;
import POJO.User;

/**
 * DAO made to manage Poll in the database
 * @author Max Cabourg
 *
 */
public class PollDAO extends DAO<Poll>{

	/**
	 * Basic constructor
	 */
	public PollDAO() {
		super();
	}

	@Override
	/**
	 * Inserts a poll in the database
	 * @param poll the poll to insert
	 * @throws SQLException
	 */
	public void create(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Poll (question, id_user) VALUES (?, ?)");
		stmt.setString(1, poll.getQuestion());
		stmt.setInt(2, poll.getCreator().getId());
		stmt.executeUpdate();
		int maxId = findMaxId();
		System.out.println("max id = "+maxId);
		for(Answer ans : poll.getAnswers()){
			stmt = connect.prepareStatement("INSERT INTO Answer (content, id_poll) VALUES (?, ?)");
			stmt.setString(1, ans.getContent());
			stmt.setInt(2, maxId);
			stmt.executeUpdate();
		}
		stmt.close();
	}

	@Override
	/**
	 * Deletes a comment in the database
	 * @param poll the poll to delete
	 * @throws SQLException
	 */
	public void delete(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Poll WHERE id_poll = ?");
		stmt.setInt(1,  poll.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Updates a comment in the database
	 * @poll the poll to update
	 * @throws SQLException
	 */
	public void update(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Poll SET question = ? WHERE id_poll = ?");
		stmt.setString(1, poll.getQuestion());
		stmt.setInt(2, poll.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Finds a poll in the database thanks to the provided id
	 * @param id the id of the poll to seek
	 * @return the poll associated to the id, null if not found
	 * @throws SQLException
	 */
	public Poll find(int id) throws SQLException{
		List<Answer> listAnswers = new ArrayList<Answer>();
		stmt = connect.prepareStatement("SELECT * FROM Poll WHERE id_poll = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			int poll_id = rs.getInt("id_poll");
			String question = rs.getString("question");
			rs.close();
			rs = stmt.executeQuery("SELECT * FROM Answer WHERE id_poll = "+id);
			while(rs.next())
				listAnswers.add(new Answer(rs.getInt("id_answer"), rs.getString("content")));
			rs.close();
			stmt.close();
			return new Poll(poll_id, question, listAnswers);
		}
		else
			return null;
	}
	
	/**
	 * Finds all the polls created by an user
	 * @param user the concerned user
	 * @return an ArrayList containing all the polls created by the user
	 * @throws SQLException
	 */
	public ArrayList<Poll> findByUser(User user) throws SQLException{
		ArrayList<Poll> polls = new ArrayList<Poll>();
		stmt = connect.prepareStatement("SELECT * FROM Poll WHERE id_user = ?");
		stmt.setInt(1, user.getId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			int id_poll = rs.getInt("id_poll");
			polls.add(find(id_poll));
		}
			return polls;
	}
	
	/**
	 * Finds all the polls created by an user with a specific pattern
	 * @param user the concerned user
	 * @param pattern pattern to seek
	 * @return an ArrayList containing all the polls created by the user
	 * @throws SQLException
	 */
	public ArrayList<Poll> findByUserAndPattern(User user, String pattern) throws SQLException{
		ArrayList<Poll> polls = new ArrayList<Poll>();
		stmt = connect.prepareStatement("SELECT * FROM Poll WHERE id_user = ? AND question LIKE ?");
		stmt.setInt(1, user.getId());
		stmt.setString(2, "%"+pattern+"%");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			int id_poll = rs.getInt("id_poll");
			polls.add(find(id_poll));
		}
			return polls;
	}

	@Override
	/**
	 * Gets all the poll created
	 * @return an ArrayList containing all the polls created
	 * @throws SQLException
	 */
	public ArrayList<Poll> getAll() throws SQLException {
		ArrayList<Poll> listPolls = new ArrayList<Poll>();
		stmt = connect.prepareStatement("SELECT COUNT(*) as count FROM Poll");
		ResultSet rs = stmt.executeQuery();
		int count = rs.getInt("count");
		for(int i = 1; i<=count; i++){
			listPolls.add(find(i));
		}
		rs.close();
		stmt.close();
		return listPolls;
	}
	
	/**
	 * Finds the maximum poll id in the database
	 * @return the maximum id
	 * @throws SQLException
	 */
	public int findMaxId() throws SQLException
	{
		stmt = connect.prepareStatement("SELECT MAX(id_poll) as maxCount FROM Poll");
		ResultSet rs = stmt.executeQuery();
		int maxId = 0;
		if(rs.next())
			maxId = rs.getInt("maxCount");
		return maxId;
	}
	
	/**
	 * Checks if an user has created a special poll
	 * @param p the concerned poll
	 * @param u the concerned user
	 * @return true if the user has created the poll, false otherwise
	 * @throws SQLException
	 */
	public boolean isCreatedBy(Poll p, User u) throws SQLException
	{
		stmt = connect.prepareStatement("SELECT * FROM Poll WHERE id_user = ? and id_poll = ?");
		stmt.setInt(1, u.getId());
		stmt.setInt(2,  p.getId());
		ResultSet rs = stmt.executeQuery();
		return rs.next();
	}

}
