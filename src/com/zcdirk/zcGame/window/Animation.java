package com.zcdirk.zcGame.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private int speed;
	private int frames;
	private int index = 0;
	private int count = 0;

	private BufferedImage[] images;
	private BufferedImage currentImage;

	public Animation(int speet, BufferedImage... args) {
		this.speed = speet;
		images = new BufferedImage[args.length];

		for (int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
	}

	public void runAnimation() {
		index++;

		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame() {
		for (int i = 0; i < frames; i++) {
			if (count == i)
				currentImage = images[i];
		}

		count++;
		if (count > frames)
			count = 0;
	}

	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImage, x, y, scaleX, scaleY, null);
	}

}