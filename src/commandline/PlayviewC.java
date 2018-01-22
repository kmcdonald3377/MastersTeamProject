package commandline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;

public class PlayviewC {
	private String choice; 

public void Menuview() {
	System.out.print("If you want to play a game enter G \n if you want to view statstics enter S \n if you want to quit the game enter Q");
	Scanner user = new Scanner(System.in);
	while(user.hasNext()); 
	choice = user.next();
	if(choice.equals("G")) {
		GameView(); 
	}
	else if(choice.equals("S")) {
		//Call statics view
	}
	else if (choice.equals("Q")) {
		//quit game 
	}
}
public void GameView() {
	//Pick randomly from player array 
	
	//If user is picked 
	System.out.println("Select a category");
	Scanner category = new Scanner(System.in);
	
}
}