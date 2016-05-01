package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Answer;
import POJO.User;

public class UserDAO extends DAO<User> {

	public UserDAO()
	{
		super();
	}
	
	@Override
	public boolean create(User user) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO User (pseudo, password, mail) VALUES (?, ?, ?)");
		stmt.setString(1, user.getPseudo());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getEmail());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean delete(User user) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM User WHERE id_user = "+user.getId());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}

	@Override
	public boolean update(User user) throws SQLException {
		// TODO Auto-generated method stub
		stmt = connect.prepareStatement("UPDATE User SET pseudo = ?, password = ?, mail = ?");
		stmt.setString(1, user.getPseudo());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getEmail());
		stmt.executeUpdate();
		stmt.close();
		return false;
	}

	@Override
	public User find(int id) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM User WHERE id_user = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		int id_user = rs.getInt("id_answer");
		String pseudo = rs.getString("pseudo");
		String password = rs.getString("password");
		String email = rs.getString("email");
		rs.close();
		stmt.close();
		return new User(id_user, pseudo, password, email);
	}
	
	@Override
	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> listUsers = new ArrayList<User>();
		stmt = connect.prepareStatement("SELECT COUNT(*) as count FROM User");
		ResultSet rs = stmt.executeQuery();
		int count = rs.getInt("count");
		for(int i = 1; i<=count; i++){
			listUsers.add(find(i));
		}
		rs.close();
		stmt.close();
		return listUsers;
	}


}
