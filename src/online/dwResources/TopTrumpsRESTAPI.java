package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.*;


@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controlled from a Web page.
 */
public class TopTrumpsRESTAPI 
{

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	MenuController menuC;
	ArrayList<Game> gameList;
	ArrayList<Round> roundList;
	Database database;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) 
	{
		menuC = new MenuController();
		database = new Database();
		gameList = new ArrayList<Game>();
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}
  
	private Game getGame(String matchID) 
	{
		int gameID = Integer.parseInt(matchID);
		for(Game x : gameList) 
		{
			if(gameID == x.getMatchID()) 
			{
				return x;
			}
		}
		return null;
	}
	
	@POST
	@Path("/startGame")
	public String startGame(@QueryParam("username") String username, @QueryParam("numberOfPlayers") String numberOfPlayers) throws IOException
	{		
		int playerNo = Integer.parseInt(numberOfPlayers) + 1;
		Game currentGame = new Game(menuC.readFile(), username, playerNo);
		gameList.add(currentGame);
		
		return oWriter.writeValueAsString(currentGame.getMatchID());
	}
	
	@POST
	@Path("/newRound")
	public String newRound(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		ArrayList<Player> playerList = currentGame.getPlayerList();
		ArrayList<Player> activePlayers = currentGame.getActivePlayers();
		Round currentRound = new Round(currentGame, playerList, activePlayers);
		currentGame.addToRoundList(currentRound);
		
		return oWriter.writeValueAsString(currentRound.getRoundID());
	}
	
	@GET
	@Path("/activePlayers")
	public String activePlayers(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getActivePlayers());
	}
	
	@GET
	@Path("/playerList") //could maybe take this out
	public String playerList(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getPlayerList());
	}
	
	@GET
	@Path("/communalPile")
	public String communalPile(@QueryParam("matchID") String matchID) throws IOException
	{
		try {
		Game currentGame = getGame(matchID);
		PileOfCards communalPile = currentGame.getCommunalPile();
		communalPile.setPlayerID(10);
		return oWriter.writeValueAsString(communalPile);
		}
		catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/playerHandSizes")
	public String playerHandSizes(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getPlayerHandSize());
	}
	
	@GET
	@Path("/playersToBeRemoved")
	public String playersToBeRemoved(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.playersToBeRemoved());
	}
	
	@GET
	@Path("/removedFromActivePlayers")
	public String removedFromActivePlayers(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.removeFromActivePlayers());
	}
	
	@GET
	@Path("/totalRounds")
	public String totalRounds(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getTotalRounds());
	}

	@POST
	@Path("/increaseRounds")
	public String increaseRounds(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.increaseRounds());
	}
	
	@GET
	@Path("/totalDraws")
	public String totalDraws(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getTotalDraws());
	}

	@POST
	@Path("/increaseDraws")
	public String increaseDraws(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.increaseDraws());
	}
	
	@GET
	@Path("/firstPlayer")
	public String firstPlayer(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		Player firstChoice = currentGame.setFirstChoice();
		return oWriter.writeValueAsString(firstChoice.getPlayerID());
	}
	
	@GET
	@Path("/categorySelection") //will get the category the ai is selecting
	public String categorySelection(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		return oWriter.writeValueAsString(currentRound.categorySelection());
	}
	
	@GET
	@Path("/maxScore")
	public String maxScore(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
	{
		String cat = Category.replaceAll("[-+.^:,\"]", "");
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		return oWriter.writeValueAsString(currentRound.findMaxScore(cat));
	}
	
	@GET
	@Path("/isWinner")
	public String isWinner(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
	{
		String cat = Category.replaceAll("[-+.^:,\"]", "");
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		boolean test = currentRound.isWinner(cat);
		int i = 0;
		if(test) {
			i = 1;
		}
		return oWriter.writeValueAsString(i);
	}
	
	@GET
	@Path("/findWinner")
	public String findWinner(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
	{
		String cat = Category.replaceAll("[-+.^:,\"]", "");
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		return oWriter.writeValueAsString(currentRound.findWinner(cat));
	}
	
	@POST
	@Path("/computeRoundWin")
	public String computeRoundWin(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
	{
		String cat = Category.replaceAll("[-+.^:,\"]", "");
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		ArrayList<Player> winner = currentRound.findWinner(cat);
		return oWriter.writeValueAsString(currentRound.computeWin(winner));
	}
	
	@POST
	@Path("/computeRoundDraw")
	public String computeRoundDraw(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
	{
		String cat = Category.replaceAll("[-+.^:,\"]", "");
		Game currentGame = getGame(matchID);
		Round currentRound = currentGame.getRoundList().getLast();
		ArrayList<Player> winners = currentRound.findWinner(cat);
		return oWriter.writeValueAsString(currentRound.computeDraw(winners));
	}

	
	//-----------------
	//database queries
	//-----------------
	
	
	@GET
	@Path("/matchesPlayed")
	public String matchesPlayed() throws IOException
	{
		return oWriter.writeValueAsString(database.getMatchesPlayed());

	}
	
	@GET
	@Path("/computerWonMatches")
	public String computerWonMatches() throws IOException
	{
		return oWriter.writeValueAsString(database.getComputerWonMatches());
		
	}
	
	@GET
	@Path("/humanWonMatches")
	public String humanWonMatches() throws IOException
	{
		return oWriter.writeValueAsString(database.getHumanWonMatches());

	}
	
	@GET
	@Path("/averageNumberOfDraws")
	public String averageNumberOfDraws() throws IOException
	{
		return oWriter.writeValueAsString(database.getAverageNumberOfDraws());

	}
	
	@GET
	@Path("/highestNumberOfRounds")
	public String highestNumberOfRounds() throws IOException
	{
		return oWriter.writeValueAsString(database.getHighestNumberOfRounds());
	}
	
	@POST
	@Path("/matchStatistics")
	public String matchStatistics(@QueryParam("MatchID") int MatchID, @QueryParam("WinnerID") int WinnerID, 
			@QueryParam("RoundsPlayed") int RoundsPlayed, @QueryParam("RoundsDrawn") int RoundsDrawn) throws IOException
	{
		return oWriter.writeValueAsString(database.writeToMatchStatistics(MatchID, WinnerID, RoundsPlayed, RoundsDrawn));
	}
	
	@POST
	@Path("/playerStatistics")
	public String playerStatistics(@QueryParam("PlayerID") int PlayerID, @QueryParam("MatchID") int MatchID, 
			@QueryParam("RoundsWon") int RoundsWon, @QueryParam("RoundsDrawn") int RoundsDrawn) throws IOException
	{
		return oWriter.writeValueAsString(database.writeToMatchStatistics(PlayerID, MatchID, RoundsWon, RoundsDrawn));
	}
	
	
	
	
	
	
	/*
	 * Extra methods - don't think need
	 */
	
//	@GET
//	@Path("/humanCategorySelection") //don't really need this
//	public String humanCategorySelection(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.humanCategorySelection(Category));
//	}
//	
//	@GET
//	@Path("/categoryValues") //do really need this?
//	public String categoryValues(@QueryParam("matchID") String matchID, @QueryParam("Category") String Category) throws IOException
//	{
//		Game currentGame = getGame(matchID);
//		Round currentRound = currentGame.getRoundList().getLast();
//		return oWriter.writeValueAsString(currentRound.categoryValues(Category));
//	}
	
//	
//	@GET
//	@Path("/currentCard")
//	public String currentCard(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("PlayerPosition") int PlayerPosition) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.getCard(PlayerPosition));
//	}
//	
//	@GET
//	@Path("/categoryValueList")
//	public String categoryValueList(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("CategoryComparison") HashMap<Integer, Integer> CategoryComparison) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.setCategoryValues(CategoryComparison));
//	}
}


