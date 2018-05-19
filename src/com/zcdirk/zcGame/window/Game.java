package com.zcdirk.zcGame.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import com.zcdirk.zcGame.framework.Keyinput;
import com.zcdirk.zcGame.framework.Texture;
import com.zcdirk.zcGame.framework.objectId;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -1392037006272339761L;

	private boolean running = false;
	private Thread thread;

	public static int WIDTH, HEIGHT;
	public BufferedImage clouds = null, start = null, level = null;
	public static int Level = 0;

	public static int state = 0; // 0 means not begin, 1 means begin and player
									// alive
									// 2 means dead, 3 means level clear, 4
									// means end

	// Object
	Handler handler;
	Camera cam;
	static Texture text;

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		text = new Texture();

		ImageLoader loader = new ImageLoader();
		level = loader.imageLoader("/res/level.png");
		clouds = loader.imageLoader("/res/cloud.png");
		start = loader.imageLoader("/res/start.png");

		cam = new Camera(0, 0);
		handler = new Handler(cam);
		

		this.addKeyListener(new Keyinput(handler));
	}

	public synchronized void start() {
		if (running == true)
			return;

		running = true;
		thread = new Thread();
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + "   TICKS: " + updates);
				frames = 0;
				updates = 0;
			}

		}

	}

	private void tick() {
		handler.tick();

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == objectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		////////////////////////////
		// Draw everything here

		// Begin Scene
		g.setColor(new Color(53, 179, 251));
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < clouds.getWidth() * 5; i += clouds.getWidth()) {
			g.drawImage(clouds, i * 2, 20, this);
		}

		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY());
		///////////////////////////

		g.dispose();
		bs.show();

	}

	public static Texture getInstance() {
		return text;
	}

	public static void main(String args[]) {
		
		new Window(800, 600, "zcGame v1.0 ", new Game());
	}
}
