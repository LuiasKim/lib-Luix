package com.luias.libLuix.graphics;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.luias.libLuix.graphics.ui.effects.Effect;

public class Camera{
	
	public float width, height;
	public Graphics2D batch;
	private ArrayList <Effect> effects = new ArrayList <Effect> (); 
	
	public Camera(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void setBatch(Graphics2D batch) {
		this.batch = batch;
	}
	
	public void doEffect() {
		
		for(int i = 0; i < effects.size(); i++) {
			
			Effect effect = effects.get(i);
			if(!effect.validateCheck()) {
				if(effect.autoDelete() || effect.isDelete()) {
					effects.remove(i);
					i = 0;
					continue;
				}
			}
			
			effect.doEffect();
			
		}
		
	}
	
	public void addEffect(Effect effect) {
		this.effects.add(effect);
	}
	
	
}
