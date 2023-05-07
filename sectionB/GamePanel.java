package sectionB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	private AtomicBoolean done; // REMOVE
	private AtomicBoolean started; // REMOVE
	private AtomicBoolean won; // REMOVE

	private FallingWord[] words;
	private HungryWord hungryWord;
	private int noWords;
	private final static int borderWidth = 25; // appearance - border

	GamePanel(FallingWord[] words, HungryWord hWord, int maxY, AtomicBoolean d, AtomicBoolean s, AtomicBoolean w) {
		this.words = words; // shared word list
		this.hungryWord = hWord;
		noWords = words.length; // only need to do this once
		done = d; // REMOVE
		started = s; // REMOVE
		won = w; // REMOVE
	}

	public void paintComponent(Graphics g) {
		int width = getWidth() - borderWidth * 2;
		int height = getHeight() - borderWidth * 2;
		g.clearRect(borderWidth, borderWidth, width, height);// the active space
		g.setColor(Color.pink); // change colour of pen
		g.fillRect(borderWidth, height, width, borderWidth); // draw danger zone

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		// draw the words
		if (!started.get()) {
			g.setFont(new Font("Arial", Font.BOLD, 26));
			String text1 = "Type all the words before they hit the red zone,";
			String text2 = "press enter after each one.";
			g.drawString(text1, width / 2 - text1.length() * 7, height / 2 - borderWidth);
			g.drawString(text2, width / 2 - text2.length() * 7, height / 2);
		} else if (!done.get()) {
			for (int i = 0; i < noWords; i++) {
				g.drawString(words[i].getWord(), words[i].getX() + borderWidth, words[i].getY());
				g.setColor(Color.GREEN);
				g.drawString(hungryWord.getWord(), hungryWord.getX() + borderWidth, hungryWord.getY());
				g.setColor(Color.BLACK);
			}
			g.setColor(Color.lightGray); // change colour of pen
			g.fillRect(borderWidth, 0, width, borderWidth);
			g.clearRect(width + borderWidth, 0, borderWidth, height);
			g.clearRect(0, 0, borderWidth, height);
		} else {
			if (won.get()) {
				g.setFont(new Font("Arial", Font.BOLD, 36));
				g.drawString("Well done!", width / 2 - 80, height / 2);
			} else {
				g.setFont(new Font("Arial", Font.BOLD, 36));
				g.drawString("Game over!", width / 2 - 80, height / 2); // subtrating 80 place game over at center
			}
		}
	}

	public int getValidXpos() {
		int width = getWidth() - borderWidth * 4;
		int x = (int) (Math.random() * width);
		return x;
	}

	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
		}
	}

}
