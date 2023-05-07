
package sectionB;

import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done; // REMOVE
	static AtomicBoolean pause; // REMOVE

	private static FallingWord[] words; // list of words
	private static int noWords; // how many
	private static Score score; // user score
	private static int l_word;
	private static HungryWord h_word;

	CatchWord(String typedWord) {
		target = typedWord;
		l_word = -1;
	}

	// New constructor
	CatchWord(String input, int l_word) {
		this(input);
		CatchWord.l_word = l_word;

	}

	public static void setWords(FallingWord[] wordList) {
		words = wordList;
		noWords = words.length;
	}

	public static void setScore(Score sharedScore) {
		score = sharedScore;
	}

	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done = d;
		pause = p;
	}

	public static void setHungryWord(HungryWord hungry) {
		h_word = hungry;
	}

	public void run() {
		int i = 0;
		while (i < noWords) {
			while (pause.get()) {
			}
			;
			i = (l_word == -1) ? i : l_word;

			if (h_word.matchWord(target)) {
				System.out.println(" hungry word'" + target); // for checking
				score.caughtWord(target.length());
				break;
			}
			if (words[i].matchWord(target)) {
				System.out.println(" score! '" + target); // for checking
				score.caughtWord(target.length());
				// FallingWord.increaseSpeed();
				break;

			}
			i++;
		}

	}
}
