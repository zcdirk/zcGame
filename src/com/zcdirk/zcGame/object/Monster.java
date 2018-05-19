package com.zcdirk.zcGame.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.window.Handler;
import com.zcdirk.zcGame.window.ImageLoader;

public class Monster extends GameObject {
	private float width = 50, height = 50;
	private BufferedImage monster = null;
	private float gravity = 0.5f;

	private Handler handler;
	private boolean monsterFalling = true;

	public Monster(float x, float y, Handler handler, objectId id) {
		super(x, y, id);
		this.handler = handler;
		velX = 3;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (monsterFalling)
			velY += gravity;

		Collision(object);
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					monsterFalling = false;
				} else
					monsterFalling = true;

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					velX = -velX;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
					velX = -velX;
				}

				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
			} else if(tempObject.getId() == objectId.Bullet) {
				if (getBoundsAll().intersects(tempObject.getBounds())) {
					handler.object.remove(this);
					handler.object.remove(tempObject);
				}
			} else if(tempObject.getId() == objectId.MonsterBlock) {
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					velX = -velX;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
					velX = -velX;
				}
			}

		}
	}

	public void render(Graphics g) {
		ImageLoader loader = new ImageLoader();
		monster = loader.imageLoader("/res/monster.png");
		g.drawImage(monster, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (x + width / 4), (int) (y + height / 2), (int) width / 2, (int) (height / 2));
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 5), (int) (y + 10), (int) 5, (int) (height - 20));
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) (x), (int) (y + 10), (int) 5, (int) (height - 20));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + width / 4), (int) y, (int) width / 2, (int) (height / 2));
	}
	
	public Rectangle getBoundsAll() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}
