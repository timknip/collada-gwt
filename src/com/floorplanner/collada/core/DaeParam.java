package com.floorplanner.collada.core;

public class DaeParam {
	private String name;
	private String type;
	
	public DaeParam() {
		name = type = null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String value) {
		name = value;
	}
	
	public void setType(String value) {
		type = value;
	}
}
