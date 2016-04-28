package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import database.Database;

public abstract class DAO<T> {
  protected Connection connect = Database.getConnexion();
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * Inserts a T object in the database
  * @param obj the object to insert in the database
  * @return true if it worked, false otherwise
  */
  public abstract boolean create(T obj);

  /**
  * Deletes a T object in the database
  * @param obj the object to delete
  * @return true if it worked, false otherwise
  */
  public abstract boolean delete(T obj);

  /**
  * Updates a T object in the database
  * @param obj the object to update
  * @return true if it worked, false otherwise
  */
  public abstract boolean update(T obj);

  /**
  * Finds an object in the database thanks to its ID
  * @param id id of the object
  * @return the object concerned
  */
  public abstract T find(int id);
  
  
  /**
   * Finds every row of the class concerned in the database
   * @return a list of every object stored in the database.
   */
  public abstract ArrayList<T> getAll();
}