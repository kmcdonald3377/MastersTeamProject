package commandline;

import java.util.ArrayList;

public class GameController 
{
	private GameView gameV;
	private CommunalPile cp;
	private Game currentGame;
	private ArrayList <PlayerHand> playerList;
	
	public GameController() 
	{
		gameV = new GameView(this);
		cp = new CommunalPile();
		currentGame = new Game();
		playerList = currentGame.getPlayerList();
	}
	
	public void startGame() 
	{
		gameV.gameIntroduction();
	}
	
	public void runGame() 
	{
		while(isValid()) //will make loop for as long as player has cards or the other ai does
		{
			getCard();
			//will need to have a method reference here which is responsible for randomly selecting a player in order to let
			//them select the category they wish to use. Will need to save the player to pick and the category they pick
			//if is human will need to allow them to select their own category and will need to make another method for this
			if() 
			{
				gameV.aiSelectCategory(null, null);
				gameV.userInput(); //for enter command to try break up flow to console
			}
			else 
			{
				
			}
			
			
			//will need to get the stats of every players current card for the category selected
			gameV.showStats(null, i, i, i, i, i);
			//will need to add in a compare method here to allow for comparison of the different stats for the players
			
			
			if() //if there is an outright winner
			{
				computeWin();
			}
			else //else if it is a draw then show draw message
			{
				computeDraw();
			}	
		}
	}
	
	private void getCard() 
	{
		Card currentCard = playerList.get(0).getCurrentCard();
		gameV.showCard(currentCard.getName(), currentCard.getValue1(), currentCard.getValue2(), currentCard.getValue3(), 
				currentCard.getValue4(), currentCard.getValue5());
	}
	
	private boolean isValid() 
	{
		if(playerList.get(0).getNumberOfCards() == 0 || playerList.get(0).getNumberOfCards() == 40) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	private void computeDraw() 
	{
		for(int i = 0; i < playerList.size(); i++) 
		{
			Card currentCard = playerList.get(i).getCurrentCard(); //identifies current card
			cp.addCard(currentCard); //adds current card to communal pile
			playerList.get(i).removeFromHand(currentCard); //removes current card from each players hand
		}
		gameV.showDraw(null, null); //need to feed in the two players who drew
	}
	
	private void computeWin() 
	{
		gameV.showWinner(null, null, null);
	}
}
