package com.cummins.mowo.resource.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.restlet.data.Status;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.cummins.mowo.MowoAPIApplication;
import com.cummins.mowo.persistence.PersistenceService;
import com.cummins.mowo.persistence.TimecardPersistence;
import com.cummins.mowo.persistence.entity.Timecard;
import com.cummins.mowo.representation.TimecardListRepresentation;
import com.cummins.mowo.representation.TimecardRepresentation;
import com.cummins.mowo.resource.TimecardListResource;
import com.cummins.mowo.utils.TimecardUtils;

public class TimecardListServerResource extends ServerResource implements TimecardListResource {

	private TimecardPersistence timecardPersistence;

	 /*
     * Method called at the creation of the Resource (ie : each time the
     * resource is called)
     */
    @Override
    protected void doInit() throws ResourceException {

        getLogger().finer(
                "Method doInit() of TimecardListServerResource called.");

        /*
         * Initialize a persistence class which will be called to do operations
         * on the database.
         */
        timecardPersistence = PersistenceService.getTimecardtPersistence();

        getLogger().finer(
                "Method doInit() of TimecardListServerResource finished.");
    }
	
	public TimecardListRepresentation represent() throws ResourceException {
		getLogger().finer(
                "Method represent() of CompanyListServerResource called.");

        /*
         * Check authorization
         */
        if (!isInRole(MowoAPIApplication.ROLE_USER)) {
            getLogger().info("Unauthorized user");
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);
        }

        getLogger().finer("User allowed to call the resource.");

        try {

            /*
             * Retrieve List<Company> from persistence layer
             */
            List<Timecard> timecards = timecardPersistence.findAll();

            /*
             * Create companyListRepresentaion
             */
            List<TimecardRepresentation> timecardReprs = new ArrayList<TimecardRepresentation>();
            for (Timecard timecard : timecards) {
                TimecardRepresentation timecardRepr = TimecardUtils
                        .timecardToTimecardRepresentation(timecard);
                timecardRepr.setSelf(MowoAPIApplication.ROUTE_TIMECARDS + "/"
                        + timecard.getId());
                timecardReprs.add(timecardRepr);
            }
            TimecardListRepresentation result = new TimecardListRepresentation();
            result.setList(timecardReprs);

            getLogger()
                    .finer("Method represent() of TimecardListServerResource finished.");
            return result;

        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            getLogger().warning("SQLException " + ex.getMessage());
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    ex.getMessage(), ex);
        }
	}

	public TimecardRepresentation add(TimecardRepresentation timecardReprIn) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /*public TimecardListServerResource() {
        super();
    }
    public TimecardListServerResource(Context context,
                          Request request,
                          Response response) {     
        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }
  
    @Override
    protected Representation get() throws ResourceException {
        
    	String message = null; 
    	
    	getLogger().finer(
                "Method doInit() of CompanyListServerResource called.");

        timecardPersistence = PersistenceService.getTimecardtPersistence();
        
        Timecard toAdd = new Timecard();
        
        try {
        	toAdd = timecardPersistence.add(toAdd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			message = e.toString();
		}

        getLogger().finer(
                "Method doInit() of CompanyListServerResource finished.");
    	
    	
        message = message +  "   /n Hello World!" +
        " \n\nTime of request is:"
        + Calendar.getInstance()
        .getTime().toString() + toAdd.getId();
 
        return new StringRepresentation(message,
        MediaType.TEXT_PLAIN);
    }
	*/
	


}
