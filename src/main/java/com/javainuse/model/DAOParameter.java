package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "parameter")
public class DAOParameter implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "parkey")// parameter Key
	private String key;
	@Column(name = "parvalue")//parameter value
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