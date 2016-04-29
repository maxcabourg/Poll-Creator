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
		stmt = connect.createStatement();
		int res = stmt.executeUpdate("INSERT INTO Poll VALUES ("+poll.getId()+",'"+poll.getQuestion()+"')");
		for(Answer ans : poll.getAnswers())
			stmt.executeUpdate("INSERT INTO Answer VALUES ("+ans.getId()+", '"+ans.getContent()+"')");	
		return true;
	}

	@Override
	public boolean delete(Poll obj) {
		return false;
	}

	@Override
	public boolean update(Poll poll) throws SQLException {
		stmt = connect.createStatement();
		int res = stmt.executeUpdate("UPDATE Poll SET id_poll = "+poll.getId()+", question = '"+poll.getQuestion()+"'");
		stmt.close();
		return true;
	}

	@Override
	public Poll find(int id) throws SQLException{
		List<Answer> listAnswers = new ArrayList<Answer>();
		stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Poll WHERE id_poll = "+id);
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
		stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM Poll");
		int count = rs.getInt("count");
		for(int i = 1; i<=count; i++){
			listPolls.add(find(i));
		}
		rs.close();
		stmt.close();
		return listPolls;
	}

}
