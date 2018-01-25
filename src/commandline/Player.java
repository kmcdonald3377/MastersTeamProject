package commandline;

import java.sql.*;

public class Player 
{
	private PlayerHand hand;
	private String playerID;

	public Player() 
	{
		hand = new PlayerHand(playerID);
	}

	public String getPlayerId() 
	{
		return playerID;
	}
	
	public void setPlayerId(String playerID) 
	{
		this.playerID = playerID;
	}
}