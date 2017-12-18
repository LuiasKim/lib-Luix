package com.luias.libLuix.cores.resource;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.luias.libLuix.graphics.Scene;
import com.luias.libLuix.graphics.ui.UIobject;

public class SceneResourceFolder {

	public ArrayList <Layer> layers; 
	
	private Scene scene;
	
	public SceneResourceFolder(Scene scene) {
		this.scene = scene;
		layers = new ArrayList <Layer>();
	}
	
	public Scene getOwnScene() {
		return scene;
	}
	
	public void addNewLayer(Layer layer) {
		
		int priority = layer.getPriority();
		if(layers.size() == 0) {
			layer.setPriority(0);
			layers.add(layer.getPriority(), layer);
			return;
		}
		
		if(layers.size() < priority) {
			layer.setPriority(layers.size()-1);
			layers.add(layer.getPriority(), layer);
			return;
		}
		
		this.layers.add(layer.getPriority(), layer);
	}
	
	public void addImageToLayer(Layer layer, String imageName, BufferedImage image) {
		layers.get(layer.getPriority()).addImage(imageName, image);
	}
	
	public void addUItoLayer(Layer layer, String UIname, UIobject ui) {
		layers.get(layer.getPriority()).addUIObject(UIname, ui);
	}
	
	public Layer getLayer(int index) {
		if(index >= layers.size())
			return null;
		
		return layers.get(index);
	}
	
	public void removeAllImages() {
		
		for(int i = 0; i < layers.size(); i++) {
			Layer layer = layers.get(i);
			layer.removeImages();
		}
		
	}
	
	public void removeLayer(int index) {
		if(index >= layers.size())
			return;
		
		Layer t_layer = layers.get(index);
		t_layer.removeImages();
		t_layer.removeUIs();
		
		layers.remove(index);
		t_layer = null;
	}
	
	public void removeAllLayer(){
		if(layers.size() <= 0)
			return;
		
		removeAllImages();
		for(int i = 0; i < layers.size(); i++) {
			Layer t_layer = layers.get(i);
			t_layer.removeUIs();
		}
		
		layers.clear();
	}
}
