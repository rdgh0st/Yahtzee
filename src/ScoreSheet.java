import java.util.*;

public class ScoreSheet {
	private int[] sheet = new int[13];
	private boolean[] filled = new boolean[13];
	private DiceCup dice;
	
	/**
	 * Default constructor, sets scores of all items to zero
	 */
	public ScoreSheet() {
		for (int i : sheet) {
			i = 0;
		}
		for (boolean b : filled) {
			b = false;
		}
		dice = new DiceCup();
	}

	public int getScore() {
		int sum = 0;
		for (int i : sheet) {
			sum += i;
		}
		return sum;
	}

	public void showScores() {
		for (int i = 0; i < sheet.length; i++) {
			String type = "";
			switch (i) {
			case 0:
				type = "Aces:			[";
				break;
			case 1:
				type = "Twos:			[";
				break;
			case 2:
				type = "Threes:			[";
				break;
			case 3:
				type = "Fours:			[";
				break;
			case 4:
				type = "Fives:			[";
				break;
			case 5:
				type = "Sixes:			[";
				break;
			case 6:
				type = "Triple:			[";
				break;
			case 7:
				type = "Quad:			[";
				break;
			case 8:
				type = "Full House:		[";
				break;
			case 9:
				type = "Small Straight:		[";
				break;
			case 10:
				type = "Large Straight:		[";
				break;
			case 11:
				type = "Yahtzee:		[";
				break;
			case 12:
				type = "Chance:			[";
				break;
			}
			System.out.println(type + sheet[i] + "]");
		}
		System.out.println("---------------------------");
		System.out.println("Total:			" + getScore());
	}
	
	public void showAndSet() {
		System.out.println("Where would you like to score this roll?");
		for (int i = 0; i < sheet.length; i++) {
			String type = "";
			switch (i) {
			case 0:
				type = "Aces:			[";
				break;
			case 1:
				type = "Twos:			[";
				break;
			case 2:
				type = "Threes:			[";
				break;
			case 3:
				type = "Fours:			[";
				break;
			case 4:
				type = "Fives:			[";
				break;
			case 5:
				type = "Sixes:			[";
				break;
			case 6:
				type = "Triple:			[";
				break;
			case 7:
				type = "Quad:			[";
				break;
			case 8:
				type = "Full House:			[";
				break;
			case 9:
				type = "Small Straight:		[";
				break;
			case 10:
				type = "Large Straight:		[";
				break;
			case 11:
				type = "Yahtzee:			[";
				break;
			case 12:
				type = "Chance:			[";
				break;
			}
			int choice = i + 1; 
			System.out.println(choice + " - " + type + sheet[i] + "]");
		}
		System.out.println("-----------------------------------");
		System.out.println("Total:			" + getScore());
		Scanner scan = new Scanner(System.in);
		int input = 0;
		System.out.print("Choose 1 - 13 to score your roll:");
		input = scan.nextInt() - 1;
		System.out.println();
		while (filled[input]) {
			System.out.print("You have already filled in a score for this category, please select another:");
			input = scan.nextInt() - 1;
		} 
		sheet[input] = dice.ScoreInCategory(input);
		filled[input] = true;
	}
	
}
