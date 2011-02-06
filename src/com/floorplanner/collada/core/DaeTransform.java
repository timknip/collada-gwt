package com.floorplanner.collada.core;

import com.google.gwt.xml.client.Node;

public class DaeTransform extends DaeElement {
	
	private String type;
	private float[] data;
	
	public DaeTransform(DaeDocument document) {
		super(document);
	}
	public DaeTransform(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		type = node.getNodeName();
		
		if (node.getChildNodes().getLength() == 1 && node.getChildNodes().item(0).getNodeType() == Node.TEXT_NODE) {
			String raw = node.getChildNodes().item(0).getNodeValue();
			String[] parts = raw.trim().split("\\s");
			
			data = new float[parts.length];
			for (int i = 0; i < parts.length; i++) {
				data[i] = Float.parseFloat(parts[i]);
			}
		}
	}
	
	public float[] getData() {
		return data;
	}
	
	public String getType() {
		return type;
	}
}
