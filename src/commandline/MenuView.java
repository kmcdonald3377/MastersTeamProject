package commandline;

import java.util.Scanner;

public class MenuView 
{
	private MenuController menuC;
	public MenuView() 
	{
		
	}
	
	public void displayMenu() 
	{
		System.out.println("If you want to play a game enter G \nIf you want to view statstics enter S \nIf you want to quit the game enter Q");
		Scanner user = new Scanner(System.in);
		menuC = new MenuController();
		menuC.getUserChoice(user.next());
	}

}
