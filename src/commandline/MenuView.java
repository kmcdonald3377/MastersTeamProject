package commandline;

import java.util.Scanner;

/**
 * class to display menu to player
 *
 */
public class MenuView 
{	
	private MenuController menuC;
	
	/**
	 * method instantiates menu controller object
	 * 
	 * @param menuC
	 */
	public MenuView(MenuController menuC) 
	{
		this.menuC = menuC;
	}
	
	// method to prompt user to enter their username
	public void setHumanName()
	{
		System.out.println("\nPlease enter your name:");
	}
	
	// method displays menu to user
	public void displayMenu() 
	{
		System.out.println("\nIf you want to play a game enter G "
				+ "\nIf you want to view statstics enter S \nIf you want to quit the game enter Q");
	}
	
	/**
	 * method gets input from user using scanner
	 * 
	 * @return input from user
	 */
	public String getInput() 
	{
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

}
