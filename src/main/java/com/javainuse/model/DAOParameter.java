package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
public class DAOParameter {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "parkey")
	private String key;
	@Column(name = "parvalue")
	private String value;

	public DAOParameter() {
	}

	public DAOParameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}