package com.luias.libLuix.cores.resource;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.luias.libLuix.graphics.ui.UIobject;

public class Layer {

	public HashMap <String, BufferedImage> images;
	public HashMap <String, UIobject> UIs;
	
	private int layer_priority;
	
	public Layer(int layer_priority) {
		
		this.layer_priority = layer_priority;
		images = new HashMap<String, BufferedImage>();
		UIs = new HashMap <String, UIobject>();
		
	}
	
	public void setPriority(int priority) {
		this.layer_priority = priority;
	}
	
	public int getPriority() {
		return this.layer_priority;
	}
	
	public void addImage(String name, BufferedImage image) {
		images.put(name, image);
	}
	
	public void addUIObject(String name, UIobject object) {
		UIs.put(name, object);
	}
	
	public void removeImages() {
		
		Collection <BufferedImage> image = images.values();
		Iterator <BufferedImage> iter = image.iterator();
		
		while(iter.hasNext()) {
			BufferedImage img = iter.next();
			img.getGraphics().dispose();
			img = null;
		}
		
		images.clear();
	}
	
	public void removeUIs() {
		UIs.clear();
	}
}
