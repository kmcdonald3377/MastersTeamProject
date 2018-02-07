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
	Round currentRound;
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
	
	@GET
	@Path("/startGame")
	public String startGame(@QueryParam("username") String username) throws IOException
	{
		Game currentGame = new Game(menuC.readFile(), username);
		gameList.add(currentGame);
		
		return oWriter.writeValueAsString(currentGame.getMatchID());
	}
	
//	@GET
//	@Path("/newRound")
//	public String newRound(@QueryParam("currentGame") Game currentGame, @QueryParam("playerList") ArrayList<Player> playerList, 
//			@QueryParam("activePlayers") ArrayList<Player> activePlayers) throws IOException
//	{
//		currentRound = new Round(currentGame, playerList, activePlayers);
//		
//		return oWriter.writeValueAsString(currentRound);
//	}
//	
	@GET
	@Path("/activePlayers")
	public String activePlayers(@QueryParam("matchID") String matchID) throws IOException //cannot pass in a java object from javascript
	{ //going to have to be a string from JSON and then convert into a game object
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getActivePlayers());
	}
	
	@GET
	@Path("/playerList")
	public String playerList(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getPlayerList());
	}
	
	@GET
	@Path("/communalPile")
	public String communalPile(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getCommunalPile());
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
	
	@GET
	@Path("/totalDraws")
	public String totalDraws(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.getTotalDraws());
	}
	
	@GET
	@Path("/firstPlayer")
	public String firstPlayer(@QueryParam("matchID") String matchID) throws IOException
	{
		Game currentGame = getGame(matchID);
		return oWriter.writeValueAsString(currentGame.setFirstChoice());
	}
	
//	@GET
//	@Path("/categorySelection")
//	public String categorySelection(@QueryParam("currentRound") Round currentRound) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.categorySelection());
//	}
//	
//	@GET
//	@Path("/humanCategorySelection")
//	public String humanCategorySelection(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.humanCategorySelection(Category));
//	}
//	
//	@GET
//	@Path("/categoryValues")
//	public String categoryValues(@QueryParam("currentRound") Round currentRound,
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.categoryValues(Category));
//	}
//	
//	@GET
//	@Path("/maxScore")
//	public String maxScore(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.findMaxScore(Category));
//	}
//	
//	@GET
//	@Path("/isWinner")
//	public String isWinner(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.isWinner(Category));
//	}
//	
//	@GET
//	@Path("/findWinner")
//	public String findWinner(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("Category") String Category) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.findWinner(Category));
//	}
//	
//	@GET
//	@Path("/currentCard")
//	public String currentCard(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("PlayerPosition") int PlayerPosition) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.getCard(PlayerPosition));
//	}
	
//	@GET
//	@Path("/categoryValueList")
//	public String categoryValueList(@QueryParam("currentRound") Round currentRound, 
//			@QueryParam("CategoryComparison") HashMap<Integer, Integer> CategoryComparison) throws IOException
//	{
//		return oWriter.writeValueAsString(currentRound.setCategoryValues(CategoryComparison));
//	}
	
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
	
	@GET
	@Path("/maxMatchID")
	public String maxMatchID() throws IOException
	{
		return oWriter.writeValueAsString(database.getMaxMatchID());
	}
	
	@GET
	@Path("/playerIDs")
	public String playerIDs() throws IOException
	{
		return oWriter.writeValueAsString(database.getPlayerId());
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
}


