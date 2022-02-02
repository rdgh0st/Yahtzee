import java.util.*;

/**
 * 
 * @author Rob Davis
 * The ScoreSheet class contains information about scores in individual categories, as well as methods to evaluate what should be scored 
 * in a specific category based on the current dice on the field.
 *
 */

public class ScoreSheet {
	private int[] sheet = new int[13];
	private boolean[] filled = new boolean[13];
	private DiceCup dice;
	
	/**
	 * Default constructor, sets scores of all items to zero and filled value to false
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

	/**
	 * Checks sum of scores in all categories
	 * @return total score
	 */
	public int getScore() {
		int sum = 0;
		for (int i : sheet) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Displays scores of all categories and their total at the bottom
	 */
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
	
	/**
	 * Displays scores in all categories with total at the bottom, then asks user to score their roll in a category
	 */
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
		System.out.println("Total:				" + getScore());
		
		Scanner scan = new Scanner(System.in);
		int input = 0;
		
		System.out.print("Choose 1 - 13 to score your roll:");
		input = scan.nextInt() - 1;
		System.out.println();
		
		while (input < 0 || input > 12 || filled[input]) {
			System.out.print("You have either already filled in a score for this category or the input is invalid, please select another:");
			input = scan.nextInt() - 1;
		} 
		
		sheet[input] = dice.ScoreInCategory(input);
		filled[input] = true;
	}
	
	/**
	 * Sets the DiceCup for this scoresheet to a new DiceCup
	 * @param The new DiceCup
	 */
	public void setDiceCup(DiceCup d) {
		dice = d;
	}
	
}
