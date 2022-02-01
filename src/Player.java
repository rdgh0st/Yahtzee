import java.util.Scanner;

public class Player {
	
	private static int numPlayers = 0;
	private DiceCup dice;
	private ScoreSheet scoring;
	private int playerID;
	
	public Player() {
		dice = new DiceCup();
		scoring = new ScoreSheet();
		numPlayers++;
		playerID = numPlayers;
	}
	
	public void StartTurn() {
		Scanner scan = new Scanner(System.in);
		System.out.println("It is now Player " + playerID + "'s Turn.");
		for (int i = 0; i < 3; i++) {
			System.out.println("Roll " + i + ": ");
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
	
}
