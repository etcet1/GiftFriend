package com.exam.giftfriend.sqlite.models;

import android.R.integer;

public class Gift {

	private int id;
	private String name;
	private int caegory_id;
	private int status;
	private String created;
	private String location;

	public Gift() {
	}

	public Gift(String name, int caegory_id) {
		this.setName(name);
		this.setCategory(caegory_id);
	}
	
	public Gift(String name, String location) {
		this.setName(name);
		this.setLocation(location);
	}

	public Gift(int id, String name, int category_id, String location) {
		this.setId(id);
		this.setName(name);
		this.setCategory(category_id);
		this.setLocation(location);
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(int category_id) {
		this.caegory_id = category_id;
	}

	public void setCreatedAt(String created) {
		this.created = created;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	// getters
	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public String getLocation(){
		return this.location;
	}

	public int getCategoryId() {
		return this.caegory_id;
	}
	
	public int getStatus() {
		return this.status;
	}
}
