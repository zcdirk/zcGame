package com.zcdirk.zcGame.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;

public class MonsterBlock extends GameObject{

	public MonsterBlock(float x, float y, objectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
