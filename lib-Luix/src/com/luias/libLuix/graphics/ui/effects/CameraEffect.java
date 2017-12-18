package com.luias.libLuix.graphics.ui.effects;

import com.luias.libLuix.cores.Utils;
import com.luias.libLuix.graphics.ui.effects.Effect.Camera_Effect;
import com.luias.libLuix.graphics.ui.effects.Effect.Camera_QuakeEffect;
import com.luias.libLuix.graphics.ui.effects.Effect.Camera_WaveEffect;

public class CameraEffect {

	private static float speed = 7.2f;
	
	public static boolean WaveEffect(Camera_WaveEffect effect) {
		boolean endEffect = false;
		
		if(effect.getStartTime() <= 0) {
			effect.setStartTime(System.currentTimeMillis());
		}
		
		if(effect.isHorizontal()) {
			effect.setAngle(effect.getAngle() + speed * 1.5f);
			effect.setCam_X(effect.getCam_X() + Math.sin(Math.toRadians(effect.getAngle())));
			effect.getBatch().translate(Math.sin(Math.toRadians(effect.getAngle())), 0);
		}else {
			effect.setAngle(effect.getAngle() + speed * 1.5f);
			effect.setCam_Y(effect.getCam_Y() + Math.sin(Math.toRadians(effect.getAngle())));
			effect.getBatch().translate(0, Math.sin(Math.toRadians(effect.getAngle())));
		}
		
		if (effect.getAngle() >= 360.0f)
			effect.setAngle(0.0f);
		
		long endTime = System.currentTimeMillis();
		if( (endTime - effect.getStartTime()) >= effect.getDuration()) {
			setOriginCenter(effect);
			endEffect = true;
		}
		
		return endEffect;
	}
	
	public static boolean QuakeEffect(Camera_QuakeEffect effect) {
		boolean endEffect = false;
		
		if(effect.getStartTime() <= 0) {
			effect.setStartTime(System.currentTimeMillis());
		}
		
		float angle = (float)(Math.cos(Math.toRadians(Utils.rand.nextInt(360)))*Utils.rand.nextInt(3));
		
		if(effect.isHorizontal()) {
			effect.setCam_X(effect.getCam_X() + angle);
			effect.getBatch().translate(angle, 0.0);
		}else {
			effect.setCam_Y(effect.getCam_X() + angle);
			effect.getBatch().translate(0.0, angle);
		}
		
		long endTime = System.currentTimeMillis();
		if( (endTime- effect.getStartTime()) >= effect.getDuration()) {
			setOriginCenter(effect);
			endEffect = true;
		}
		
		return endEffect;
		
	}
	
	public static void setOriginCenter(Camera_Effect effect) {
		effect.getBatch().translate( effect.getCam_X() * -1, effect.getCam_Y()  * -1);
		effect.setCam_X(0.0f);
		effect.setCam_Y(0.0f);
	}
	
}
