package com.luias.libLuix.objects.attribs;

import com.luias.libLuix.objects.GameObject;

public interface Attackable {

	public boolean nowAttackable = false;
	
	public void attack(GameObject obj);
	
}
