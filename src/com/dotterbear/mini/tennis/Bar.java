package com.dotterbear.mini.tennis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class Bar {

	private int x;

	private int rightX;

	private int y;

	private int topY;

	private int width;

	private int height;

	private boolean isBroken;

	private Color color;

	public Bar(int x, int y, int width, int height, boolean isBroken, Color color) {
		super();
		this.x = x;
		rightX = x + width;
		this.y = y;
		topY = y + height;
		this.width = width;
		this.height = height;
		this.isBroken = isBroken;
		this.color = color;
	}

	public void paint(Graphics2D g) {
		if (isBroken)
			return;
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	public Shape getShape() {
		return new Rectangle(x, y, width, height);
	}

	public int getY() {
		return y;
	}

	public int getTopY() {
		return topY;
	}

	public int getX() {
		return x;
	}

	public int getRightX() {
		return rightX;
	}

	public boolean isBroken() {
		return isBroken;
	}

	public void setBroken(boolean isBroken) {
		this.isBroken = isBroken;
	}
}
