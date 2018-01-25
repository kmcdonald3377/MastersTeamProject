package commandline;

import java.sql.*;

public class Player {

private PlayerHand hand;
private String ID, AID1, AID2, AID3, AID4;
 
public Player() 
{
	//Creates players hand 
	hand = new PlayerHand();
}
public String PlayerID() {
	//Returns ID from database 
	Statement stmt = null;
	String query = "SELECT ID FROM toptrump.player";
	
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
public void AIID()
{
	//Sets ID of AI players 
	AID1 = "AI1";
	AID2 = "AI2";
	AID3 = "AI3";
	AID4 = "AI4"; 
}
}