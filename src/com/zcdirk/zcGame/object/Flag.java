package com.zcdirk.zcGame.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;

public class Flag extends GameObject{

	public Flag(float x, float y, objectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 5, 64);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 5, 64);
	}

}
