package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Answer;
import POJO.Poll;
import POJO.UserAnswer;

/**
 * DAO made to manages answers submitted by users in the database
 * @author Max Cabourg
 *
 */
public class UserAnswerDAO extends DAO<UserAnswer>{

	@Override
	/**
	 * Inserts an user's anwser in the database
	 * @param userAnswer the user's answer to insert
	 * @throws SQLException
	 */
	public void create(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO UserAnswer VALUES (?, ?, ?)");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.setInt(3, userAnswer.getId_answer());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Deletes an user's answer in the database
	 * @param userAnswer the user's answer to delete
	 * @throws SQLException
	 */
	public void delete(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM UserAnswer WHERE id_user = ? AND id_poll = ?");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	/**
	 * Updates an user's answer in the database
	 * @param userAnswer the user's answer to update
	 * @throws SQLException
	 */
	public void update(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("UPDATE UserAnswer SET id_user = ?, id_poll =  ?, id_answer = ?");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.setInt(3, userAnswer.getId_answer());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public UserAnswer find(int idUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Finds an user's answer on a specific poll
	 * @param idUser the id of the user
	 * @param idPoll the id of the poll
	 * @return the answer submitted by the user, null if there's any
	 * @throws SQLException
	 */
	public UserAnswer find(int idUser, int idPoll) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM UserAnswer WHERE id_user = ? AND id_poll = ?");
		stmt.setInt(1, idUser);
		stmt.setInt(2, idPoll);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return new UserAnswer(rs.getInt("id_user"), rs.getInt("id_poll"), rs.getInt("id_answer"));
		else
		{
			stmt.close();
			return null;
		}	
	}
	
	private int getNumberAnswersSubmitted(Poll poll) throws SQLException{
		stmt = connect.prepareStatement("SELECT COUNT(*) as numberAnswers FROM UserAnswer WHERE id_poll = ?");
		stmt.setInt(1, poll.getId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			int count = rs.getInt("numberAnswers");
			rs.close();
			stmt.close();
			return count;
		}
		else
			return -1;
	}
	
	/**
	 * Gets the rates of an answer in %
	 * @param poll the poll concerned
	 * @param answer the answer concerned
	 * @return the rate of the answer compared to the number of answers submitted on this poll in %
	 * @throws SQLException
	 */
	public double getAnswersRates(Poll poll, Answer answer) throws SQLException
	{
		int count = getNumberAnswersSubmitted(poll);
		if(count != -1)
		{
			stmt = connect.prepareStatement("SELECT COUNT(*) as numberAnswers FROM UserAnswer WHERE id_poll = ? AND id_answer = ?");
			stmt.setInt(1, poll.getId());
			stmt.setInt(2, answer.getId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return (double)((double)(rs.getInt("numberAnswers")) / (double)(count) * 100);
			else
				return -1;
		}
		else
			return -1;
	}

	@Override
	public ArrayList<UserAnswer> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
