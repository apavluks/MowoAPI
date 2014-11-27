package com.cummins.mowo.representation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

//@JacksonXmlRootElement(localName = "timecard")
//@JsonInclude(Include.NON_NULL)
//@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class TimecardRepresentation {
	@JsonProperty("punch_start")
	private long punch_start;
	private long punch_end;
	private String self;
	
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public long getPunch_start() {
		return punch_start;
	}
	public void setPunch_start(long punch_start) {
		this.punch_start = punch_start;
	}
	public long getPunch_end() {
		return punch_end;
	}
	public void setPunch_end(long punch_end) {
		this.punch_end = punch_end;
	}
	
	
}
