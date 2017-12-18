package com.luias.libLuix.graphics.ui.effects;

import java.awt.Graphics2D;

public class TypeWriterEffect {

	public static boolean fast_dialog = false;
	public static boolean skip_dialog = false;
	
	private static float Normal_speed = 1.8f;
	private static float Fast_speed = 2.7f;
	
	public static float typoEffect(Graphics2D batch, float loc_x, float loc_y, String message, float complete_char_count) {
		
		complete_char_count += (fast_dialog ? Fast_speed : Normal_speed) * 0.1f;
		
		int char_count = (int)complete_char_count;
		if(char_count >= message.length())
			char_count = message.length();
		
		if(skip_dialog)
			char_count = message.length();
		
		batch.drawString(message.substring(0, char_count), loc_x, loc_y);
		 
		return complete_char_count;
		//complete_char_count = char_count;
		
	}
	
}
