package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Answer;
import POJO.Poll;

public class PollDAO extends DAO<Poll>{

	public PollDAO() {
		super();
	}

	@Override
	public boolean create(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Poll VALUES (?, ?)");
		stmt.setInt(1, poll.getId());
		stmt.setString(2, poll.getQuestion());
		stmt.executeUpdate();
		for(Answer ans : poll.getAnswers()){
			stmt = connect.prepareStatement("INSERT INTO Answer VALUES (?, ?, ?)");
			stmt.setInt(1, ans.getId());
			stmt.setString(2, ans.getContent());
			stmt.setInt(3, poll.getId());
			stmt.executeUpdate();
		}
		stmt.close();
		return true;
	}

	@Override
	public boolean delete(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Poll WHERE id_poll = ?");
		stmt.setInt(1,  poll.getId());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean update(Poll poll) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Poll SET question = ? WHERE id_poll = ?");
		stmt.setString(1, poll.getQuestion());
		stmt.setInt(2, poll.getId());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public Poll find(int id) throws SQLException{
		List<Answer> listAnswers = new ArrayList<Answer>();
		stmt = connect.prepareStatement("SELECT * FROM Poll WHERE id_poll = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		int poll_id = rs.getInt("id_poll");
		String question = rs.getString("question");
		rs.close();
		rs = stmt.executeQuery("SELECT * FROM Answer WHERE id_poll = "+id);
		while(rs.next())
			listAnswers.add(new Answer(rs.getInt("id_answer"), rs.getString("answer")));
		rs.close();
		stmt.close();
		return new Poll(poll_id, question, listAnswers);
	}

	@Override
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

}
