package com.luias.libLuix.cores;

import java.util.Random;

public class Utils {

	public static Random rand = new Random();
	
	public static float getDistance(float x1, float y1, float x2, float y2) {
		return (float)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}
	
	public static float getAngle(float x1, float y1, float x2, float y2) {
		
		float xx = x2 - x1;
		float yy = y2 - y1;
		
		float atan = (float)Math.atan2(yy, xx);
		
		return (float)(Math.toDegrees(Math.toRadians(atan)));
		
	}
}
