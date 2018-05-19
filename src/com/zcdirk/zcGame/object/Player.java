package com.zcdirk.zcGame.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.Texture;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.window.Animation;
import com.zcdirk.zcGame.window.Camera;
import com.zcdirk.zcGame.window.Game;
import com.zcdirk.zcGame.window.Handler;

public class Player extends GameObject {
	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;

	private Handler handler;
	private Camera cam;

	Texture tex = Game.getInstance();

	private Animation playerWalkRight, playerWalkLeft;

	public Player(float x, float y, Handler handler, Camera cam, objectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;

		playerWalkRight = new Animation(10, tex.player[1], tex.player[2], tex.player[3], tex.player[4]);
		playerWalkLeft = new Animation(10, tex.player[5], tex.player[6], tex.player[7], tex.player[8]);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0)
			facing = -1;
		else if (velX > 0)
			facing = 1;

		if (falling || jumping) {
			velY += gravity;
			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}

		Collision(object);
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();

		if (y > 650) {
			handler.playerDead();
		}

	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else
					falling = true;

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
				}

				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
			} else if (tempObject.getId() == objectId.Flag) {
				// Switch level
				if (getBoundsRight().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())) {
					Game.Level++;
					handler.clearLevel();
					cam.setX(0);;
					Game.state = 3;
				}

			} else if (tempObject.getId() == objectId.Monster) {
				Monster m = (Monster) tempObject;
				if (getBoundsRight().intersects(m.getBoundsLeft())
						|| getBoundsLeft().intersects(m.getBoundsRight())
						|| getBounds().intersects(m.getBoundsTop())) {
					//handler.object.remove(tempObject);
					Game.state = 2;
					handler.clearLevel();
					cam.setX(0);
				}
				
			}
		}
	}

	public void render(Graphics g) {

		if (facing == 1) {
			if (jumping == true)
				g.drawImage(tex.player[10], (int) x, (int) y, null);
			else if (velX != 0)
				playerWalkRight.drawAnimation(g, (int) x, (int) y, 32, 64);
			else
				g.drawImage(tex.player[0], (int) x, (int) y, null);
		} else if (facing == -1) {
			if (jumping == true)
				g.drawImage(tex.player[9], (int) x, (int) y, null);
			else if (velX != 0)
				playerWalkLeft.drawAnimation(g, (int) x, (int) y, 32, 64);
			else
				g.drawImage(tex.player[5], (int) x, (int) y, null);
		}

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

}
