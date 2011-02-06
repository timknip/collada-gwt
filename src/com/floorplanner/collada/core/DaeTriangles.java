package com.floorplanner.collada.core;

import java.util.Vector;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeTriangles extends DaePrimitive {
	private int[] p;
	
	public DaeTriangles(DaeDocument document, DaeMesh mesh) {
		super(document, mesh);
	}
	
	public DaeTriangles(DaeDocument document, Node node, DaeMesh mesh) {
		super(document, node, mesh);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		p = null;
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		p = null;
		
		Vector<DaeInput> inputs = new Vector<DaeInput>();
		NodeList list = node.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("input") == 0) {
				DaeInput input = new DaeInput(getDocument(), child);
				if (getMesh().getVerticesID().compareTo(input.getSource()) == 0) {
					input.setSource(getMesh().getVertices().getID());
				}
				inputs.add(input);
			} else if (nodeName.compareTo("p") == 0) {
				p = readIntArray(child);
			}
		}

		if (p != null && p.length > 0 && inputs.size() > 0) {
			readTriangles(p, inputs);
		}
	}
	
	private void readTriangles(int[] p, Vector<DaeInput> inputs) {
		int current = 0;
		int maxOffset = 0;
		
		for (DaeInput input: inputs) {
			maxOffset = Math.max(maxOffset, input.getOffset());
		}

		while (current < p.length) {
			for (int i = 0; i < inputs.size(); i++) {
				DaeInput input = inputs.elementAt(i);
				DaeSource source = getDocument().getSourceByID(input.getSource());
				int index = p[current + input.getOffset()];
				
				addIndex(input, index, source.getAccessor().getParams().size());
			}
			current += (maxOffset + 1);
		}
	}
}
