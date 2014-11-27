package com.cummins.mowo.persistence;

import org.restlet.Context;

import com.cummins.mowo.persistence.entity.Timecard;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class PersistenceService<T> {

    /*
     * Initialize database settings.
     * 
     * Example made with H2 database but you are free to use your favorite DBMS.
     */

	
	public static final String URL = "jdbc:mysql://aa1kd1bv2uvafjo.cjmmmo08frru.ap-southeast-1.rds.amazonaws.com:3306/ebdb";

	public static final String USER = "admin";

	public static final String PASSWORD = "Welcome1982";

    public static void initialize() {
        // Ensure DB configuration is correct
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
        	
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Define CRUD functions for childen classes.
     */

    public abstract List<T> findAll() throws SQLException;

    public abstract List<T> findBy(String fieldName, String fieldValue)
            throws SQLException;

    public abstract T add(T toAdd) throws SQLException;

    public abstract Boolean delete(String id) throws SQLException;

    public abstract T findById(String id) throws SQLException;

    public abstract T update(T toUpdate, String id) throws SQLException;

    /*
     * Create and release connection
     */

    protected Connection createConnection() throws SQLException {
        Context.getCurrentLogger().finer(
                "Method createConnection() of PersistenceService called.");
        
        Connection conn =  DriverManager.getConnection(URL, USER, PASSWORD);
        return  conn;
    }

    protected void releaseConnection(Connection connection) throws SQLException {
        Context.getCurrentLogger().finer(
                "test_logger");
        connection.close();
    }

    /*
     * Generate a random ID.
     */

    protected String generateStringId() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }

    /*
     * Factories
     */

    public static TimecardPersistence getTimecardtPersistence() {
        Context.getCurrentLogger().finer(
                "Method getContactPersistence() of PersistenceService called.");
        return TimecardPersistence.getTimecardPersistence();
    }

}