package com.zcdirk.zcGame.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.Texture;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.window.Game;

public class Block extends GameObject {

	Texture tex = Game.getInstance();

	private int type;

	public Block(float x, float y, int type, objectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		if (type == 0)
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		if (type == 1)
			g.drawImage(tex.block[1], (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
