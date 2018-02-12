package commandline;

import java.util.Scanner;

/*
 * View class - responsible for outputting to console allowing user to 
 * player a game. 
 */
public class GameView 
{
	private GameController gameC;
	private Scanner scanner;

	/**
	 * Method constructs a game view. It allows the player to play a 
	 * game using console output. 
	 * 
	 * Player input is handled through use of the scanner object.
	 * 
	 * @param gameC - GameController object which provides control to the game.
	 */
	public GameView(GameController gameC) 
	{
		this.gameC = gameC;
		scanner = new Scanner(System.in);
	}

	// outputs the game introduction to the player via console
	public void gameIntroduction() 
	{
		System.out.println("\nStar Citizen Deck has been shuffled and hands have been delt.\n");
	}

	/**
	 * Method outputs the number of cards each player has followed by 
	 * the details of your first card
	 * 
	 * @param humanCards, ai1Cards... ai4Cards - ints representing number of cards each
	 * player has
	 * @param card - card Object to get attributes/values from your card
	 */
	public void showCard(int humanCards, int ai1Cards, int ai2Cards, int ai3Cards, int ai4Cards, Card card) 
	{
		System.out.println("\nYou have " + humanCards + " cards.");
		System.out.println("AI Player 1 has " + ai1Cards + " cards.");
		System.out.println("AI Player 2 has " + ai2Cards + " cards."); 
		System.out.println("AI Player 3 has " + ai3Cards + " cards.");
		System.out.println("AI Player 4 has " + ai4Cards + " cards.");
		System.out.println("\nHere is your card:");
		System.out.println("-------------------------");
		if (card.getName().equalsIgnoreCase("merchantman") || card.getName().equalsIgnoreCase("constellation")
				|| card.getName().equalsIgnoreCase("vanguard")|| card.getName().equalsIgnoreCase("hurricane"))
		{
			System.out.println("|\t" + card.getName()+"\t|");
		}
		else
		{
			System.out.println("|\t" + card.getName()+"\t\t|");
		}
		System.out.println("-------------------------");
		if (card.getValue1()!=10)
		{
		System.out.println("|\t" + card.getAttribute1() + ": " + card.getValue1() + "\t\t|\n" + "|\t" + card.getAttribute2() +": " + 
				card.getValue2() + "\t|\n" + "|\t" + card.getAttribute3() +": " + card.getValue3() + "\t|\n" + "|\t" + card.getAttribute4() + ": " 
				+ card.getValue4() + "\t|\n" + "|\t" + card.getAttribute5() + ": " + card.getValue5() + "\t|");
		}
		else
			System.out.println("|\t" + card.getAttribute1() + ": " + card.getValue1() + "\t|\n" + "|\t" + card.getAttribute2() +": " + 
					card.getValue2() + "\t|\n" + "|\t" + card.getAttribute3() +": " + card.getValue3() + "\t|\n" + "|\t" + card.getAttribute4() + ": " 
					+ card.getValue4() + "\t|\n" + "|\t" + card.getAttribute5() + ": " + card.getValue5() + "\t|");
		System.out.println("-------------------------\n");
	}

	/**
	 * method shows number of cards each player has
	 * 
	 * @param humanCards, ai1Cards... ai4Cards - ints representing number of cards each
	 * player has
	 */
	public void showCardNumbers(int humanCards, int ai1Cards, int ai2Cards, int ai3Cards, int ai4Cards) 
	{
		System.out.println("\nYou have " + humanCards + " cards.");
		System.out.println("AI Player 1 has " + ai1Cards + " cards.");
		System.out.println("AI Player 2 has " + ai2Cards + " cards."); 
		System.out.println("AI Player 3 has " + ai3Cards + " cards.");
		System.out.println("AI Player 4 has " + ai4Cards + " cards.");
	}

	/**
	 * method outputs the first choice AI player and their choice to console
	 * 
	 * @param player - string representing which player is first to call
	 * @param category - category representing which category they choose
	 */
	public void aiSelectCategory(String player, String category) 
	{
		System.out.println(player + " is first to call.\n" + player + " has selected the category " + category);
		System.out.println("Please hit enter when you are ready to reveal cards for all players.");
	}

	/**
	 * method to output message asking user to in[put category choice 
	 * when they are first choice
	 */
	public void userSelectCategory() 
	{
		System.out.println("It is your choice to select the category."
				+ "\nPlease type the name of the category you wish to compare.");
	}

	/**
	 * method returns string representing player's choice of category
	 * @return input - user's category choice
	 */
	public String userInput() 
	{
		String input = scanner.nextLine();
		//scanner.close();
		return input;
	}

	/**
	 * method shows the stats of each players card
	 * 
	 * @param username - string of human players input username
	 * @param hValue...ai4Value - ints representing values of human/AI players attributes
	 */
	public void showStats(String username, int hValue, int ai1Value, int ai2Value, int ai3Value, int ai4Value) 
	{
		System.out.println("\n" + username + " has: " + hValue
				+ "\nAI Player 1 has: " + ai1Value
				+ "\nAI Player 2 has: " + ai2Value
				+ "\nAI Player 3 has: " + ai3Value
				+ "\nAI Player 4 has: " + ai4Value);
	}

	/**
	 * method shows winner
	 * 
	 * @param player - String representing winner of the round 
	 */
	public void showWinner(String player) 
	{
		System.out.println("\n" + player + " wins!");
		System.out.println("All cards has been surrendered to " + player);
	}

	/**
	 * method outputting players who have drawn when draw occurs 
	 * 
	 * @param player, player 2 - the string representations of drawing players
	 */
	public void showDraw(String player, String player2) 
	{
		System.out.println("\n" + player + " and " + player2 + " have drawn!");
		System.out.println("All players cards surrendered to communal pile.");
	}

	/**
	 * method details players removed from game as they run out of cards 
	 * 
	 * @param player - string representing player removed from game
	 */
	public void removedPlayers(String player) 
	{
		System.out.println("\n" + player + " has run out of cards.");
	}

	/**
	 * method to output message when player has lost and details of
	 * who has won the game 
	 * 
	 * @param winner - string of winning player
	 */
	public void humanLoses(String winner) 
	{
		System.out.println("\nYou have lost the game.");
		System.out.println(winner + " has won the game!");
	}

	// method outputs congratulations message when human wins
	public void humanWon() 
	{
		System.out.println("\nCongratulations!\nYou have won the game!");
	}

	// error message called when invalid input entered to select a category
	public void errorMessage() 
	{
		System.out.println("Error! Please enter one of the above categories:");
	}
		
	public void askDifficulty() 
	{
		System.out.println("Please select the number of the desired difficulty level: \n1. Easy \n2. Medium "
				+ "\n3. Hard\n");
	}
}