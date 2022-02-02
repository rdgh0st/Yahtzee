import java.util.Scanner;

/**
 * 
 * @author Rob Davis
 * The Game class contains the foundational game logic for yahtzee. It takes the specified number of players, initiates turns in the right order,
 * and then evaluates a winner and reports back.
 *
 */

public class Game {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Yahtzee!");
		
		System.out.println("How many players will there be?");
		int total = scan.nextInt();
		
		Player[] players = new Player[total];
		
		// initialize player objects in array
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player();
		}
		
		// take 13 turns, cycling through all players for each turn
		for (int turn = 1; turn <= 13; turn++) {
			for (int i = 0; i < players.length; i++) {
				players[i].StartTurn();
			}
		}
		
		int maxScore = 0;
		int maxScoreIndex = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i].getSheet().getScore() > maxScore) {
				maxScore = players[i].getSheet().getScore();
				maxScoreIndex = i;
			}
		}
		
		System.out.println("Player " + players[maxScoreIndex].getID() + " wins with a total score of " + players[maxScoreIndex].getSheet().getScore() + "! Congratulations!");
		System.out.println("Thanks for Playing!");
		
	}

}
