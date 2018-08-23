package com.dotterbear.mini.tennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;

public class Stick {

	private int x = 0;

	private int mx = 0;

	private int width;

	private int y;

	private final int STICK_WIDTH = 60;

	private final int STICK_HEIGHT = 10;

	public void start(int width, int height) {
		this.width = width - STICK_WIDTH;
		this.y = height - STICK_HEIGHT;
		x = this.width / 2;
	}

	public void move(int speed) {
		if (x + mx > 0 && x + mx < width)
			x = x + mx * speed;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, STICK_WIDTH, STICK_HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		mx = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			mx = -1;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			mx = 1;
	}

	public Shape getShape() {
		return new Rectangle(x, y, STICK_WIDTH, STICK_HEIGHT);
	}

	public int getY() {
		return y;
	}
}
