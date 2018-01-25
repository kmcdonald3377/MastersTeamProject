//Nadya
package commandline;

public class CommunalPile {

	private Card[] card;

	// constructor
	public CommunalPile() {

		this.card = new Card[0];
	}

	// get the card at the given index
	public Card getCardAtIndex(int index) {

		return this.card[index];
	}

	// take the drawing card from the player
	public Card takeCard() {

		Card drawingCard = this.card[0];
		Card[] ph = new Card[this.card.length - 1];

		for (int i = 0; i < ph.length; i++) {

			ph[i] = this.card[i + 1];
		}
		this.card = ph;

		return drawingCard;
	}

	// get the number of cards in the communal pile
	public int getPileLength() {

		return this.card.length;
	}

	// add card to winning players hand
	public void addCard(Card n) {

		Card[] c = new Card[this.card.length + 1];

		for (int i = 0; i < card.length; i++) {
			c[i] = this.card[i];
		}

		c[card.length] = n;

		this.card = c;
	}
}