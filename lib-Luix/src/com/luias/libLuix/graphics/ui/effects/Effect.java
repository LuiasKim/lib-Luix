package com.luias.libLuix.graphics.ui.effects;

import java.awt.Graphics2D;

public abstract class Effect {

	public enum EFFECTTYPE{
		CAMERA_WAVE,
		CAMERA_QUAKE,
		TYPOEFFECT,
	}
	
	protected EFFECTTYPE type;
	protected boolean isDelete = false;
	protected boolean autoDelete = false;
	
	public Effect() {
		
	}
	
	public Effect(EFFECTTYPE effect) {
		this.type = effect;
	}
	
	public EFFECTTYPE getType() {
		return type;
	}
	
	public abstract boolean validateCheck();
	public abstract void doEffect();
	public boolean autoDelete() {
		return autoDelete;
	}
	
	public boolean isDelete() {
		return this.isDelete;
	}
	
	public void Delete() {
		this.isDelete = true;
	}
	
	protected static abstract class Camera_Effect extends Effect{
		protected Graphics2D batch;
		protected float duration = 0.0f;
		protected boolean isHorizontal = false;
		protected boolean isEnd = false;
		
		protected long startTime = 0;
		protected float angle = 0.0f;
		
		protected double cam_x = 0.0f, cam_y = 0.0f;
		
		public Camera_Effect(EFFECTTYPE type) {
			super(type);
		}
		
		public boolean isHorizontal() {
			return isHorizontal;
		}
		
		public double getCam_X() {
			return cam_x;
		}
		
		public double getCam_Y() {
			return cam_y;
		}
		
		public void setCam_X(double cam_x) {
			this.cam_x = cam_x;
		}
		
		public void setCam_Y(double cam_y) {
			this.cam_y = cam_y;
		}
		
		public float getDuration() {
			return duration;
		}
		
		public long getStartTime() {
			return startTime;
		}
		
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}
		
		public float getAngle() {
			return angle;
		}
		
		public void setAngle(float angle) {
			this.angle = angle;
		}
		
		public Graphics2D getBatch() {
			return batch;
		}
		
	}
	
	public static class Camera_QuakeEffect extends Camera_Effect{
		
		public Camera_QuakeEffect(Graphics2D batch, long duration, boolean isHorizontal, boolean autoDelete) {
			super(EFFECTTYPE.CAMERA_QUAKE);
			
			this.batch = batch;
			this.duration = duration;
			this.autoDelete = autoDelete;
			this.isHorizontal = isHorizontal;
		}

		@Override
		public boolean validateCheck() {
			// TODO Auto-generated method stub
			return !isEnd;
		}

		@Override
		public void doEffect() {
			// TODO Auto-generated method stub
			isEnd = CameraEffect.QuakeEffect(this);
		}
		
	}
	
	public static class Camera_WaveEffect extends Camera_Effect{
		
		public Camera_WaveEffect(Graphics2D batch, long duration, boolean isHorizontal, boolean autoDelete) {
			super(EFFECTTYPE.CAMERA_WAVE);
			
			this.batch = batch;
			this.duration = duration;
			this.isHorizontal = isHorizontal;
			this.autoDelete = autoDelete;
		}

		@Override
		public boolean validateCheck() {
			// TODO Auto-generated method stub
			return !isEnd;
		}

		@Override
		public void doEffect() {
			// TODO Auto-generated method stub
			isEnd = CameraEffect.WaveEffect(this);
		}
		
	}
	
	public static class TypoEffect extends Effect{
		
		String context = "";
		String message = "";
		float complete_char_count = 0.0f;
		float loc_x, loc_y;
		Graphics2D batch;
		
		public TypoEffect(Graphics2D batch, String message, float loc_x, float loc_y, boolean autoDelete) {
			super(EFFECTTYPE.TYPOEFFECT);
			this.message = message;
			this.loc_x = loc_x;
			this.loc_y = loc_y;
			this.batch = batch;
			this.autoDelete = autoDelete;
		}
		
		@Override
		public boolean validateCheck() { 
			// true 일때는 아직 이펙트가 끝난게 아닌거,
			// false 일때는 이펙트가 끝난거.
			if((int)complete_char_count >= message.length())
				return false;
			
			return true;
		}

		@Override
		public void doEffect() {
			complete_char_count = TypeWriterEffect.typoEffect(batch, loc_x, loc_y, message, complete_char_count);
		}
		
	}
	
	
	
}
