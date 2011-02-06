package com.floorplanner.collada.fx;

import com.floorplanner.collada.core.DaeDocument;
import com.google.gwt.xml.client.Node;

public class DaeLambert extends DaeConstant {
	
	private DaeColorOrTexture ambient;
	private DaeColorOrTexture diffuse;

	public DaeLambert(DaeDocument document) {
		super(document);
	}
	
	public DaeLambert(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
	}
	
	public DaeColorOrTexture getAmbient() {
		return ambient;
	}
	
	public DaeColorOrTexture getDiffuse() {
		return diffuse;
	}
	
	public void setAmbient(DaeColorOrTexture value) {
		ambient = value;
	}
	
	public void setDiffuse(DaeColorOrTexture value) {
		diffuse = value;
	}
}

