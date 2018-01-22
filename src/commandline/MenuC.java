package commandline;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuC {
	public static void ReadFile() {
		Scanner scan = null; 
		Card[] carddeck = new Card[42]; 
		int i = 0; 
		try {
			scan = new Scanner("StarCitizenDeck.txt"); 
			// flag indicating whether finished reading
					while (scan.hasNextLine()) { 
						//For each line an array instance is made, stops at 7 
						while(i<= 42) { 
							//Reads the file into string array 
					       String line = scan.nextLine();
					       String array[] = line.split(" "); 
					       String name = array[0];
					       String size = array[1];
					       String speed = array[2];
					       String range = array[3];
					       String firepower = array[4];
					       String cargo = array[5];
					       int SIZE = Integer.parseInt(size);
					       int SPEED = Integer.parseInt(speed); 
					       int RANGE =  Integer.parseInt(range);
					       int FIREPOWER = Integer.parseInt(firepower);
					       int CARGO = Integer.parseInt(cargo);
					       carddeck[i] = new Card("Size", "Speed", "Range", "Firepower", "Cargo", name, SIZE, SPEED, RANGE , FIREPOWER, CARGO);
					       i++;
					       
					       System.out.println(carddeck);
					
}
 
}	

		}		
				 
		catch (NoSuchElementException a) {
			// executed whether or not an exception is raised above
			System.out.println("The end");	
		    scan.close(); 
			// close the input file assuming it was opened successfully
			
		
		}
	}
}