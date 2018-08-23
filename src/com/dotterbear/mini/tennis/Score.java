package com.dotterbear.mini.tennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score {

	private int score;

	public void start() {
		score = 0;
	}

	public void add() {
		++score;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 30));
		g.drawString(String.valueOf(score), 10, 30);
	}

	public int getScore() {
		return score;
	}
}
