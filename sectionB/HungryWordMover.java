package sectionB;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {

	private HungryWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause;
	private Score score;
	private FallingWord[] words;
	CountDownLatch startLatch; // so all can start at once

	HungryWordMover(HungryWord word) {
		myWord = word;
	}

	HungryWordMover(HungryWord word, WordDictionary dict, Score score, CountDownLatch startLatch, AtomicBoolean d,
			AtomicBoolean p) {
		this(word);
		this.startLatch = startLatch;
		this.score = score;
		this.done = d;
		this.pause = p;
	}

	public void setWords(FallingWord[] words) {
		this.words = words;
	}

	public void run() {

		try {
			System.out.println(myWord.getWord() + " waiting to start ");
			startLatch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} // wait for other threads to start
		System.out.println(myWord.getWord() + " started");
		while (!done.get()) {
			// animate the word
			while (!myWord.moved() && !done.get()) {

				// If the hungry word touches others words they should disappear

				for (int i = 0; i < words.length; i++) {
					int X = myWord.getX();
					int X_1 = myWord.getX() + myWord.getWord().length() * 16;
					int Y = myWord.getY();
					int Y_1 = myWord.getY() + 16;
					int x = words[i].getX();
					int x_2 = words[i].getX() + words[i].getWord().length() * 16;
					int y = words[i].getY();
					int y_2 = words[i].getY() + 16;

					if ((X_1 >= x && X <= x_2) && (y_2 >= Y && y <= Y_1)) {
						words[i].resetWord();
						score.missedWord();
					} else if ((X_1 >= x && X <= x_2) && (Y_1 >= y && Y <= y_2)) {
						words[i].resetWord();
						score.missedWord();
					} else if ((x_2 >= X && x <= X_1) && (y_2 >= Y && y <= Y_1)) {
						words[i].resetWord();
						score.missedWord();
					} else if ((x_2 >= X && x <= X_1) && (Y_1 >= y && Y <= y_2)) {
						words[i].resetWord();
						score.missedWord();
					}

				}
				myWord.move(20);
				try {
					sleep(myWord.getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
				while (pause.get() && !done.get()) {
				}
				;
			}
			if (!done.get() && myWord.moved()) {
				score.missedWord();
				myWord.resetWord();
			}
			myWord.resetWord();
		}
	}

}
