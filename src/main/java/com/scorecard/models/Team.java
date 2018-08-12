package com.scorecard.models;

public class Team {
	private Integer id;
	private Integer teamtype;
	private Integer captain;
	private Integer wicketkepper;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamType() {
		return teamtype;
	}

	public void setTeamType(Integer teamtype) {
		this.teamtype = teamtype;
	}
	public Integer getCaptain() {
		return captain;
	}

	public void setCaptain(Integer captain) {
		this.captain = captain;
	}
	public Integer getWicketKepper() {
		return wicketkepper;
	}

	public void setWicketKepper(Integer wicketkepper) {
		this.wicketkepper = wicketkepper;
	}


}
