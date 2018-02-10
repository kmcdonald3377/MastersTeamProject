package commandline;

import java.sql.*;

public class Player 
{
	private PileOfCards playerHand;
	private int playerID;
	private int roundsWon;
	private int roundsDrawn;

	public Player() 
	{
		playerHand = new PileOfCards(playerID);
		roundsWon = 0;
		roundsDrawn = 0;
	}
	
	public PileOfCards getPlayerHand() 
	{
		return playerHand;
	}

	public int getPlayerID() 
	{
		return playerID;
	}

	public void setPlayerId(int playerID) 
	{
		this.playerID = playerID;
	}
	
	public int getRoundsWon() 
	{
		return roundsWon;
	}
	
	public void increaseRoundsWon() 
	{
		roundsWon ++;
	}
	
	public int getRoundsDrawn() 
	{
		return roundsDrawn;
	}
	
	public void increaseRoundsDrawn() 
	{
		roundsDrawn++;
	}
}