package com.floorplanner.collada.core;

import com.google.gwt.xml.client.Node;

public class DaeInput extends DaeElement {
	private int set;
	private int offset;
	private String semantic;
	private String source;
	
	public DaeInput(DaeDocument document) {
		super(document);
	}
	
	public DaeInput(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		semantic = readAttribute(node, "semantic");
		source = readAttribute(node, "source", true);
		offset = readIntAttribute(node, "offset", 0);
		set = readIntAttribute(node, "set", 0);
	}
	
	public String getSemantic() {
		return semantic;
	}
	
	public String getSource() {
		return source;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getSet() {
		return set;
	}
	
	public void setSource(String value) {
		source = value;
	}
}
