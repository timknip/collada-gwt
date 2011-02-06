package com.floorplanner.collada.fx;

import com.allen_sauer.gwt.log.client.Log;
import com.floorplanner.collada.core.DaeDocument;
import com.floorplanner.collada.core.DaeElement;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeEffect extends DaeElement {
	
	public DaeEffect(DaeDocument document) {
		super(document);
	}
	
	public DaeEffect(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("profile_COMMON") == 0) {
				readProfileCommon(child);
			}
		}
	}
	
	private void readProfileCommon(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("technique") == 0) {
				readTechnique(child);
			}
		}
	}
	
	private void readTechnique(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("phong") == 0) {
				Log.debug(child.getNodeName());
				
				new DaePhong(getDocument(), child);
			}
		}
	}
}
