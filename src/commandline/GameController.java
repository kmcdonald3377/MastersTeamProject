package commandline;

import java.util.ArrayList;
import java.util.Random;

public class GameController 
{
	private GameView gameV;
	private PileOfCards communalPile;
	private Player firstChoice;
	private Game currentGame;
	private ArrayList <Player> playerList;
	
	public GameController(PileOfCards starCitizenDeck) 
	{
		gameV = new GameView(this);
		communalPile = new PileOfCards(null); //0 passed in as no player with an id of 0
		currentGame = new Game(starCitizenDeck);
		playerList = currentGame.getPlayerList();
	}
	
	public void startGame() 
	{
		gameV.gameIntroduction();
		setFirstChoice();
	}
	
	public void runGame() 
	{
		while(isValid()) //will make loop for as long as player has cards or 
		{
			Round2 currentRound = new Round2(playerList);
			displayCard(currentRound); //displays card to user
			
			
			
			ArrayList<Integer> categoryComparison;
						
			
			if(firstChoice.getPlayerId() != 0) 
			{
				
				categoryComparison = currentRound.aiCategorySelection();
				gameV.aiSelectCategory(""+firstChoice.getPlayerId(), null);
				gameV.userInput(); //for enter command to try break up flow to console
			}
			else 
			{
				gameV.userSelectCategory();
				String category = gameV.userInput();
				categoryComparison = currentRound.humanCategorySelection(category);
			}
			
			
			
			
			
			
			gameV.showStats("Test", categoryComparison.get(0), categoryComparison.get(1), 
					categoryComparison.get(2), categoryComparison.get(3), categoryComparison.get(4));

			int winningScore = currentRound.findWinner(categoryComparison);
			int winner;
			
			for(int i = 0; i < playerList.size(); i++) 
			{
				//need to be able to get the player/pile of cards which has the winner score - may be one or multiple for draw
			}
				
			if(currentRound.isWinner(categoryComparison)) //if there is an outright winner
			{
				computeWin();
				//increase round won for winner
			}
			else //else if it is a draw then show draw message
			{
				computeDraw();
				currentGame.increaseDraws();
				//increase rounds drawn for the players who drew
			}
			currentGame.increaseRounds();
			int id = firstChoice.getPlayerID();
			if(id == 5) 
			{
				id = 1;
			}
			else 
			{
				id++;
			}
			firstChoice = playerList.get(id);
		}
	}
	
	private void displayCard(Round2 current) 
	{
		Card currentCard = current.getCard(0); //0 as only currently showing the human players card
		
		gameV.showCard(currentCard);
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
			communalPile.addCard(currentCard); //adds current card to communal pile
			playerList.get(i).removeCard(currentCard); //removes current card from each players hand
		}
		gameV.showDraw(null, null); //need to feed in the two players who drew
	}
	
	private void computeWin(int winner) 
	{
		playerList.get(winner).addCard(playerList.get(winner).getCurrentCard());
		playerList.get(winner).removeCard(playerList.get(winner).getCurrentCard()); //this will move the current card to the back of the deck
		
		for(int i = 0; i < playerList.size(); i++) 
		{
			if(i == winner) 
			{
				
			}
			else 
			{
				playerList.get(winner).addCard(playerList.get(i).getCurrentCard()); //will add card to winners hand
				playerList.get(i).removeCard(playerList.get(i).getCurrentCard()); //will remove card from losers hand
			}
		}
		
		while(communalPile.getNumberOfCards() > 0) 
		{
			playerList.get(winner).addCard(communalPile.getCurrentCard());
			communalPile.removeCard(communalPile.getCurrentCard());
		}
				
		gameV.showWinner("" + playerList.get(winner).getPlayerID());
	}
	
	private void setFirstChoice() 
	{
		Random randomNumber = new Random();
		int myNumber = randomNumber.nextInt(playerList.size()-1);
		
		firstChoice = playerList.get(myNumber);
	}
	
}
