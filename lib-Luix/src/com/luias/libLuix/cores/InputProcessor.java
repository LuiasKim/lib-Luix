package com.luias.libLuix.cores;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.luias.libLuix.listener.InputListener;

public class InputProcessor {

	private static InputProcessor INSTANCE = new InputProcessor();
	private static final long INPUT_MASK = 
			AWTEvent.MOUSE_EVENT_MASK+
			AWTEvent.MOUSE_MOTION_EVENT_MASK+
			AWTEvent.MOUSE_WHEEL_EVENT_MASK+
			AWTEvent.KEY_EVENT_MASK;
	
	public static InputProcessor getInstance() {
		return INSTANCE;
	}
	
	private InputListener input_listener;
	
	private boolean key_codes[] = new boolean[65535];
	
	public InputProcessor() {
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			
			@Override
			public void eventDispatched(AWTEvent e) {
				if(input_listener == null)
					return;
				
				if(e.getID() == KeyEvent.KEY_PRESSED) {
					input_listener.keyPressed(key_codes);
					return;
				}
				if(e.getID() == KeyEvent.KEY_RELEASED) {
					input_listener.keyUp(key_codes);
					return;
				}

				if(e.getID() == MouseEvent.MOUSE_CLICKED) {
					MouseEvent me = (MouseEvent)e;
					input_listener.mouseClicked(me.getX(), me.getY(), me.getButton());
					return;
				}
				
				if(e.getID() == MouseEvent.MOUSE_DRAGGED) {
					MouseEvent me = (MouseEvent)e;
					input_listener.mouseDragged(me.getX(), me.getY(), me.getButton());
					return;
				}
				
				if(e.getID() == MouseEvent.MOUSE_MOVED) {
					MouseEvent me = (MouseEvent)e;
					input_listener.mouseMoved(me.getX(), me.getY());
					return;
				}
				
				if(e.getID() == MouseEvent.MOUSE_PRESSED) {
					MouseEvent me = (MouseEvent)e;
					input_listener.mouseDown(me.getX(), me.getY(), me.getButton());
					return;
				}
				
				if(e.getID() == MouseEvent.MOUSE_RELEASED) {
					MouseEvent me = (MouseEvent)e;
					input_listener.mouseUp(me.getX(), me.getY(), me.getButton());
					return;
				}

			}
		}, INPUT_MASK);
		
		
	}
	
	public void addInputListener(InputListener proc) {
		this.input_listener = proc;
	}

	
	public boolean[] getKey_codes() {
		return key_codes;
	}
	
}
