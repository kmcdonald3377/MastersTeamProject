package commandline;

public class Player {

private PlayerHand hand;

private String AID1;
private String AID2;
private String AID3;
private String AID4;
private String PlayerID;
private PlayerHand AI1hand;
private PlayerHand AI2hand;
private PlayerHand AI3hand;
private PlayerHand AI4hand;
private String playername;
 
public Player(String Playername) 
{
	//Creates human player ID
	PlayerID = "Player" + playername;
	//Creates players hand 
	hand = new PlayerHand();
}
public void AIPlayer()
{
	//Creates first AI ID
	AID1 = "AI1"; 
	//first AI hand
	AI1hand = new PlayerHand();
}
public void AIPlayer2() 
{
	//Creates second AI ID
	AID2 = "AI2";
	//Second AI hand 
	AI2hand = new PlayerHand();
}
public void AIPlayer3()
{ 
	//Creates third AI ID
	AID3 = "AI3";
	//Third AI hand 
	AI3hand = new PlayerHand(); 
}
public void AIPlayer4() 
{
	//Creates fourth AI ID
	AID4 = "AI4";
	//Fourth AI hand 
	AI4hand = new PlayerHand(); 
}
}