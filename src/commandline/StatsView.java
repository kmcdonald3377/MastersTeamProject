package commandline;

public class StatsView 
{
	public StatsView() 
	{
		
	}
	
	public void showStats() 
	{
		System.out.println("\n--------------------");
		System.out.println("- Match Statistics -");
		System.out.println("--------------------");
		
		//correctly formatted string but need to put in the database results - will put in latterly so ensure that can all use the
		//command line at home rather than having to only use it in the uni labs
		String priorStats = String.format("\nTotal Games Played: %s\nGames player has won: %s\nGames computer has won: %s"
				+ "\nHighest number of rounds in a game: %s\nAverage number of draws: %s\n", "5", "3", "2", "5", "2");
		
		System.out.println(priorStats);
	}
}
