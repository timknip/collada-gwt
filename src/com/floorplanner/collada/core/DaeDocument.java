package com.floorplanner.collada.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.floorplanner.collada.fx.DaeEffect;
import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.mouchel.gwt.xpath.client.XPath;

public class DaeDocument  {

	private Document document;
	private DaeNode rootNode;
	private Map<String, DaeGeometry> geometries;
	private Map<String, DaeSource> sources;
	
	public DaeDocument() {
		this.document = null;
		geometries = new HashMap<String, DaeGeometry>();
		sources = new HashMap<String, DaeSource>();
	}
	
	public DaeDocument(Document document) {
		this();
		this.document = document;
	}
	
	/**
	 * Gets a geometry by its ID.
	 * 
	 * @param id
	 * @return
	 */
	public DaeGeometry getGeometryByID(String id) {
		if (geometries.containsKey(id)) {
			return geometries.get(id);
		} else {
			Node node = XPath.evaluateSingle(document, "//geometry[@id='"+id+"']");
			if (node != null) {
				DaeGeometry geometry = new DaeGeometry(this, node);
				if (geometry.getID() != null) {
					geometries.put(geometry.getID(), geometry);
					return geometry;
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets a source by its ID.
	 * 
	 * @param id
	 * @return
	 */
	public DaeSource getSourceByID(String id) {
		if (sources.containsKey(id)) {
			return sources.get(id);
		} else {
			Node node = XPath.evaluateSingle(document, "//source[@id='"+id+"']");
			if (node != null) {
				DaeSource source = new DaeSource(this, node);
				if (source.getID() != null) {
					sources.put(source.getID(), source);
					return source;
				}
			} else {
				Log.error("Could not find source with id=" + id + " !");
			}
		}
		return null;
	}
	
	public void readScene() {
		Node node = XPath.evaluateSingle(document, "//scene[1]/instance_visual_scene[1]/@url");
		
		if (node != null) {
			readVisualScene(node.getNodeValue());
		} else {
			GWT.log("Could not find a scene!");
		}
		
		List<Node> list = XPath.evaluate(document, "//effect");
		for (Node effectNode: list) {
			DaeEffect effect = new DaeEffect(this, effectNode);
		}
	}
	
	public void readVisualScene(String url) {
		if (url.startsWith("#")) {
			url = url.substring(1);
		}
		Node node = XPath.evaluateSingle(document, "//visual_scene[@id='"+ url +"']");
		
		if (node != null) {
			rootNode = new DaeNode(this, node);
		} else {
			
		}
	}
	
	public Document getDocument() {
		return document;
	}
	
	public DaeNode getRootNode() {
		return rootNode;
	}
}
