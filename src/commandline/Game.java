// Robbie
package commandline;

public class Game {

	private int rounds;
	private int draws;
	private final int MAX_PLAYERS = 5;
	boolean gameOver = false;


	public Game() {
		// set up players for game
		// player 1 human
		Player human = new Player();
		// AI opponents
		human.Player();
		for (int i = 0; i<MAX_PLAYERS; i++)
		{
			
		}
	}

	public playGame() {
		while (!gameOver) {
			Round round = new Round();
			round.playRound();
			rounds++;
		}

	}

	// return number of rounds in game
	private int roundCount() {
		return rounds;
	}
}
