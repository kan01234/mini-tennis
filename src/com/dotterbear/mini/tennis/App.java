package com.dotterbear.mini.tennis;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		final int WIDTH = 300;
		final int HEIGHT = 300;
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
