package com.cummins.mowo.persistence.entity;

/**
 * Reprensent the entity company stored in the database.
 * @author Aivars Pavluks
 *
 */
public class Timecard {
	
	private long clockInTime;
	private long clockOutTime;
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getClockInTime() {
		return clockInTime;
	}
	public void setClockInTime(long clockInTime) {
		this.clockInTime = clockInTime;
	}
	public long getClockOutTime() {
		return clockOutTime;
	}
	public void setClockOutTime(long clockOutTime) {
		this.clockOutTime = clockOutTime;
	}
	
}
