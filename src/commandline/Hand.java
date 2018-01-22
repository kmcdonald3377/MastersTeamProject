package commandline;

import java.util.Arrays;

public class Hand {
	private Card[] card;
	private int players;

public void splitcards() {
	Arrays.copyOfRange(card, 0, card.length/players);
}
}
