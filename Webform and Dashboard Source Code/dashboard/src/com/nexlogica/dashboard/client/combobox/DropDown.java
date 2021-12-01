package com.nexlogica.dashboard.client.combobox;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DropDown implements Serializable {


	private Integer id;
	private String name;

	private static int COUNTER = 0;

	public DropDown() {
		this.id = Integer.valueOf(COUNTER++);
	}

	public DropDown(String name) {
		this();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return getName();
	}

}