package commandline;

public class StatsView 
{
	Database database;
	
	public StatsView() 
	{
		database = new Database();
		database.makeConnection();
	}
	
	public void showStats() 
	{
		System.out.println("\n--------------------");
		System.out.println("- Match Statistics -");
		System.out.println("--------------------");
		
		//correctly formatted string but need to put in the database results - will put in latterly so ensure that can all use the
		//command line at home rather than having to only use it in the uni labs
		String priorStats = String.format("\nTotal Games Played: %d\nGames player has won: %d\nGames computer has won: %d"
				+ "\nHighest number of rounds in a game: %d\nAverage number of draws: %d\n", database.getMatchesPlayed(), database.getHumanWonMatches(), 
				database.getComputerWonMatches(), database.getHighestNumberOfRounds(), database.getAverageNumberOfDraws());
		
		System.out.println(priorStats);
		database.closeConnection();
	}
}
