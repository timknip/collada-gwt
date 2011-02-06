package com.floorplanner.collada.core.data;

import com.floorplanner.collada.core.DaeDocument;
import com.google.gwt.xml.client.Node;

public class DaeFloatArray extends DaeDataArray {
	private float[] data;
	
	public DaeFloatArray(DaeDocument document) {
		super(document);
	}
	
	public DaeFloatArray(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		data = readFloatArray(node);
	}
	
	public float[] getData() {
		return data;
	}
}
