package commandline;

import java.util.Scanner;

public class MenuView 
{	
	private MenuController menuC;
	
	public MenuView(MenuController menuC) 
	{
		this.menuC = menuC;
	}
	public void Name()
	{
		System.out.println("Please enter your name");
	}
	
	public void displayMenu() 
	{
		System.out.println("If you want to play a game enter G "
				+ "\nIf you want to view statstics enter S \nIf you want to quit the game enter Q");
	}
	
	public String getInput() 
	{
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

}
