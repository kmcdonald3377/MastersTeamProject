package commandline;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuController 
{
	private String choice;
	private MenuView menuV;
	private Scanner scanner;
	private String name;
	private PileOfCards starCitizenDeck;

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

	public String GetName()
	{
		return name; 
	}

	public void setName()
	{
		menuV.setHumanName();
		name = menuV.getInput();
	}

	public void setChoiceFromUserInput() 
	{
		choice = menuV.getInput();
	}	

	public void readFile()
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
		
	}

	public void choiceSelection() 
	{
		if(choice.equalsIgnoreCase("G")) 
		{
			GameController gameC = new GameController(starCitizenDeck);
			gameC.startGame();
			gameC.runGame();
		}
		else if(choice.equalsIgnoreCase("S")) 
		{
			StatsView statsV = new StatsView();
			statsV.showStats();
		}
	}
}