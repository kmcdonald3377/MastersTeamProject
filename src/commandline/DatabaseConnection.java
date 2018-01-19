package commandline;

import java.sql.*;

public class DatabaseConnection 
{

	private Connection connection =null;
	
	/**
	 * Constructor
	 */
	public DatabaseConnection() 
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
		String query = "SELECT COUNT (matchstatistics.id) AS total FROM toptrumps.matchstatistics";
		
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
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics WHERE matchstatistics.winner = 'Computer'";
		
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
		String query = "SELECT COUNT (matchstatistics.winner) AS total FROM toptrumps.matchstatistics WHERE matchstatistics.winner = 'Human'";
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
		String query = "SELECT avg (matchstatistics.numberofdraws) AS average FROM toptrumps.matchstatistics";
		
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
		String query = "SELECT max (matchstatistics.numberofrounds) AS maximum FROM toptrumps.matchstatistics";
		
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
