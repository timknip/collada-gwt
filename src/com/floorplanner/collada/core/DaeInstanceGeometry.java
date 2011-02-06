package com.floorplanner.collada.core;

import java.util.Vector;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeInstanceGeometry extends DaeElement {
	
	private String url;
	private DaeGeometry geometry;
	private Vector<DaeBindMaterial> boundMaterials;
	
	public DaeInstanceGeometry(DaeDocument document) {
		super(document);
	}
	
	public DaeInstanceGeometry(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		url = readAttribute(node, "url", true);
		
		boundMaterials = new Vector<DaeBindMaterial>();
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("bind_material") == 0) {
				boundMaterials.add(new DaeBindMaterial(getDocument(), child));
			}
		}
	}
	
	public Vector<DaeBindMaterial> getBoundMaterials() {
		return boundMaterials;
	}
	
	public DaeGeometry getGeometry() {
		return geometry;
	}
	
	public String getURL() {
		return url;
	}
	
	public void setGeometry(DaeGeometry value) {
		geometry = value;
	}
}
