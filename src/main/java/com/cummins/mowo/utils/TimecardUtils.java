package com.cummins.mowo.utils;

import com.cummins.mowo.persistence.entity.Timecard;
import com.cummins.mowo.representation.TimecardRepresentation;

public class TimecardUtils {
	
	 public static Timecard timecardRepresentationToTimecard(
	            TimecardRepresentation timecardRepr) {
	        if (timecardRepr != null) {
	            Timecard timecard = new Timecard();
	            timecard.setClockInTime(timecardRepr.getPunch_start());
	            timecard.setClockOutTime(timecardRepr.getPunch_end());
	            return timecard;
	        }
	        return null;
	    }

	    public static TimecardRepresentation timecardToTimecardRepresentation(
	            Timecard timecard) {
	        if (timecard != null) {
	            TimecardRepresentation timecardRepr = new TimecardRepresentation();
	            timecardRepr.setPunch_start(timecard.getClockInTime());
	            timecardRepr.setPunch_end(timecard.getClockOutTime());
	            return timecardRepr;
	        }
	        return null;
	    }
}
