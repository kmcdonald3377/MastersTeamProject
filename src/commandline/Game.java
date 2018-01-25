package commandline;

//import java.util.Random;

public class Game {

	//these are for the game controller class????
//	private int numPlayers; //initially 5
//	private Deck deck;
//	private Round newRound;
//	private Player currentPlayer;
//	private String username;
	private int rounds;
	private int draws;
	private boolean humanWinner;

	// constructor
	public Game(Deck deck) {

		this.rounds = 0;
		this.draws = 0;
		this.humanWinner = false;
	}
	
	//for the game controller class???
//	//a method to randomly assign the first player in the game
//	private int firstPlayer() { 
//
////		Random rand = new Random();
////		int player = rand.nextInt(5) + 1;
//		
//		int rand = (int)Math.floor(Math.random() * numPlayers);
//		return rand;
//	}
//	
//	public Deck getDeck()
//	{
//		return deck;
//	}

	// a method to get the number of rounds in the game
	public int getNumRounds() {

		return rounds;
	}

	// increment the number of rounds in the game
	public void addRounds() {

		this.rounds++;
	}

	// a method to get the number of draws in the game
	public int getNumDraws() {

		return draws;
	}

	// increment draws
	public void addDraws() {

		this.draws++;
	}

	// check if the winner is human or AI player
	public boolean checkWinner() {

		return humanWinner;
	}

	// set the winner as a human player
	public void humanWinner(boolean humanWinner) {

		this.humanWinner = humanWinner;
	}
	public void printWinner() {
		
//	System.out.println("The winner of the game is " + PLAYER METHOD TO GET THE LIST OF PLAYERS.get(0).getName());
	}
}