package com.scorecard.models;

import java.sql.Date;

public class Match {
	private Integer id;
	private Integer matchtype;
	private String vengue;
	private Date startdate;
	private Integer tosswinner;
	private Integer batfirst;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatchType() {
		return matchtype;
	}
	public void setMatchType(Integer matchtype) {
		this.matchtype = matchtype;
	}
	public String getVengue() {
		return vengue;
	}

	public void setVengue(String vengue) {
		this.vengue = vengue;
	}
	public Date getStartDate() {
		return startdate;
	}

	public void setStartDate(Date startdate) {
		this.startdate = startdate;
	}
	public Integer getTossWinner() {
		return tosswinner;
	}

	public void setTossWinner(Integer tosswinner) {
		this.tosswinner = tosswinner;
	}
	public Integer getBatFirst() {
		return batfirst;
	}

	public void setBatFirst(Integer batfirst) {
		this.batfirst = batfirst;
	}

}
