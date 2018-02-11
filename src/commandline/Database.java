package commandline;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{

	private Connection connection =null;
	
	/**
	 * Constructor
	 */
	public Database() 
	{
		
	}
	
	/**
	 * Will make the connection to the database
	 */
	public void makeConnection() 
	{
		String databaseName = "m_17_2081415m", username = "m_17_2081415m", password = "2081415m";
		
		try //Will try connect to the database using the above details
		{
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + databaseName, username, password);
		}
		catch(SQLException e) 
		{
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		
		if (connection != null) 
		{
			System.out.println("Connection successful");
		}
		else 
		{
			System.err.println("Failed to make connection!");
		}
	}
	
	/**
	 * Will return the total number of matches that have been played.
	 * @return
	 */
	public int getMatchesPlayed() 
	{
		int matchesPlayed = 0;
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.matchID) AS total FROM toptrumps.matchstatistics";
		
		
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				matchesPlayed = rs.getInt("total");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return matchesPlayed;
	}
	
	/**
	 * Will return the number of matches in which the computer has won the match.
	 * @return
	 */
	public int getComputerWonMatches() 
	{
		int computerWins = 0;
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics "
				+ "WHERE (SELECT player.isai FROM toptrumps.player WHERE isai = true)";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				computerWins = rs.getInt("total");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return computerWins;
	}
	
	/**
	 * Will return the number of matches in which the human player has won the match.
	 * @return
	 */
	public int getHumanWonMatches() 
	{
		int humanWins = 0;
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics \"\r\n" + 
				"				+ \"WHERE (SELECT player.isai FROM toptrumps.player WHERE isai = false)";
		
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				humanWins = rs.getInt("total");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return humanWins;
	}
	
	/**
	 * Will return the average number of draws per match.
	 * @return
	 */
	public int getAverageNumberOfDraws() 
	{	
		int avgDraws = 0;
		Statement stmt = null;
		String query = "SELECT avg (matchstatistics.roundsdrawn) AS average FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				avgDraws = rs.getInt("average");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return avgDraws;
	}
	
	/**
	 * Will return the highest number of rounds played in a match so far.
	 * @return
	 */
	public int getHighestNumberOfRounds() 
	{
		int highestNumRnds = 0;
		Statement stmt = null;
		String query = "SELECT max (matchstatistics.roundsplayed) AS maximum FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				highestNumRnds = rs.getInt("maximum");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return highestNumRnds;
	}
	
	/**
	 * Will return the latest matchID so that a new unique one can be created.
	 * Rather than this would generate random identifiers for the matchID.
	 * @return
	 */
	public int getMaxMatchID() 
	{
		int maxMatch = 0;
		Statement stmt = null;
		String query = "SELECT max (matchstatistics.matchid) AS maximum FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				maxMatch = rs.getInt("maximum");
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return maxMatch;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList getPlayerId(int numberOfPlayers) 
	{
		ArrayList<Integer> playerIdList = new ArrayList();
		Statement stmt = null;
		String query = "SELECT player.playerid FROM toptrumps.player LIMIT " + numberOfPlayers;
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				int playerIDs = rs.getInt("playerid");
				playerIdList.add(playerIDs);
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return playerIdList;
	}
	
	/**
	 * method to insert match statistics to the database
	 * 
	 * @param matchID - int representing match number
	 * @param winnerID - int representing the player id of the winning player
	 * @param roundsPlayed - int representing the number of rounds played
	 * @param roundsDrawn - int representing the number of rounds drawn
	 */
	public boolean writeToMatchStatistics(int matchID, int winnerID, int roundsPlayed, int roundsDrawn) 
	{
		Statement stmt = null;
		String query = "INSERT INTO toptrumps.matchstatistics "
				+ "VALUES ('" + matchID + "', '" + winnerID + "', '" + roundsPlayed + "', '" + roundsDrawn + "')";
		
			try 
			{
				stmt = connection.createStatement();
				
				stmt.executeUpdate(query); //this will update the database with the added details without returning any values
				return true;
			}
			catch(SQLException e) 
			{
				System.out.println("Could not save match statistics.");
				return false;
			}
	}
	
	/**
	 * Will write the rounds won per player per match to the database
	 * 
	 * @param - playerID int representing the player ID
	 * @param - matchID int representing the match number 
	 * @param - roundsWon int representing the number of rounds won in the game
	 */
	public boolean writeToPlayerStatistics(int playerID, int matchID, int roundsWon, int roundsDrawn) 
	{
		Statement stmt = null;
		String query = "INSERT INTO toptrumps.playerstatistics "
				+ "VALUES ('" + playerID + "', '" + matchID + "', '" + roundsWon + "', '" + roundsDrawn + "')";
		
			try 
			{
				stmt = connection.createStatement();
				
				stmt.executeUpdate(query); //this will update the database with the added details without returning any values
				return true;
				
			}
			catch(SQLException e) 
			{
				System.out.println("Could not save indiviual player statistics for the match.");
				return false;
			}
	}
	
	/**
	 * This will close the connection to the database
	 */
	public void closeConnection() 
	{
		try 
		{
			connection.close();
			System.out.println("Connection closed");
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Connection could not be closed - SQL exception");
		}
	}
}
