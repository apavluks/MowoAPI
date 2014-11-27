package com.cummins.mowo.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import com.cummins.mowo.representation.TimecardListRepresentation;
import com.cummins.mowo.representation.TimecardRepresentation;

public interface TimecardListResource {

    @Get("json")
    public TimecardListRepresentation represent();

    @Post
    public TimecardRepresentation add(TimecardRepresentation timecardReprIn);

}