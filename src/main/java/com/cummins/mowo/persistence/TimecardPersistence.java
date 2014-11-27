package com.cummins.mowo.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.restlet.Context;

import com.cummins.mowo.persistence.entity.Timecard;

public class TimecardPersistence extends PersistenceService<Timecard> {

	  /*
     * Singleton pattern.
     */
    private static TimecardPersistence timcardPersistence;

    public static synchronized TimecardPersistence getTimecardPersistence() {
        if (timcardPersistence == null) {
        	timcardPersistence = new TimecardPersistence();
        }
        return timcardPersistence;
    }

    private TimecardPersistence() {
    }
	
	@Override
	public List<Timecard> findAll() throws SQLException {
        Context.getCurrentLogger().finer(
                "Method add() of TimecardPersistence called.");

        Connection connection = null;
        try {
            connection = createConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("Select * FROM TIMECARD");
            ResultSet rs = preparedStatement.executeQuery();

            List<Timecard> timecards = new ArrayList<Timecard>();
            while (rs.next()) {
                Timecard timecard = new Timecard();
                timecards.add(timecard);
                timecard.setId(rs.getInt("ID"));
                timecard.setClockInTime(rs.getInt("punch_start"));
                timecard.setClockInTime(rs.getInt("punch_end"));
            }
            return timecards;
        } finally {

            releaseConnection(connection);
            Context.getCurrentLogger().finer(
                    "Method findAll() of TimecardPersistence finished.");
        }
	}

	@Override
	public List<Timecard> findBy(String fieldName, String fieldValue)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timecard add(Timecard toAdd) throws SQLException {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
            connection = createConnection();
            stmt = connection.createStatement();
		    String sql = "INSERT INTO TIMECARD VALUES (null, 1, 2)";
		    stmt.executeUpdate(sql);
		    ResultSet generatedKeys = stmt.getGeneratedKeys();
		    generatedKeys.next();
		    toAdd.setId(generatedKeys.getInt(1));
		 
			return toAdd;
		} finally {
         //   releaseConnection(connection);
            Context.getCurrentLogger().finer(
                    "Method add() of TimecardPersistence finished.");
        }		

	}

	@Override
	public Boolean delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timecard findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timecard update(Timecard toUpdate, String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
