package commandline;

import java.sql.*;

/**
 *  class representing a player in game
 */
public class Player 
{
	private PileOfCards playerHand;
	private int playerID;
	private int roundsWon;
	private int roundsDrawn;

	/**
	 * constructor
	 */
	public Player() 
	{
		playerHand = new PileOfCards(playerID);
		roundsWon = 0;
		roundsDrawn = 0;
	}
	
	/**
	 * method returns players hand 
	 * 
	 * @return playerHand - PileOfCards of players hand/deck
	 */
	public PileOfCards getPlayerHand() 
	{
		return playerHand;
	}

	/**
	 * method gets player ID 
	 * 
	 * @return playerID - int representing playerID
	 */
	public int getPlayerId() 
	{
		return playerID;
	}

	/**
	 * method sets player id 
	 * 
	 * @param playerID - int representing playerID
	 */
	public void setPlayerId(int playerID) 
	{
		this.playerID = playerID;
	}
	
	/**
	 * method returns rounds won 
	 * 
	 * @returnroundsWon - int representing rounds won 
	 */
	public int getRoundsWon() 
	{
		return roundsWon;
	}
	
	/**
	 * method increments number of rounds won 
	 */
	public void increaseRoundsWon() 
	{
		roundsWon ++;
	}
	
	/**
	 * method returns number of rounds drawn 
	 * 
	 * @return roundsDrawn - int representing number of rounds drawn 
	 */
	public int getRoundsDrawn() 
	{
		return roundsDrawn;
	}
	
	/**
	 * method increments int roundsDrawn counting number of rounds drawn 
	 */
	public void increaseRoundsDrawn() 
	{
		roundsDrawn++;
	}
}