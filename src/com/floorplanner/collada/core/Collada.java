package com.floorplanner.collada.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;
import com.mouchel.gwt.xpath.client.XPath;

public class Collada {
	
	private DaeDocument daeDocument;
	private Document document;
	
	public Collada() {
		document = null;
	}
	
	public Element getSourceElementById(String id) {
		
		return (Element) XPath.evaluateSingle(document, "//source[@id='"+id+"']");
	}
	
	public void load(String url) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);
		try {
			rb.sendRequest(null, new RequestCallback() {
					
				@Override
				public void onResponseReceived(Request request, Response response) {
					document = parseXML(response.getText());		
				}
					
				@Override
				public void onError(Request request, Throwable exception) {
					GWT.log("Error while loading COLLADA file.");
				}
			});
		} catch (RequestException exception) {
			GWT.log("Error while loading COLLADA file.");
		}
	}
	
	public Document parseXML(String xmlString) {
		document = XMLParser.parse(xmlString);
		
		//parse(document.getDocumentElement());
		daeDocument = new DaeDocument(document);
		
		daeDocument.readScene();

		return document;
	}
	
	public Document getDocument() {
		return document;
	}
}
