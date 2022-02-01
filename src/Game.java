import java.util.Scanner;

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
	}

}
