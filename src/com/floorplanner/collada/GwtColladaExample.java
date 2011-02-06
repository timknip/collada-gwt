package com.floorplanner.collada;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.collada._2005._11.colladaschema.COLLADA;

import com.floorplanner.collada.core.Collada;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.impl.DOMParseException;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtColladaExample implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final VerticalPanel panel = new VerticalPanel();
		final PushButton button = new PushButton();
		
		final COLLADA c = null;
		
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("org.collada._2005._11.colladaschema");
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL("http://beaker.east/nosferatu.xml");
			u.unmarshal(url);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		button.setText("load collada");
		panel.add(button);
		
		panel.setSize("100%", "100%");
		
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				loadCollada("/teapot.dae");
			}
		});
		RootPanel.get().add(panel);
	}
	
	private void loadCollada(String url) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
			rb.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					
					try {
						// get rid of namespace
						String xmlns = "http://www.collada.org/2005/11/COLLADASchema";
						String xmlString = response.getText();
						
						xmlString = xmlString.replace(xmlns, "");
						
						GWT.log(xmlString);
						
						Collada collada = new Collada();
						collada.parseXML(xmlString);
					} catch (DOMParseException parseException) {
						GWT.log("parse failed");
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					GWT.log("Collada file failed to load!");
				}
			});
		} catch (RequestException exception) {
			GWT.log("Error while loading Collada file!");
		}
	}
}