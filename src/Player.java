import java.util.Scanner;

/**
 * 
 * @author Rob Davis
 * The Player class stores information about each individual player, such as their scoresheet, current dice, and their ID.
 * This contains minor game logic about having three rounds to roll and choose what to keep.
 *
 */

public class Player {
	
	private static int numPlayers = 0;
	private DiceCup dice;
	private ScoreSheet scoring;
	private int playerID;
	
	/**
	 * Player constructor makes a new dicecup, scoresheet, and playerID, while increasing the total players int
	 */
	public Player() {
		dice = new DiceCup();
		scoring = new ScoreSheet();
		numPlayers++;
		playerID = numPlayers;
	}
	
	/**
	 * This contains the turn logic for each player's turn
	 */
	public void StartTurn() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("It is now Player " + playerID + "'s Turn.");
		
		for (int i = 0; i < 3; i++) {
			System.out.println("Roll " + (i + 1) + ": ");
			
			dice.RollDice();
			
			dice.PrintDice();
			System.out.println("Which dice do you want to keep?");
			System.out.println("Enter 1-5 to Toggle the Die or -1 to Roll");
			
			int input = 0;
			while (input != -1) {
				dice.PrintWithKeep();
				input = scan.nextInt();
				dice.Keep(input);
			}
			
		}
		System.out.println("Scoring Turn For Player " + playerID);
		dice.PrintDice();
		
		scoring.setDiceCup(dice);
		scoring.showAndSet();
		
		dice.CompleteReroll();
	}
	
	/**
	 * returns the scoresheet of the corresponding player
	 */
	public ScoreSheet getSheet() {
		return this.scoring;
	}
	
	/**
	 * 
	 * @return playerID
	 */
	public int getID() {
		return this.playerID;
	}
	
}
