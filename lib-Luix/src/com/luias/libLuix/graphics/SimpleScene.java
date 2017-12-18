package com.luias.libLuix.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.luias.libLuix.cores.Configurations;
import com.luias.libLuix.cores.Container;
import com.luias.libLuix.cores.resource.ResourceManager;
import com.luias.libLuix.cores.resource.SceneResourceFolder;

public abstract class SimpleScene extends Scene{
	private static final long serialVersionUID = 1L;
	
	public SimpleScene() {
		super();
	}
	
	@Override
	public void create() {
		image = new BufferedImage(Configurations.getInstance().width, Configurations.getInstance().height, BufferedImage.TYPE_INT_ARGB);
		resources = new SceneResourceFolder(this);
		ResourceManager.resourcePool.put(this, resources);
		this.createGraphics();
	}
	
	@Override
	public void render(float dt) {
		if(this.getBufferStrategy() == null) {
			this.createBufferStrategy(3);
			buffers = this.getBufferStrategy();
			return;
		}
		
		camera.doEffect();
		
		
		
		Graphics2D graphic = (Graphics2D)buffers.getDrawGraphics();
		graphic.clearRect(0, 0, Configurations.getInstance().width, Configurations.getInstance().height);
		
		graphic.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
		
		graphic.setColor(Color.white);
		if (Configurations.getInstance().showFPS)
			graphic.drawString(String.format("FPS: %.1f", Container.fps), 10.0f, 20.0f);
		
		buffers.show();
	}
	
	@Override
	public void destroy() {
		image = null;
		ResourceManager.Dispose_SceneResourceFolder(this);
	}
	
	
}
