package com.scorecard.models;

public class Match {
	private Integer id;
	private Integer matchtype;
	private String vengue;
	private Integer team1;
	private Integer team2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVengue() {
		return vengue;
	}

	public void setVengue(String vengue) {
		this.vengue = vengue;
	}

	public Integer getMatchtype() {
		return matchtype;
	}

	public void setMatchtype(Integer matchtype) {
		this.matchtype = matchtype;
	}

	public Integer getTeam1() {
		return team1;
	}

	public void setTeam1(Integer team1) {
		this.team1 = team1;
	}

	public Integer getTeam2() {
		return team2;
	}

	public void setTeam2(Integer team2) {
		this.team2 = team2;
	}

}
