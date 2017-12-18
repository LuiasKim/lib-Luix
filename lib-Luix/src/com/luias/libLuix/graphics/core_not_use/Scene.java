package com.luias.libLuix.graphics.core_not_use;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Scene extends Pixelmap{

	public BufferedImage image;
	
	public Scene(int w, int h) {
		super(w, h);
		
		
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
	}
	
}
