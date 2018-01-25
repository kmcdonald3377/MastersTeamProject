package commandline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;

public class GameView 
{
	private GameController gameC;

	public GameView(GameController gameC) 
	{
		this.gameC = gameC;
	}
	
	public void gameIntroduction() 
	{
		System.out.println("Star Citizen Deck has been shuffled and hands have been delt.\n"
				+ "You have 8 cards.");
	}
	
	public void showCard() 
	{
		System.out.println("Here is the details of your first card:\n");
		System.out.println("---------------");
		System.out.println("Description of card:\nSize:\nSpeed:\nRange:\nFirepower:\nCargo:");
		System.out.println("---------------\n");
	}
	
	public void aiSelectCategory(String player, String category) 
	{
		System.out.println(player + " is first to call.\n" + player + "has selected the category " + category);
		System.out.println("Please hit enter when you are ready to reveal cards for all players.");
	}
	
	public void selectCategory() 
	{
		System.out.println("It is your choice to select the category."
				+ "\nPlease type the name of the category you wish to compare.");
	}
	
	public String userInput() 
	{
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	public void showStats(String username, int hValue, int ai1Value, int ai2Value, int ai3Value, int ai4Value) 
	{
		System.out.println(username + " have: " + hValue
				+ "\nAI Player 1 has: " + ai1Value
				+ "\nAI Player 2 has: " + ai2Value
				+ "\nAI Player 3 has: " + ai3Value
				+ "\nAI Player 4 has: " + ai4Value);
	}
	
	public void showWinner(String player, String username, String cardName) 
	{
		System.out.println(player + " wins!");
		System.out.println(username + " card " + cardName + " has been surrendered to " + player);
	}
	
	public void showDraw(String player, String player2) 
	{
		System.out.println(player + " and " + player2 + "have drawn!");
		System.out.println("All players cards surrendered to communal pile.");
	}
}