package commandline;

public class GameController 
{
	private GameView gameV;
	
	public GameController() 
	{
		gameV = new GameView(this);
	}
	
	public void startGame() 
	{
		gameV.gameIntroduction();
	}
	
	public void runGame() 
	{
		int i = 0;
		while(i < 5) //will make loop for as long as player has cards or the other ai does
		{
			gameV.showCard();
			//will need to have a method reference here which is responsible for randomly selecting a player in order to let
			//them select the category they wish to use. Will need to save the player to pick and the category they pick
			//if is human will need to allow them to select their own category and will need to make another method for this
			gameV.aiSelectCategory(null, null);
			//for user to hit enter to proceed but will need another one of these if the user is to select category
			gameV.userInput();
			//will need to get the stats of every players current card for the category selected
			gameV.showStats(null, i, i, i, i, i);
			//will need to add in a compare method here to allow for comparison of the different stats for the players
			gameV.showWinner(null, null, null);
			gameV.showDraw(null, null);
			//depending upon the outcome of the above comparison method will depending upon which message will be shown.
			//will also need methods here to remove or add cards to each respective hand depending upon winner or draw
			
			i++;	//just to prevent infinite looping for now		
		}
	}
}
