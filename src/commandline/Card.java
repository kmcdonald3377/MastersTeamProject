// Nadya
// this class represents cards to get the five attribute names and values 
package commandline;

public class Card {

	// instance variables for the five card attribute names
	private final String attribute1;
	private final String attribute2;
	private final String attribute3;
	private final String attribute4;
	private final String attribute5;

	// instance variables for each card value
	private final String name;
	private final int value1;
	private final int value2;
	private final int value3;
	private final int value4;
	private final int value5;

	// card values array
	private Card[] card;

	// constructor
	public Card(String att1, String att2, String att3, String att4, String att5, String desc, int val1, int val2,
			int val3, int val4, int val5) {

		this.name = desc;
		this.attribute1 = att1;
		this.attribute2 = att2;
		this.attribute3 = att3;
		this.attribute4 = att4;
		this.attribute5 = att5;
		this.value1 = val1;
		this.value2 = val2;
		this.value3 = val3;
		this.value4 = val4;
		this.value5 = val5;
	}

	// get attribute names of each card

	// get the name of the card
	public String getName() {

		return name;
	}

	// get attribute name 1
	private String getAttribute1() {

		return attribute1;
	}

	// get attribute name 2
	private String getAttribute2() {

		return attribute2;
	}

	// get attribute name 3
	private String getAttribute3() {

		return attribute3;
	}

	// get attribute name 4
	private String getAttribute4() {

		return attribute4;
	}

	// get attribute name 5
	private String getAttribute5() {

		return attribute5;
	}

	// get the card at the given index
	public Card getCardAtIndex(int index) {

		return this.card[index];
	}

	public String getAttributeAtIndex(int index) {

		// index at 0 is description/name - i don't think this is required, as it will
		// never be compared against any other card

		if (index == 1) {

			return getAttribute1();

		} else if (index == 2) {

			return getAttribute2();

		} else if (index == 3) {

			return getAttribute3();

		} else if (index == 4) {

			return getAttribute4();

		} else if (index == 5) {

			return getAttribute5();

		} else

			return null;
	}

	// get the int values of each attribute
	private int getValue1() {

		return value1;
	}

	private int getValue2() {

		return value2;
	}

	private int getValue3() {

		return value3;
	}

	private int getValue4() {

		return value4;
	}

	private int getValue5() {

		return value5;
	}

	// a method to return the values of each attribute at a given index
	public int getValueAtIndex(int index) {

		if (index == 1) {

			return getValue1();

		} else if (index == 2) {

			return getValue2();

		} else if (index == 3) {

			return getValue3();

		} else if (index == 4) {

			return getValue4();

		} else if (index == 5) {

			return getValue5();

		} else

			return -1;
	}
}
