package com.zcdirk.zcGame.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.window.ImageLoader;

public class LevelClear extends GameObject{
	private BufferedImage levelClear = null;

	public LevelClear(float x, float y, objectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		ImageLoader loader = new ImageLoader();
		levelClear = loader.imageLoader("/levelClear.png");
		g.drawImage(levelClear, 0, 0, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}
