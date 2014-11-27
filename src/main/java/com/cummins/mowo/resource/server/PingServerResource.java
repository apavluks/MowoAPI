package com.cummins.mowo.resource.server;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.cummins.mowo.MowoAPIApplication;

public class PingServerResource  extends ServerResource{

    @Get("txt")
    public String represent() {
        return MowoAPIApplication.PING;
    }
    
}
