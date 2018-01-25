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