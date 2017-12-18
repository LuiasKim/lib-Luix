package com.luias.libLuix.graphics;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.luias.libLuix.cores.Configurations;
import com.luias.libLuix.cores.resource.SceneResourceFolder;

public abstract class Scene extends Canvas{
	private static final long serialVersionUID = 1L;
	
	protected BufferedImage image;
	protected BufferStrategy buffers;
	public Graphics2D batch;
	public Camera camera;
	public SceneResourceFolder resources;
	
	public Scene() {
		create();
	}
	
	protected void createGraphics() {
		camera = new Camera(Configurations.getInstance().width, Configurations.getInstance().height);
		batch = image.createGraphics();
		camera.setBatch(batch);
	}
	
	public abstract void create();
	public abstract void render(float dt);
	public abstract void updater(float dt);
	public abstract void destroy();
	
}
