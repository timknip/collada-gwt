package com.floorplanner.collada.fx;

import org.mortbay.log.Log;

import com.floorplanner.collada.core.DaeDocument;
import com.floorplanner.collada.core.DaeElement;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class DaeConstant extends DaeElement {
	
	private DaeColorOrTexture emission;
	private DaeColorOrTexture reflective;
	private float reflectivity = 0.0f;
	private DaeColorOrTexture transparent;
	private float transparency = 0.0f;
	private float index_of_refraction = 0.0f;

	public DaeConstant(DaeDocument document) {
		super(document);
	}
	
	public DaeConstant(DaeDocument document, Node node) {
		super(document, node);
	}
	
	@Override
	public void read(Node node) {
		super.read(node);
		
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			String nodeName = child.getNodeName();
			
			if (nodeName.compareTo("emission") == 0) {
				emission = new DaeColorOrTexture(getDocument(), child);
			} else if (nodeName.compareTo("reflective") == 0) {
				reflective = new DaeColorOrTexture(getDocument(), child);
			} else if (nodeName.compareTo("transparent") == 0) {
				transparent = new DaeColorOrTexture(getDocument(), child);
			} else if (nodeName.compareTo("reflectivity") == 0) {
				DaeColorOrTexture cotr = new DaeColorOrTexture(getDocument(), child);
				//reflectivity = readFloatArray(child)[0];
				//Log.debug("" + readFloatArray(child));
			} else if (nodeName.compareTo("transparency") == 0) {
				DaeColorOrTexture cott = new DaeColorOrTexture(getDocument(), child);
			} else if (nodeName.compareTo("index_of_refraction") == 0) {
				DaeColorOrTexture coti = new DaeColorOrTexture(getDocument(), child);
				//index_of_refraction = readFloatArray(child)[0];
			}  
		}
	}
	
	public DaeColorOrTexture getEmission() {
		return emission;
	}
	
	public float getIndexOfRefraction() {
		return index_of_refraction;
	}
	
	public DaeColorOrTexture getReflective() {
		return reflective;
	}
	
	public float getReflectivity() {
		return reflectivity;
	}
	
	public float getTransparency() {
		return transparency;
	}
	
	public DaeColorOrTexture getTransparent() {
		return transparent;
	}
	
	public void setEmission(DaeColorOrTexture value) {
		emission = value;
	}
	
	public void setIndexOfRefraction(float value) {
		index_of_refraction = value;
	}
	
	public void setReflective(DaeColorOrTexture value) {
		reflective = value;
	}
	
	public void setReflectivity(float value) {
		reflectivity = value;
	}
	
	public void setTransparency(float value) {
		transparency = value;
	}
	
	public void setTransparent(DaeColorOrTexture value) {
		transparent = value;
	}
}

