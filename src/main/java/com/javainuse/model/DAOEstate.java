package com.javainuse.model;

import javax.persistence.*;

@Entity
@Table(name = "estate")
public class DAOEstate {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false,name = "estatesName")
	private String estatesName;
	@Column(nullable = false,name = "numberOfShares")
	private int numberOfShares;
	@Column(nullable = false,name = "cost")
	private int cost;
	@Column(name = "timestamp")
	private Long timestamp;
	@Column(name = "state")
	private boolean state=false;
	@Column(nullable = false,name = "buyer")
	private String buyers="";

	public DAOEstate() {
		timestamp=System.currentTimeMillis();
	}

	public DAOEstate(String estatesName, int numberOfShares, int cost, boolean state, String buyers) {
		this.estatesName = estatesName;
		this.numberOfShares = numberOfShares;
		this.cost = cost;
		this.state = state;
		this.buyers = buyers;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstatesName() {
		return estatesName;
	}

	public void setEstatesName(String estatesName) {
		this.estatesName = estatesName;
	}

	public int getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(int numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getBuyers() {
		return buyers;
	}

	public void setBuyers(String buyers) {
		this.buyers = buyers;
	}
}