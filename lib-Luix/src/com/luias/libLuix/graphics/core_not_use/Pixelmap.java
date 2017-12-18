package com.luias.libLuix.graphics.core_not_use;

import java.util.Arrays;

public class Pixelmap {

	public int w, h;
	public int[] pixels;
	
	public Pixelmap(int w, int h) {
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
	}
	
	public void clear(int color) {
		Arrays.fill(pixels, color);
	}
	
	public void render(Pixelmap pixelmap, int x, int y) {
		int x0 = x, y0 = y;
		int x1= x + pixelmap.w, y1 = y + pixelmap.h;
		
		if(x0 < 0) x0 = 0;
		if(x1 > w) x1 = w;
		if(y0 < 0) y0 = 0;
		if(y1 > y) y1 = y;
		
		int ww = x1 - x0;
		for(int yy = y0; yy < y1; yy++) {
			int tp = yy * w + x0;
			int sp = (yy - y) * pixelmap.w + (x0 - x);
			tp -= sp;
			for(int xx = sp; xx < sp + ww; xx++) {
				int col = pixelmap.pixels[xx];
				if(col < 0)pixels[tp + xx] = col;
			}
		}
	}
	
}
