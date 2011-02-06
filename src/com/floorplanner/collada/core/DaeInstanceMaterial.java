package com.floorplanner.collada.core;

import com.google.gwt.xml.client.Node;

public class DaeInstanceMaterial extends DaeElement {
	private String symbol;
	private String target;
	
	public DaeInstanceMaterial(DaeDocument document) {
		super(document);
	}
	
	public DaeInstanceMaterial(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		symbol = readAttribute(node, "symbol", true);
		target = readAttribute(node, "target", true);
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getTarget() {
		return target;
	}
}
