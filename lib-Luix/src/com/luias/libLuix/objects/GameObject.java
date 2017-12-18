package com.luias.libLuix.objects;

import com.luias.libLuix.misc.Vector2;

public abstract class GameObject{

	protected Vector2 position, linearVelocity;
	
	public void setPosition(Vector2 vec) {
		this.position.set(vec);
	}
	
	public void setLinearVelocity(Vector2 vec) {
		this.linearVelocity.set(vec);
	}
	
	public void setPosition(float x, float y) {
		this.position.set(x, y);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}
	
	
	
}
