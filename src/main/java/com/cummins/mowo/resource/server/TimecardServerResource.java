package com.cummins.mowo.resource.server;

import org.restlet.resource.ServerResource;

import com.cummins.mowo.persistence.TimecardPersistence;
import com.cummins.mowo.persistence.entity.Timecard;
import com.cummins.mowo.representation.TimecardRepresentation;
import com.cummins.mowo.resource.TimecardResource;

public class TimecardServerResource extends ServerResource implements TimecardResource {

    private TimecardPersistence timecardPersistence;

    private Timecard timecard;

    private String id;
    
	
	public TimecardRepresentation represent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public TimecardRepresentation store(TimecardRepresentation timecardReprIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
