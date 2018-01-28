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
		try
		{
			FileReader reader = new FileReader("StarCitizenDeck.txt");

			scanner = new Scanner(reader);
			ArrayList<String> input = new ArrayList();

			while(scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();
				input.add(line);
			}

			String[] description = input.get(0).split(" +");


			for(int i = 1; i < input.size(); i++) 
			{
				String[] values = input.get(i).split(" +");

				starCitizenDeck = new PileOfCards(0);
				starCitizenDeck.addCard(new Card(description[1], description[2], description[3], description[4], description[5], 
						values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), 
						Integer.parseInt(values[4]), Integer.parseInt(values[5])));
			}
		}
		catch(Exception e) 
		{

		}
	}

	public void choiceSelection() 
	{
		if(choice.equalsIgnoreCase("G")) 
		{
			GameController gameC = new GameController(starCitizenDeck);
			gameC.startGame();
		}
		else if(choice.equalsIgnoreCase("S")) 
		{
			StatsView statsV = new StatsView();
			statsV.showStats();
		}
	}
}