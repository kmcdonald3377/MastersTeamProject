package commandline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;

public class playgameview {
	private String choice; 
	private Card[] player;

public void Menuview() {
	System.out.print("If you want to play a game enter G \n if you want to view statstics enter S \n if you want to quit the game enter Q");
	Scanner user = new Scanner(System.in);
	while(user.hasNext()); 
	choice = user.next();
	if(choice.equals("G")) {
		GameView(player); 
	}
	else if(choice.equals("S")) {
		//Call statics view
	}
	else if (choice.equals("Q")) {
		//quit game 
	}
}

public void GameView(Card[] player) {
	System.out.print("Select number of players...");
	Scanner players = new Scanner(System.in);
	while(players.hasNext());
	String numplayer = players.next();
	int NumPlayer = Integer.parseInt(numplayer);
	if((NumPlayer <= 2) && (NumPlayer >= 4)) {
	System.out.print("You've selected " + player);
	} else {
	System.out.print("Invalid number of players");
	}
	int i = 1;
	while((player.length >= 1) && (player.length <= 39)){
	//Call select category method 
	//Call method for selecting current card 
	int currentcard = 1; //Just to stop errors need to pass the value of current card from the method for selecting card 
	System.out.print(player[currentcard]); 
	//Call method for comparing values 
	System.out.print("You currently have " + player.length + "Cards");
	i++; 
	if(player.length <= 0) {
	System.out.print("You've lost the game");
	}
	if(player.length >= 40) {
	System.out.print("You've won the game"); 
	}
	}
}
}
