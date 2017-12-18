package com.luias.libLuix.cores.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.luias.libLuix.graphics.Scene;

public class ResourceManager {

	public static HashMap <Scene, SceneResourceFolder> resourcePool = new HashMap <Scene, SceneResourceFolder>();
	
	public static BufferedImage loadImage(String path) {
		
		try {
			
			return ImageIO.read(new File(path));

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static File loadSoundFile(String path) {
		
		try {
			
			return new File(path);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void Dispose_SceneResourceFolder(Scene scene) {
		
		SceneResourceFolder folder = resourcePool.get(scene);
		folder.removeAllLayer();
		resourcePool.remove(scene);
		
	}
	
	public static SceneResourceFolder getImageFolder(Scene scene) {
		return resourcePool.get(scene);
	}
	
}
