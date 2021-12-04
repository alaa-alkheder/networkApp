package com.javainuse.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class DAOLog {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "obj")
	private String obj;
	@Column(name = "date")
	private Date date;


	public DAOLog() {
		date=new Date();
	}

	public DAOLog(String name, String obj) {
		this.name = name;
		this.obj = obj;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}