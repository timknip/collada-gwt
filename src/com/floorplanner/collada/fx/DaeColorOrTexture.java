package com.floorplanner.collada.fx;

import com.allen_sauer.gwt.log.client.Log;
import com.floorplanner.collada.core.DaeDocument;
import com.floorplanner.collada.core.DaeElement;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeColorOrTexture extends DaeElement {

	private boolean _isTexture;
	private float[] color;
	
	public DaeColorOrTexture(DaeDocument document) {
		super(document);
	}
	
	public DaeColorOrTexture(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("color") == 0) {
				Log.info(node.getNodeName() + " color: " + readFloatArray(child));
			} else if (nodeName.compareTo("float") == 0) {
				Log.info(node.getNodeName() +" float: " + readFloatArray(child)[0]);
			}
		}
	}
	
	public boolean isTexture() {
		return _isTexture;
	}
}
