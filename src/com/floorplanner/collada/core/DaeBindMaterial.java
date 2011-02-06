package com.floorplanner.collada.core;

import java.util.Vector;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeBindMaterial extends DaeElement {
	
	Vector<DaeInstanceMaterial> materials;
	
	public DaeBindMaterial(DaeDocument document) {
		super(document);
	}
	
	public DaeBindMaterial(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		materials = new Vector<DaeInstanceMaterial>();
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("technique_common") == 0) {
				readTechniqueCommon(child);
			}
		}
	}
	
	private void readTechniqueCommon(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("instance_material") == 0) {
				DaeInstanceMaterial material = new DaeInstanceMaterial(getDocument(), child);
				materials.add(material);
			}
		}
	}
	
	public Vector<DaeInstanceMaterial> getMaterials() {
		return materials;
	}
}
