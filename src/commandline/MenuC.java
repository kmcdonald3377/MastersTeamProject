package commandline;

import java.io.*;
import java.util.Scanner;

public class MenuC {
	public static void ReadFile() {
		FileReader reader = null;
		try {
			try {
				reader = new FileReader("StarCitizenDeck.txt"); 
				// flag indicating whether finished reading
				boolean done = false;
				while (!done) {
                         // read a character, represented by an int
				        int next = reader.read();
				        // -1 represents end of file
					if (next == -1)
					        done = true;
					else {
					        // convert to character
					        char c = (char) next;
                        // echo character to user
					        System.out.print(c);
					}
				}
			}
			finally {
				// executed whether or not an exception is raised above
				System.out.println("The end");
				// close the input file assuming it was opened successfully
				//comment
				if (reader != null) reader.close();
			}
		}
		catch (IOException e) {
			System.out.println("Error opening file");
		}
	}
}