package commandline;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuController 
{
	private String choice;
	private MenuView menuV;
	
	public MenuController() 
	{
		menuV = new MenuView(this);
	}
	
	public void displayMenuV() 
	{
		menuV.displayMenu();
	}
	
	public String getChoice() 
	{
		return choice;
	}
	
	public void setChoiceFromUserInput() 
	{
		choice = menuV.getInput();
	}	
	
	public void readFile() 
	{
		
//		Card[] carddeck = new Card[41];  
//		int i = 0; 
//		try 
//		{
//			scanner = new Scanner("StarCitizenDeck.txt"); 
//			// flag indicating whether finished reading
//			while (scanner.hasNextLine()) 
//			{
//				//For each line an array instance is made, stops at 7 
////				while (i <= 41) 
////				{ 
////					//Reads the file into string array 
////					String line = scanner.nextLine();
////					String array[] = line.split(" "); 
////					String name = array[0];
////					String size = array[1];
////
////					String speed = array[2];
////					String range = array[3];
////					String firepower = array[4];
////					String cargo = array[5];
////					int SIZE = Integer.parseInt(size);
////					int SPEED = Integer.parseInt(speed); 
////					int RANGE =  Integer.parseInt(range);
////					int FIREPOWER = Integer.parseInt(firepower);
////					int CARGO = Integer.parseInt(cargo);
////					//Card array
////					carddeck[i] = new Card("Size", "Speed", "Range", "Firepower", "Cargo", name, SIZE, SPEED, RANGE , FIREPOWER, CARGO); //Causing array out of bounds exception  of 1, not sure how to fix it. 
////					i++;
////					String str="";
////					int j=0;
////					
////					while (carddeck[j] != null)
////					{
////						str += carddeck[j]+"\n";
////						j++;
////						System.out.println(carddeck);
//
////					}
////
////				}	
//
//			}	
//		}
//
//		catch (NoSuchElementException a) 
//		{
//			// executed whether or not an exception is raised above
//			System.out.println("The end");	
//			scanner.close(); 
//			// close the input file assuming it was opened successfully
//		}
	}

	public void userChoice() 
	{
		if(choice.equalsIgnoreCase("Q")) 
		{
			System.out.println("play game");
		}
		else if(choice.equalsIgnoreCase("S")) 
		{
			StatsView statsV = new StatsView();
			statsV.showStats();
		}
	}
}