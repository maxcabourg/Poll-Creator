package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Comment;

public class CommentDAO extends DAO<Comment> {
	
	public CommentDAO() {
		super();
	}

	@Override
	public boolean create(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Comment (content, id_poll, id_user) VALUES (?, ?, ?)");
		stmt.setString(1, comment.getContent());
		stmt.setInt(2, comment.getId_poll());
		stmt.setInt(3, comment.getId_user());
		stmt.executeUpdate();
		return true;
	}

	@Override
	public boolean delete(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Comment WHERE id_comment = ?");
		stmt.setInt(1, comment.getId());
		stmt.executeUpdate();
		return true;
	}

	@Override
	public boolean update(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Comment SET content = ?, id_poll = ?, id_user = ?");
		stmt.setString(1, comment.getContent());
		stmt.setInt(2, comment.getId_poll());
		stmt.setInt(3, comment.getId_user());
		stmt.executeUpdate();
		return true;
	}

	@Override
	public Comment find(int id) throws SQLException {
		stmt = connect.prepareStatement("SELECT * FROM Comment WHERE id_comment = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			String content = rs.getString("content");
			int id_poll = rs.getInt("id_poll");
			int id_user = rs.getInt("id_user");
			return new Comment(id, content, id_user, id_poll);
		}
		else
			return null;
	}

	@Override
	public ArrayList<Comment> getAll() throws SQLException {
		return null;
	}

}
