package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Comment;

/**
 * DAO made to manage comments in the database
 * @author Max Cabourg
 *
 */
public class CommentDAO extends DAO<Comment> {
	
	/**
	 * Basic constructor
	 */
	public CommentDAO() {
		super();
	}

	@Override
	/**
	 * Inserts a comment in the database
	 * @param comment the comment to insert
	 * @throws SQL exception
	 */
	public void create(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("INSERT INTO Comment (content, id_poll, id_user) VALUES (?, ?, ?)");
		stmt.setString(1, comment.getContent());
		stmt.setInt(2, comment.getId_poll());
		stmt.setInt(3, comment.getId_user());
		stmt.executeUpdate();
	}

	@Override
	/**
	 * Deletes a comment in the database
	 * @param comment the comment to delete
	 * @throws SQLException
	 */
	public void delete(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("DELETE FROM Comment WHERE id_comment = ?");
		stmt.setInt(1, comment.getId());
		stmt.executeUpdate();
	}

	@Override
	/**
	 * Updates a comment in the database
	 * @param comment the comment to update
	 * @throws SQLException
	 */
	public void update(Comment comment) throws SQLException {
		stmt = connect.prepareStatement("UPDATE Comment SET content = ?, id_poll = ?, id_user = ?");
		stmt.setString(1, comment.getContent());
		stmt.setInt(2, comment.getId_poll());
		stmt.setInt(3, comment.getId_user());
		stmt.executeUpdate();
	}

	@Override
	/**
	 * Finds a comment in the database thanks to the provided id
	 * @param id id of the comment to seek in the database
	 * @return the comment associated to this id, null if not found
	 * @throws SQLException
	 */
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
	
	/**
	 * Finds all the comments concerning a poll
	 * @param idPoll poll which we want to get the comments
	 * @return an arrayList containing all the comments concerning the poll
	 * @throws SQLException if the request fails
	 */
	public ArrayList<Comment> getAll(int idPoll) throws SQLException {
		ArrayList<Comment> allComments = new ArrayList<Comment>();
		stmt = connect.prepareStatement("SELECT * FROM Comment WHERE id_poll = ?");
		stmt.setInt(1, idPoll);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			int id_comment = rs.getInt("id_comment");
			String content = rs.getString("content");
			int id_user = rs.getInt("id_user");
			allComments.add(new Comment(id_comment, content, id_user, idPoll));
		}
		stmt.close();
		return allComments;
	}

}
