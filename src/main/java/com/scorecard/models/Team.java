package com.scorecard.models;

public class Team {
	private Integer id;
	private TeamType teamtype;
	private Player captain;
	private Player wicketkepper;
	private String teamname;


	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname=teamname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeamType getTeamtype() {
		return teamtype;
	}

	public void setTeamtype(TeamType teamtype) {
		this.teamtype = teamtype;
	}
	public Player getCaptain() {
		return captain;
	}

	public void setCaptain(Player captain) {
		this.captain = captain;
	}
	public Player getWicketkepper() {
		return wicketkepper;
	}

	public void setWicketkepper(Player wicketkepper) {
		this.wicketkepper = wicketkepper;
	}
}
