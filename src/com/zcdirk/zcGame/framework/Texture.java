package com.zcdirk.zcGame.framework;

import java.awt.image.BufferedImage;

import com.zcdirk.zcGame.window.ImageLoader;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	

	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[11];

	public Texture() {

		ImageLoader loader = new ImageLoader();
		try {
			blockSheet = loader.imageLoader("/res/blockSheet.png");
			playerSheet = loader.imageLoader("/res/playerSheet.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);

		getTextures();
	}

	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(2, 1, 32, 32);

		player[0] = ps.grabImage(1, 1, 32, 64);
		
		//right
		player[1] = ps.grabImage(2, 1, 32, 64);
		player[2] = ps.grabImage(3, 1, 32, 64);
		player[3] = ps.grabImage(4, 1, 32, 64);
		player[4] = ps.grabImage(5, 1, 32, 64);
		
		//left
		player[5] = ps.grabImage(6, 1, 32, 64);
		player[6] = ps.grabImage(7, 1, 32, 64);
		player[7] = ps.grabImage(8, 1, 32, 64);
		player[8] = ps.grabImage(9, 1, 32, 64);

		//jump
		player[9] = ps.grabImage(10, 1, 32, 64);
		player[10] = ps.grabImage(11, 1, 32, 64);



	}
}
