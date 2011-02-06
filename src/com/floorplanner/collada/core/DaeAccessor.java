package com.floorplanner.collada.core;

import java.util.Vector;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeAccessor extends DaeElement {
	private Vector<DaeParam> params;
	
	public DaeAccessor(DaeDocument document) {
		super(document);
	}
	
	public DaeAccessor(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		params = new Vector<DaeParam>();
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("param") == 0) {
				DaeParam param = new DaeParam();
				param.setName(readAttribute(child, "name"));
				param.setType(readAttribute(child, "type"));
				params.add(param);
			}
		}
	}
	
	public Vector<DaeParam> getParams( ){
		return params;
	}
}
