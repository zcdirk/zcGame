package com.zcdirk.zcGame.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.zcdirk.zcGame.object.Bullet;
import com.zcdirk.zcGame.window.Game;
import com.zcdirk.zcGame.window.Handler;

public class Keyinput extends KeyAdapter {
	Handler handler;

	public Keyinput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER){
			switch(Game.state){
			case 0:
				Game.state = 1;
				break;
			case 2:
				Game.state = 1;
				handler.switchLevel();
				break;
			case 3:
				Game.state = 1;
				handler.switchLevel();
				break;
			case 4:
				System.exit(0);
				break;
				
			}
		}

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectId.Player) {
				if (key == KeyEvent.VK_D)
					tempObject.setVelX(5);
				if (key == KeyEvent.VK_A)
					tempObject.setVelX(-5);
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setVelY(-10);
					tempObject.setJumping(true);
				}
				if (key == KeyEvent.VK_SPACE) {
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), handler, objectId.Bullet, tempObject.facing * 10));
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectId.Player) {
				if (key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_LEFT)
					tempObject.setVelX(0);
			}
		}
	}

}
