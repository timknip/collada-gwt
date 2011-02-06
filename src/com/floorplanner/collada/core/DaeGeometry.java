package com.floorplanner.collada.core;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeGeometry extends DaeElement {

	private DaeMesh mesh;
	
	public DaeGeometry(DaeDocument document) {
		super(document);
	}
	
	public DaeGeometry(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		if (mesh != null) {
			mesh.destroy();
			mesh = null;
		}
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		mesh = null;
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("mesh") == 0) {
				mesh = new DaeMesh(getDocument(), child);
			}
		}
	}
	
	public DaeMesh getMesh() {
		return mesh;
	}
}
