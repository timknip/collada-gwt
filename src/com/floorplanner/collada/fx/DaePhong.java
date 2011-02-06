package com.floorplanner.collada.fx;

import com.floorplanner.collada.core.DaeDocument;
import com.google.gwt.xml.client.Node;

public class DaePhong extends DaeLambert {
	
	private DaeColorOrTexture specular;
	private float shininess = 0.0f;
	
	public DaePhong(DaeDocument document) {
		super(document);
	}
	
	public DaePhong(DaeDocument document,  Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
	}
	
	public DaeColorOrTexture getSpecular() {
		return specular;
	}
	
	public float getShininess() {
		return shininess;
	}
	
	public void setSpecular(DaeColorOrTexture value) {
		specular = value;
	}
	
	public void setShininess(float value) {
		shininess = value;
	}
}
