package com.luias.libLuix.misc;

public class Vector2 {
	public static final float radiansToDegrees = 57.295776f;
	
	float x, y;
	
	public Vector2() {}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	// ================= Calculations ===================
	
	public float len() {
		return (float)Math.sqrt( (x * x) + (y * y) );
	}
	
	public float len(float x, float y) {
		return (float)Math.sqrt( (x * x) + (y * y));
	}
	
	public float len2() {
		return x * x + y * y;
	}
	
	public void nor() {
		
		float v = len();
		
		if(v != 0) {
			this.x /= v;
			this.y /= v;
		}
		
	}
	
	public void add(float value) {
		this.x += value;
		this.y += value;
	}
	
	public void add(Vector2 vec) {
		this.x += vec.x;
		this.y += vec.y;
	}
	
	public void sub(float value) {
		this.x -= value;
		this.y -= value;
	}
	
	public void sub(Vector2 vec) {
		this.x -= vec.x;
		this.y -= vec.y;
	}
	
	public void scl(float value) {
		this.x *= value;
		this.y *= value;
	}
	
	public void scl(float x, float y) {
		this.x *= x;
		this.y *= y;
	}
	
	public void mulAdd(Vector2 vec, float value) {
		this.x += vec.x * value;
		this.y += vec.y * value;
	}
	
	public void mulAdd(Vector2 vec, Vector2 vec2) {
		this.x += vec.x * vec.x;
		this.y += vec.y * vec.y;
	}
	
	public float dist(Vector2 vec) {
		
		float dx = vec.x - this.x;
		float dy = vec.y - this.y;
		return (float)Math.sqrt( dx * dx + dy * dy);
	}
	
	public float dist(float x, float y) {
		return dist(new Vector2(x, y));
	}
	
	public float dist2(float x, float y) {
		float dx = x - this.x;
		float dy = y - this.y;
		
		return (dx * dx + dy * dy);
	}
	
	public float dist2(Vector2 vec) {
		return dist2(vec.x, vec.y);
	}
	
	public Vector2 clamp(float min, float max) {
		
		float len2 = len2();
		if(len2 == 0f) return this;
		
		float max2 = max * max;
		if(len2 > max2) {
			scl((float)Math.sqrt(max2 / len2));
			return this;
		}
		
		float min2 = min * min;
		if(len2 < min2) {
			scl((float)Math.sqrt(min2 / len2));
			return this;
		}
		
		return this;
		
	}
	
	public float dot(float x, float y) {
		return this.x * x + this.y * y;
	}
	
	public float dot(Vector2 vec) {
		return dot(vec.x, vec.y);
	}
	
	public float dot_cos(float x, float y) {
		float v = dot(x, y);
		return v / (this.len() * len(x, y));
	}
	
	public float dot_cos(Vector2 vec) {
		
		float v = (this.x * vec.x) + (this.y * vec.y);
		
		return v / (this.len() * vec.len());
	}
	
	public float dot_theta_radian(float x, float y) {
		float cos = dot_cos(x, y);
		
		return (float)Math.acos(cos);
	}
	
	public float dot_theta_radian(Vector2 vec) {
		
		float cos = dot_cos(vec);
		
		return (float)Math.acos(cos);
	}
	
	public float dot_theta_degree(float x, float y) {
		float radian = dot_theta_radian(x, y);
		return radian * (180.0f / (float)Math.PI);
	}
	
	public float dot_theta_degree(Vector2 vec) {
		
		float radian = dot_theta_radian(vec);
		
		return radian * (180.0f / (float)Math.PI);
	}
	
	public float cross(float x, float y) {
		return this.x * y - this.y * x;
	}
	
	public float cross(Vector2 vec) {
		return cross(vec.x, vec.y);
	}
	
	public float angle() {
		float angle = (float)Math.atan2(y,x) * radiansToDegrees;
		if (angle < 0) angle += 360;
		return angle;
	}
	
	public float angle(Vector2 ref) {
		return (float)Math.atan2(cross(ref), dot(ref)) * radiansToDegrees;
	}
	
	public float angleRad() {
		return (float)Math.atan2(y, x);
	}
	
	public float angleRad(Vector2 ref) {
		return (float)Math.atan2(cross(ref), dot(ref));
	}
	
	public void setAngle(float degrees) {
		setAngleRad(degrees * radiansToDegrees);
	}
	
	public void setAngleRad(float radians) {
		this.set(len(), 0f);
		this.rotateRad(radians);
	}
	
	public void rotate(float degrees) {
		rotateRad(degrees * radiansToDegrees);
	}
	
	public void rotateRad(float radians) {
		
		float cos = (float)Math.cos(radians);
		float sin = (float)Math.sin(radians);
		
		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin - this.y * cos;
		
		this.set(newX, newY);
	}
	
	public void rotate90(int dir) {
		float x = this.x;
		if(dir >= 0) {
			this.x = -y;
			this.y = x;
		}else {
			this.x = y;
			this.y = -x;
		}
	}
	
	public Vector2 lerp(float targetX, float targetY, float alpha) {
		float invAlpha = 1.0f - alpha;
		
		this.x = (x * invAlpha) + (targetX * alpha);
		this.y = (y * invAlpha) + (targetY * alpha);
		
		return this;
	}
	
	public Vector2 lerp(Vector2 target, float alpha) {
		
		float invAlpha = 1.0f - alpha;
		
		this.x = (x * invAlpha) + (target.x * alpha);
		this.y = (y * invAlpha) + (target.y * alpha);
		
		return this;
		
	}
	
	public Vector2 lerpX(float targetX, float alpha) {
		float invAlpha = 1.0f - alpha;
		
		this.x = (x * invAlpha) + (targetX * alpha);
		
		return this;
	}
	
	public Vector2 lerpY(float targetY, float alpha) {
		float invAlpha = 1.0f - alpha;
		
		this.y = (y * invAlpha) + (targetY * alpha);
		
		return this;
	}
	
	@Override
	public String toString() {
		return "("+ x + "," + y + ")";
	}
	
	
	
}
