package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;

public abstract class DAO<T> {
  protected Connection connect = Database.getConnexion();
  protected PreparedStatement stmt = null;
   
  /**
  * Inserts a T object in the database
  * @param obj the object to insert in the database
  * @return true if it worked, false otherwise
 * @throws SQLException 
  */
  public abstract boolean create(T obj) throws SQLException;

  /**
  * Deletes a T object in the database
  * @param obj the object to delete
  * @return true if it worked, false otherwise
 * @throws SQLException 
  */
  public abstract boolean delete(T obj) throws SQLException;

  /**
  * Updates a T object in the database
  * @param obj the object to update
  * @return true if it worked, false otherwise
 * @throws SQLException 
  */
  public abstract boolean update(T obj) throws SQLException;

  /**
  * Finds an object in the database thanks to its ID
  * @param id id of the object
  * @return the object concerned
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