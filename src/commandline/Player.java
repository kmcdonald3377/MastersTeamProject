// Robbie
public class Player {
//	IVS
	private PlayerHand hand;
	private String name;
	private static int playerNum;
	
	public Player() {
		playerNum++;
		name = "Player " + playerNum;
		hand = new PlayerHand();
	}
}
