package com.dotterbear.mini.tennis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {

	private Ball ball;

	private Stick stick;

	private Score score;

	private List<Bar> bars;

	private JButton startBtn;

	boolean isPlaying = false;

	private List<Color> colors;

	public Game() {
		setLayout(new BorderLayout());
		stick = new Stick();
		score = new Score();
		ball = new Ball(this);
		startBtn = new JButton("start");
		startBtn.setFocusable(false);
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startBtn.setVisible(false);
				start();
			}
		});
		add(startBtn);
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				stick.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				stick.keyReleased(e);
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}

	public void start() {
		init();
		isPlaying = true;
		CompletableFuture.runAsync(() -> {
			while (isPlaying) {
				move();
				repaint();
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void init() {
		int width = getWidth();
		int height = getHeight();
		initColors();
		initBars(30, 3, 4);
		score.start();
		stick.start(width, height);
		ball.setBars(bars);
		ball.start(width, height);
	}

	public void move() {
		int speed = score.getScore() / 3 + 1;
		ball.move(speed);
		stick.move(speed);
	}

	@Override
	public void paint(Graphics g) {
		if (!isPlaying)
			return;
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		stick.paint(g2d);
		bars.forEach(bar -> bar.paint(g2d));
		score.paint(g2d);
	}

	public Stick getStick() {
		return stick;
	}

	public Score getScore() {
		return score;
	}

	public List<Bar> getBars() {
		return bars;
	}

	public void gameEnd(boolean isWon) {
		isPlaying = false;
		String message = String.format(isWon ? "you win" : "game over" + ", your score is: %d\n continue?",
				score.getScore());
		int input = JOptionPane.showOptionDialog(this, message, isWon ? "You win" : "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (input == 0) {
			startBtn.setVisible(true);
			init();
		} else
			System.exit(ABORT);
	}

	private void initBars(int height, int row, int col) {
		int width = (getWidth()) / col;
		bars = new ArrayList<Bar>();
		IntStream.range(0, row).forEach(i -> {
			IntStream.range(0, col).forEach(j -> {
				bars.add(new Bar(width * j, height * i, width, height, false, colors.get((i + j) % 4)));
			});
		});
	}

	private void initColors() {
		colors = new ArrayList<Color>();
		IntStream.range(0, 4).forEach(i -> colors.add(new Color((int) (Math.random() * 0x1000000))));
	}
}
