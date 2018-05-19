package com.zcdirk.zcGame.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.window.Handler;

public class Bullet extends GameObject{
	
	private Handler handler;
	public float startx;

	public Bullet(float x, float y, Handler handler, objectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		startx = x;
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;	
		if(x - startx > 800)
			handler.object.remove(this);
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.object.remove(this);

				}
			}

		}
	}
	

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)(x + 16), (int)(y + 16), 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)(x + 16), (int)(y + 16), 8, 8);
	}

}
