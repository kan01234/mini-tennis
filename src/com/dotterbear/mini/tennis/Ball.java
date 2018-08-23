package com.dotterbear.mini.tennis;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;
import java.util.Random;

public class Ball {

	private int x;

	private int y;

	private int mx = 1;

	private int my = 1;

	private int height;

	private int width;

	private Game game;

	private Stick stick;

	private Score score;

	private List<Bar> bars;

	private int stickTopY;

	private Random random;

	private final int BALL_RADIUS = 30;

	public Ball(Game game) {
		this.game = game;
		stick = game.getStick();
		score = game.getScore();
		random = new Random();
	}

	public void start(int width, int height) {
		this.height = height - BALL_RADIUS;
		this.width = width - BALL_RADIUS;
		x = this.width / 2;
		y = stick.getY() - BALL_RADIUS;
		stickTopY = stick.getY();
		mx = randomInRange(-1, 1);
	}

	public void move(int speed) {
		if (x + mx < 0)
			mx = 1;
		else if (x + mx > width)
			mx = -1;
		if (y + my < 0)
			my = 1;
		else if (y + my > height)
			game.gameEnd(false);
		else if (isCollision(stick.getShape())) {
			y = stickTopY - BALL_RADIUS;
			my = -1;
		} else {
			int index = -1;
			for (int i = 0; i < bars.size(); i++) {
				Bar bar = bars.get(i);
				if (isCollision(bar.getShape())) {
					score.add();
					mx = mx < 0 ? 1 : -1;
					my = my < 0 ? 1 : -1;
					bar.setBroken(true);
					index = i;
					break;
				}
			}
			if (index > 0) {
				bars.remove(index);
				if (bars.isEmpty())
					game.gameEnd(true);
			}
		}
		x = x + mx * speed;
		y = y + my * speed;
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, BALL_RADIUS, BALL_RADIUS);
	}

	public void setBars(List<Bar> bars) {
		this.bars = bars;
	}

	private boolean isCollision(Shape shape) {
		return shape.intersects(x, y, BALL_RADIUS, BALL_RADIUS);
	}

	private int randomInRange(int min, int max) {
		return random.nextInt((max - min) + 1)  + min;
	}
}
