package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.UserAnswer;

public class UserAnswerDAO extends DAO<UserAnswer>{

	@Override
	public boolean create(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO UserAnswer VALUES (?, ?, ?)");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.setInt(3, userAnswer.getId_answer());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean delete(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM UserAnswer WHERE id_user = ? AND id_poll = ?");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean update(UserAnswer userAnswer) throws SQLException {
		stmt = connect.prepareStatement("UPDATE UserAnswer SET id_user = ?, id_poll =  ?, id_answer = ?");
		stmt.setInt(1, userAnswer.getId_user());
		stmt.setInt(2, userAnswer.getId_poll());
		stmt.setInt(3, userAnswer.getId_answer());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public UserAnswer find(int idUser) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
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

	@Override
	public ArrayList<UserAnswer> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
