package com.luias.libLuix.cores;

public class Configurations {

	private static Configurations INSTANCE = new Configurations();
	
	public static Configurations getInstance() {
		return INSTANCE;
	}
	
	public static float KEY_REPEAT_TIME = 250.0f; // 0.25sec
	public static float MOUSE_REPEAT_TIME = 150.0f; // 0.15sec
	
	public boolean showFPS = false;
	public boolean resizeable = false;
	
	public int width;
	public int height;
	public float fps_limit = 60.0f;
	
	public void setConfig(Configurations config) {
		Configurations.INSTANCE = config;
	}
	
}