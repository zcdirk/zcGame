package com.zcdirk.zcGame.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.zcdirk.zcGame.framework.GameObject;
import com.zcdirk.zcGame.framework.objectId;
import com.zcdirk.zcGame.object.Begin;
import com.zcdirk.zcGame.object.Block;
import com.zcdirk.zcGame.object.Dead;
import com.zcdirk.zcGame.object.Flag;
import com.zcdirk.zcGame.object.LevelClear;
import com.zcdirk.zcGame.object.Monster;
import com.zcdirk.zcGame.object.MonsterBlock;
import com.zcdirk.zcGame.object.Player;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	private Camera cam;
	private BufferedImage level, level2, level3, end;
	private boolean hadSwitchLevel = false;

	public Handler(Camera cam) {
		this.cam = cam;
		ImageLoader loader = new ImageLoader();
		level = loader.imageLoader("/res/level.png");
		level2 = loader.imageLoader("/res/level2.png");
		level3 = loader.imageLoader("/res/level3.png");
		end = loader.imageLoader("/res/end.png");
	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}	
	}

	public void render(Graphics g) {

		switch (Game.state) {
		case 0:
			addObject(new Begin(0, 0, objectId.Begin));
			break;
		case 1:
			if(hadSwitchLevel == false){
				switchLevel();
				hadSwitchLevel = true;
			}
			break;
		case 2:
			addObject(new Dead(0, 0, objectId.Dead));
			hadSwitchLevel = false;
			break;
		case 3:
			addObject(new LevelClear(0, 0, objectId.LevelClear));
			hadSwitchLevel = false;
			break;
		case 4:
			g.drawImage(end, 30, 50, null);
			break;
		}

		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}

	}

	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255)
					addObject(new Block(32 * i, 32 * j, 0, objectId.Block));

				if (red == 0 && green == 0 && blue == 255)
					addObject(new Player(32 * i, 32 * j, this, cam, objectId.Player));

				if (red == 255 && green == 0 && blue == 0)
					addObject(new Flag(32 * i, 32 * j, objectId.Flag));

				if (red == 0 && green == 255 && blue == 0)
					addObject(new Monster(32 * i, 32 * j, this, objectId.Monster));
				
				if (red == 255 && green == 255 && blue == 0)
					addObject(new MonsterBlock(32 * i, 32 * j, objectId.MonsterBlock));

			}

		}
	}

	public void switchLevel() {
		clearLevel();
		cam.setX(0);

		switch (Game.Level) {
		case 0:
			loadImageLevel(level);
			break;
		case 1:
			loadImageLevel(level2);
			break;
		case 2:
			loadImageLevel(level3);
			break;
		case 3:
			clearLevel();
			cam.setX(0);
			Game.state = 4;
			break;
		}
	}

	public void clearLevel() {
		object.clear();
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void playerDead() {
		Game.state = 2;
		clearLevel();
		cam.setX(0);
	}

}
