// this class represents cards to get the five attribute names and values 
package commandline;

import java.util.ArrayList;
import java.util.HashMap;

public class Card 
{

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
	private ArrayList<Integer> cardValues; //added these two in so that can find correct index for the value to compare against
	private ArrayList<String> cardAttributes; //other cards to decide winner/if draw
	
	//second option
	private HashMap<String, Integer> attributes;

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
		cardAttributes = new ArrayList<String>();
		cardAttributes.add(attribute1);
		cardAttributes.add(attribute2);
		cardAttributes.add(attribute3);
		cardAttributes.add(attribute4);
		cardAttributes.add(attribute5);
		
		cardValues = new ArrayList<Integer>();
		cardValues.add(value1);
		cardValues.add(value2);
		cardValues.add(value3);
		cardValues.add(value4);
		cardValues.add(value5);
		
		attributes = new HashMap<String, Integer>();
		attributes.put(att1, val1);
		attributes.put(att2, val2);
		attributes.put(att3, val3);
		attributes.put(att4, val4);
		attributes.put(att5, val5);
	}
	
	public HashMap<String, Integer> getAttributes()
	{
		return attributes;
	}

	// get the name of the card
	public String getName() {

		return name;
	}

	// get attribute name 1
	public String getAttribute1() {

		return attribute1;
	}

	// get attribute name 2
	public String getAttribute2() {

		return attribute2;
	}

	// get attribute name 3
	public String getAttribute3() {

		return attribute3;
	}

	// get attribute name 4
	public String getAttribute4() {

		return attribute4;
	}

	// get attribute name 5
	public String getAttribute5() {

		return attribute5;
	}

	// get the card at the given index
//	public Card getCardAtIndex(int index) {
//
//		return this.card[index];
//	}

	public String getAttributeAtIndex(int index) {

		// index at 0 is description/name - i don't think this is required, as it will
		// never be compared against any other card

		return cardAttributes.get(index);
	}

	// get the int values of each attribute
	public int getValue1() {

		return value1;
	}

	public int getValue2() {

		return value2;
	}

	public int getValue3() {

		return value3;
	}

	public int getValue4() {

		return value4;
	}

	public int getValue5() {

		return value5;
	}

	// a method to return the values of each attribute at a given index
	public int getValueAtIndex(int index) {

		return cardValues.get(index);
	}
	
	public String toString() {
		return  String.format(name + value1 + value2 + value3 + value4 + value5);
	}
}
