package Service;

import java.sql.SQLException;
import java.util.List;

public interface Services <T>{
    /**
     * Adds an element of type T to the collection.
     * 
     * @param t The element to be added to the collection
     * @throws SQLException If a database access error occurs
     */
    public void add(T t) throws SQLException;
    public List<T> show() throws SQLException;
    /**
     * Deletes a record with the specified ID from the database.
     * 
     * @param id The unique identifier of the record to be deleted
     * @throws SQLException If a database access error occurs or this method is called on a closed connection
     */
    public void delete(int id) throws SQLException;
    public void edit(T t) throws SQLException;




}
