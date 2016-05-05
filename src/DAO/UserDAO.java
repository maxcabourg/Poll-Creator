package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		try {
			stmt.setString(2, UserDAO.sha1(user.getPassword()));
		} catch (NoSuchAlgorithmException e) {}
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
		if(rs.next()){
			int id_user = rs.getInt("id_user");
			String pseudo = rs.getString("pseudo");
			String password = rs.getString("password");
			String email = rs.getString("mail");
			rs.close();
			stmt.close();
			return new User(id_user, pseudo, password, email);
		}
		else
			return null;
	}
	
	public User find(String name) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM User WHERE pseudo = ?");
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			int id_user = rs.getInt("id_user");
			String pseudo = rs.getString("pseudo");
			String password = rs.getString("password");
			String email = rs.getString("mail");
			rs.close();
			stmt.close();
			return new User(id_user, pseudo, password, email);
		}
		else
			return null;
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
	
	private static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }       
        return sb.toString();
	}
	
	/**
	 * Checks in the couple (pseudo, password) matches an entry of the User table in Database
	 * @param pseudo Name of the user
	 * @param password Password of the user : <strong>non encrypted</strong>
	 * @return true it matches an entry, false otherwise
	 * @throws SQLException if there's a problem with the request
	 */
	public boolean existsInDabatase(String pseudo, String password) throws SQLException{
		stmt = connect.prepareStatement("SELECT password FROM User WHERE pseudo = '"+pseudo+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			String pwd = rs.getString("password");
			try {
				if(pwd.equals(UserDAO.sha1(password)))
					return true;
				return false;
			} catch (NoSuchAlgorithmException e) {}
		}
		else	
			return false;
		return false;
	}

}
