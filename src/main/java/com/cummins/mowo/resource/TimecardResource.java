package com.cummins.mowo.resource;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import com.cummins.mowo.representation.TimecardRepresentation;

public interface TimecardResource {
	
	@Get
    public TimecardRepresentation represent();

    @Delete
    public void remove();

    @Put
    public TimecardRepresentation store(TimecardRepresentation timecardReprIn);

}
