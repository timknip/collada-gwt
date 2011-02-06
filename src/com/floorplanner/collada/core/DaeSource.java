package com.floorplanner.collada.core;

import java.util.Vector;

import com.floorplanner.collada.core.data.DaeDataArray;
import com.floorplanner.collada.core.data.DaeFloatArray;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeSource extends DaeElement {
	public enum Type {
		INVALID,
		FLOAT,
		INT,
		NAME,
		BOOLEAN
	}
	
	private DaeAccessor accessor;
	private Type type;
	private DaeDataArray data;
	
	public DaeSource(DaeDocument document) {
		super(document);
	}
	public DaeSource(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		this.type = Type.INVALID;
		
		NodeList list = node.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("float_array") == 0) {
				this.type = Type.FLOAT;
				data = new DaeFloatArray(getDocument(), child);
			} else if (nodeName.compareTo("technique_common") == 0) {
				readTechniqueCommon(child);
			}
		}
	}
	
	public DaeAccessor getAccessor() {
		return accessor;
	}
	
	public float[] getFloats() {
		if (data instanceof DaeFloatArray) {
			return ((DaeFloatArray) data).getData();
		} else {
			return null;
		}
	}
	
	public DaeDataArray getData() {
		return data;
	}
	
	public Type getType() {
		return type;
	}
	
	public float[] readFloats(int index) {
		float[] floats = getFloats();
		if (floats != null && floats.length > 0 && accessor != null) {
			Vector<DaeParam> params = accessor.getParams();
			float[] result = new float[params.size()];
			for (int i = 0; i < params.size(); i++) {
				result[i] = floats[index + i];
			}
			return result;
		}
		return null;
	}
	
	private void readTechniqueCommon(Node node) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			if (nodeName.compareTo("accessor") == 0) {
				accessor = new DaeAccessor(getDocument(), child);
			}
		}
	}
}
