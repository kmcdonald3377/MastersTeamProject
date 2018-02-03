package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.MenuController;
import commandline.PileOfCards;


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
public class TopTrumpsRESTAPI {

/** A Jackson Object writer. It allows us to turn Java objects
* into JSON strings easily. */
ObjectWriter Cardobj = new ObjectMapper().writerWithDefaultPrettyPrinter();
private MenuController deckCaller;
/**
* Contructor method for the REST API. This is called first. It provides
* a TopTrumpsJSONConfiguration from which you can get the location of
* the deck file and the number of AI players.
* @param conf
*/
public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
// ----------------------------------------------------
// Add relevant initalization here
// ----------------------------------------------------
}
// ----------------------------------------------------
// Add relevant API methods here
// ----------------------------------------------------

@GET
@Path("/CardList")
/**
* Here is an example of a simple REST get request that returns a String.
* We also illustrate here how we can convert Java objects to JSON strings.
* @return - List of words as JSON
* @throws IOException
*/

	

public String CardList(PileOfCards TopTrumpsDeck) throws IOException {

//deckCaller.readFile();
//TopTrumpsDeck = new PileOfCards(null);


String listofCards = Cardobj.writeValueAsString(TopTrumpsDeck); //Turn command line array of cards into a JSON array of cards 
return listofCards; //Sending as null, probably cause the TopTrumpsDeck is null, need to send it the value of StarCitzenDeck as read from the text file

}
@GET
@Path("/hellocards")
/**
* Here is an example of how to read parameters provided in an HTML Get request.
* @param Word - A word
* @return - A String
* @throws IOException
*/
public String hellocards(@QueryParam("New Deck") String ListofCards) throws IOException {
return ListofCards;
}
}


