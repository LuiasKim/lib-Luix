package com.luias.libLuix.objects.attribs;

public interface Moveable {

	public boolean nowMoveable = false;
	
	public void move(float vx, float vy);
	public void jump(float vy);
	
	public void nuckBack(float vx);
	
}
