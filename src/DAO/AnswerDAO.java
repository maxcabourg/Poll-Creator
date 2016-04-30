package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Answer;
import POJO.Poll;

public class AnswerDAO extends DAO<Answer> {

	@Override
	public boolean create(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Answer (content) VALUES (?)");
		stmt.setString(1, answer.getContent());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean delete(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Answer WHERE id_answer = ?");
		stmt.setInt(1,  answer.getId());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean update(Answer answer) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Answer SET content = ? WHERE id_answer = ?");
		stmt.setString(1, answer.getContent());
		stmt.setInt(2, answer.getId());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public Answer find(int id) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM Answer WHERE id_answer = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		int answer_id = rs.getInt("id_answer");
		String content = rs.getString("content");
		rs.close();
		stmt.close();
		return new Answer(answer_id, content);
	}

	@Override
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
