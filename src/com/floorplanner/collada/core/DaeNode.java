package com.floorplanner.collada.core;

import java.util.Vector;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeNode extends DaeElement {

	private Vector<DaeNode> nodes;
	private Vector<DaeInstanceGeometry> geometries;
	private Vector<DaeTransform> transforms;
	
	public DaeNode(DaeDocument document) {
		super(document);
	}
	
	public DaeNode(DaeDocument document, Element element) {
		super(document, element);
	}
	
	public DaeNode(DaeDocument document, Node node) {
		super(document, node);
	}
	
	public DaeInstanceGeometry getGeometryByID(String id) {
		for (DaeInstanceGeometry geometry: geometries) {
			if (geometry.getURL() != null && geometry.getURL().compareTo(id) == 0) {
				return geometry;
			}
		}
		return null;
	}
	
	public DaeNode getNodeByID(String id) {
		for (DaeNode node: nodes) {
			if (node.getID().compareTo(id) == 0) {
				return node;
			}
		}
		return null;
	}
	
	public Vector<DaeInstanceGeometry> getGeometries() {
		return geometries;
	}
	
	public Vector<DaeNode> getNodes() {
		return nodes;
	}
	
	@Override
	public void read(Node node) {
		super.read(node);

		nodes = new Vector<DaeNode>();
		geometries = new Vector<DaeInstanceGeometry>();
		transforms = new Vector<DaeTransform>();
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("node") == 0) {
				DaeNode n = new DaeNode(getDocument(), child);
				if (n.getID() != null) {
					nodes.add(n);
				}
			} else if (nodeName.compareTo("matrix") == 0 || 
					   nodeName.compareTo("rotate") == 0 || 
					   nodeName.compareTo("translate") == 0 ||
					   nodeName.compareTo("skew") == 0 ||
					   nodeName.compareTo("lookat") == 0 ||
					   nodeName.compareTo("scale") == 0) {
				readTransform(child);
			} else if (nodeName.compareTo("instance_geometry") == 0) {
				String geomId = readAttribute(child, "url", true);
				if (geomId != null) {
					DaeGeometry geometry = getDocument().getGeometryByID(geomId);
					if (geometry != null && geometry.getID() != null) {
						DaeInstanceGeometry instanceGeometry = new DaeInstanceGeometry(getDocument(), child);
						instanceGeometry.setGeometry(geometry);
						geometries.add(instanceGeometry);
					}
				}
			}
		}
	}
	
	private void readTransform(Node node) {
		DaeTransform transform = new DaeTransform(getDocument(), node);
		if (transform.getData() != null && transform.getData().length > 0) {
			transforms.add(transform);
		}
	}
}
