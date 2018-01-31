package commandline;

import java.util.Scanner;

public class GameView 
{
	private GameController gameC;
	private Scanner scanner;

	public GameView(GameController gameC) 
	{
		this.gameC = gameC;
		scanner = new Scanner(System.in);
	}
	
	public void gameIntroduction() 
	{
		System.out.println("\nStar Citizen Deck has been shuffled and hands have been delt.\n");
	}
	
	public void showCard(int humanCards, int ai1Cards, int ai2Cards, int ai3Cards, int ai4Cards, Card card) 
	{
		System.out.println("\nYou have " + humanCards + " cards.");
		System.out.println("AI Player 1 has " + ai1Cards + " cards.");
		System.out.println("AI Player 2 has " + ai2Cards + " cards."); 
		System.out.println("AI Player 3 has " + ai3Cards + " cards.");
		System.out.println("AI Player 4 has " + ai4Cards + " cards.");
		System.out.println("\nHere are the details of your first card:");
		System.out.println("---------------");
		System.out.println("" + card.getName());
		System.out.println("---------------");
		System.out.println(card.getAttribute1() + ": " + card.getValue1() + "\n" + card.getAttribute2() +": " + 
				card.getValue2() + "\n" + card.getAttribute3() +": " + card.getValue3() + "\n" + card.getAttribute4() + ": " 
				+ card.getValue4() + "\n" + card.getAttribute5() + ": " + card.getValue5());
		System.out.println("---------------\n");
	}
	
	public void showCardNumbers(int humanCards, int ai1Cards, int ai2Cards, int ai3Cards, int ai4Cards) 
	{
		System.out.println("\nYou have " + humanCards + " cards.");
		System.out.println("AI Player 1 has " + ai1Cards + " cards.");
		System.out.println("AI Player 2 has " + ai2Cards + " cards."); 
		System.out.println("AI Player 3 has " + ai3Cards + " cards.");
		System.out.println("AI Player 4 has " + ai4Cards + " cards.");
	}
	
	public void aiSelectCategory(String player, String category) 
	{
		System.out.println(player + " is first to call.\n" + player + " has selected the category " + category);
		System.out.println("Please hit enter when you are ready to reveal cards for all players.");
	}
	
	public void userSelectCategory() 
	{
		System.out.println("It is your choice to select the category."
				+ "\nPlease type the name of the category you wish to compare.");
	}
	
	public String userInput() 
	{
		String input = scanner.next();
		//scanner.close();
		return input;
	}
	
	public void showStats(String username, int hValue, int ai1Value, int ai2Value, int ai3Value, int ai4Value) 
	{
		System.out.println("\n" + username + " has: " + hValue
				+ "\nAI Player 1 has: " + ai1Value
				+ "\nAI Player 2 has: " + ai2Value
				+ "\nAI Player 3 has: " + ai3Value
				+ "\nAI Player 4 has: " + ai4Value);
	}
	
	public void showWinner(String player) 
	{
		System.out.println("\n" + player + " wins!");
		System.out.println("All cards has been surrendered to " + player);
	}
	
	public void showDraw(String player, String player2) 
	{
		System.out.println("\n" + player + " and " + player2 + " have drawn!");
		System.out.println("All players cards surrendered to communal pile.");
	}
	
	public void removedPlayers(String player) 
	{
		System.out.println("\n" + player + " has run out of cards.");
	}
	
	public void humanLoses(String winner) 
	{
		System.out.println("\nYou have lost the game.");
		System.out.println(winner + " has won the game!");
	}
	
	public void humanWon() 
	{
		System.out.println("\nCongratulations!\nYou have won the game!");
	}
	
	public void errorMessage() 
	{
		System.out.println("Error! Please enter one of the above categories:");
	}
}