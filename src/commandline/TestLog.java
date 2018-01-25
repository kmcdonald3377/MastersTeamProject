// Robbie
import java.io.*;
/*
 * Class creates a test log object. 
 * Test log will create/overwrite file 'toptrumps.log' and print details 
 * of the game's operation to the file.
 */

public class TestLog {
	// instance variable
	private final String fin = ("toptrumps.log");
	private final File log = new File(fin);
	private PrintWriter writer;
	private String lineBreak;
	private Card [] deck;
	private Card [] shuffledDeck;
	private Card [] humanDeck;
	private Card [] AIDeck;
	private Card [] pile;
	private String cardsIP;
	private int hCount;
	private int AICount;
	
	public TestLog ()
	{
		try {
			writer = new PrintWriter(log);
			// make this just - * arr.len
			lineBreak = "--------------------------------------------------------";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
