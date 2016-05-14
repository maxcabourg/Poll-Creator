package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

/**
 * Data Access Object : Provides 4 functions according to the <strong>CRUD</strong> pattern.
 * @author Max Cabourg
 *
 * @param <T> Type of object 
 */
public abstract class DAO<T> {

  protected Database db = new Database();
  protected Connection connect;
  protected PreparedStatement stmt;
  
  /**
   * Intializates the database.
   */
  public DAO()
  {
	  connect = db.getConnexion();
  }
   
  /**
  * Inserts a T object in the database
  * @param obj the object to insert in the database
  * @throws SQLException
  */
  public abstract void create(T obj) throws SQLException;

  /**
  * Deletes a T object in the database
  * @param obj the object to delete
  * @throws SQLException 
  */
  public abstract void delete(T obj) throws SQLException;

  /**
  * Updates a T object in the database
  * @param obj the object to update
  * @throws SQLException 
  */
  public abstract void update(T obj) throws SQLException;

  /**
  * Finds an object in the database thanks to its ID
  * @param id id of the object
  * @throws SQLException 
  */
  public abstract T find(int id) throws SQLException;
  
  
  /**
   * Finds every row of the class concerned in the database
   * @return a list of every object stored in the database.
   * @throws SQLException 
   */
  public abstract ArrayList<T> getAll() throws SQLException;
}