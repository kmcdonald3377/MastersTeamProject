<<<<<<< HEAD
<<<<<<< HEAD
// Robbie
package commandline;
public class Player {
//	IVS
	private PlayerHand hand;
	private String name;
	prvate int playerID;
	private static int playerNum;
	
	public Player() {
		playerNum = 0;
		playerID = playerNum;
		name = "Player " + playerNum;
		hand = new PlayerHand();
	}
	
	public AIPlayer() {
		playerNum++;
		name = "Player " + playerNum;
		hand = new PlayerHand();
	}
=======
package commandline;

import java.sql.*;

public class Player {

private PlayerHand hand;
private String ID;
 
public Player() 
{
	//Creates players hand 
	hand = new PlayerHand();
}
public String PlayerID() {
	//Returns ID from database 
	Statement stmt = null;
	String query = "SELECT ID FROM toptrump.player";
>>>>>>> eab7ee4f2b12554a5f19e9a08d30643038d49b1f
	
=======
package commandline;

import java.sql.*;

public class Player {

private PlayerHand hand;
private String ID;
 
public Player() 
{
	//Creates players hand 
	hand = new PlayerHand();
}
public String PlayerID() {
	//Returns ID from database 
	Statement stmt = null;
	String query = "SELECT ID FROM toptrump.player";
	
>>>>>>> eab7ee4f2b12554a5f19e9a08d30643038d49b1f
		try 
		{	
			//stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(query); 
		
			while (rs.next()) {
				ID = rs.getString("ID");
		}
		}
		catch(SQLException e) 
		{
			System.out.println("Could not get player ID");
		}
		return ID;
}
}