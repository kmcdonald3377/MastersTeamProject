package commandline;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * controller class - responsible for control functions of the game
 */
public class MenuController 
{
	// instance variables
	private String choice;
	private boolean log;
	private MenuView menuV;
	private Scanner scanner;
	private String name;
	private PileOfCards starCitizenDeck;

	// constructor
	public MenuController() 
	{
		log = false;
		menuV = new MenuView(this);
	}

	// method to display a menu view object
	public void displayMenuV() 
	{
		menuV.displayMenu();
	}

	/**
	 * method returns choice string from user input
	 * 
	 * @return
	 */
	public String getChoice() 
	{
		return choice;
	}

	/**
	 * menu to return name string from user input
	 * 
	 * @return
	 */
	public String getName()
	{
		return name; 
	}

	// method sets name from user input
	public void setName()
	{
		menuV.setHumanName();
		name = menuV.getInput();
	}

	// method sets choice from user input 
	public void setChoiceFromUserInput() 
	{
		choice = menuV.getInput();
	}	

	// method creates filereader to read in deck file 
	public PileOfCards readFile()
	{
		FileReader reader = null;
		try
		{
			reader = new FileReader("StarCitizenDeck.txt");
		}
		catch(Exception e) 
		{

		}

			scanner = new Scanner(reader);
			
			ArrayList<String> input = new ArrayList();

			while(scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();
				input.add(line);
			}

			String[] description = input.get(0).split(" +");
			
			starCitizenDeck = new PileOfCards(null);


			for(int i = 1; i < input.size(); i++) 
			{
				String[] values = input.get(i).split(" +");

				starCitizenDeck.addCard(new Card(description[1], description[2], description[3], description[4], description[5], 
						values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), 
						Integer.parseInt(values[4]), Integer.parseInt(values[5])));
			}
			return starCitizenDeck;
		
	}
	
	/**
	 * switch method which will decide if a games log is to be written 
	 * 
	 * @param writeGameLogsToFile - boolean switch, true if log to be written
	 */
	public void log(boolean writeGameLogsToFile)	{
		log = writeGameLogsToFile;
	}

	/* 
	 * method gets choice from user indicating if they want to play
	 * a game or view statistics
	 */
	public void choiceSelection() 
	{
		if(choice.equalsIgnoreCase("G")) 
		{
			GameController gameC = new GameController(starCitizenDeck, name, log);
			gameC.startGame();
		}
		else if(choice.equalsIgnoreCase("S")) 
		{
			StatsView statsV = new StatsView();
			statsV.showStats();
		}
	}
}