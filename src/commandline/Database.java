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
	public String getMatchesPlayed() 
	{
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.matchID) AS total FROM toptrumps.matchstatistics";
		
		String result = "";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String totalMatchesPlayed = rs.getString("total");
				result += totalMatchesPlayed;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return result;
	}
	
	/**
	 * Will return the number of matches in which the computer has won the match.
	 * @return
	 */
	public String getComputerWonMatches() 
	{
		String result = "";
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics "
				+ "WHERE (SELECT player.isai FROM toptrumps.player WHERE isai = true)";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String computerWins = rs.getString("total");
				result += computerWins;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return result;
	}
	
	/**
	 * Will return the number of matches in which the human player has won the match.
	 * @return
	 */
	public String getHumanWonMatches() 
	{
		Statement stmt = null;
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics \"\r\n" + 
				"				+ \"WHERE (SELECT player.isai FROM toptrumps.player WHERE isai = false)";
		String result = "";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String humanWins = rs.getString("total");
				result += humanWins;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return result;
	}
	
	/**
	 * Will return the average number of draws per match.
	 * @return
	 */
	public String getAverageNumberOfDraws() 
	{	
		String result = "";
		Statement stmt = null;
		String query = "SELECT avg (matchstatistics.roundsdrawn) AS average FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String averageRoundDraws = rs.getString("average");
				result += averageRoundDraws + "\n";
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return result;
	}
	
	/**
	 * Will return the highest number of rounds played in a match so far.
	 * @return
	 */
	public String getHighestNumberOfRounds() 
	{
		String test = "";
		Statement stmt = null;
		String query = "SELECT max (matchstatistics.roundsplayed) AS maximum FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String highestRounds = rs.getString("maximum");
				test += highestRounds;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return test;
	}
	
	/**
	 * Will return the latest matchID so that a new unique one can be created.
	 * Rather than this would generate random identifiers for the matchID.
	 * @return
	 */
	public String getMaxMatchID() 
	{
		String test = "";
		Statement stmt = null;
		String query = "SELECT max (matchstatistics.matchid) AS maximum FROM toptrumps.matchstatistics";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				String highestRounds = rs.getString("maximum");
				test += highestRounds;
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return test;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList getPlayerId() 
	{
		ArrayList<Integer> test = new ArrayList();
		Statement stmt = null;
		String query = "SELECT player.playerid FROM toptrumps.player";
		
		try 
		{
			stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				int playerIDs = rs.getInt("playerid");
				test.add(playerIDs);
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}
		
		return test;
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
