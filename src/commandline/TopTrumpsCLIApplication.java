package commandline;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
	 * @param args
	 */
	public static void main(String[] args) {


		boolean writeGameLogsToFile = false; // Should we write game logs to file?

//		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		MenuController menuC = new MenuController();
		menuC.setName();

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) 
		{
			menuC.readFile();
			menuC.displayMenuV();
			menuC.setChoiceFromUserInput();
			menuC.choiceSelection();

			if(menuC.getChoice().equalsIgnoreCase("Q")) 
			{
				userWantsToQuit=true; // use this when the user wants to exit the game
			}
		}

	}

}
