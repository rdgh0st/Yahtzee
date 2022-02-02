import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Rob Davis
 * The Dice class contains information about the five dice on the field, and contains method to keep certain dice, roll while keeping that in mind,
 * and rolling without regard to what is kept or not.
 *
 */

public class DiceCup {
	private Die[] cup;
	private boolean[] kept;
	
	/**
	 * Constructor initializes Die array to empty Die, kept array to all false
	 */
	public DiceCup() {
		cup = new Die[5];
		kept = new boolean[5];
		for (boolean t : kept) {
			t = false;
		}
		for (int i = 0; i < cup.length; i++) {
			cup[i] = new Die();
		}
	}
	
	/**
	 * Toggles the kept value of requested Die, if i == -1 we roll and if it is any other index nothing happens
	 * @param i - Index of the Die to keep
	 */
	public void Keep(int i) {
		if (i == -1) {
			return;
		}
		if (i < 1 || i > 5) {
			System.out.println("Please Enter a Valid Die Index.");
		} else {
			kept[i - 1] = !kept[i - 1];
		}
	}
	
	/**
	 * RollDice cycles through each Die in the array. If the kept value is true, it is ignored.
	 * Otherwise, we call the Roll() method from the Die class
	 */
	public void RollDice() {
		for (int i = 0; i < kept.length; i++) {
			if (!kept[i]) {
				cup[i].Roll();
			}
			kept[i] = false;
		}
	}
	
	/**
	 * Prints only the dice values in standardized format
	 */
	public void PrintDice() {
		String ret = "Roll: ";
		for (int i = 0; i < cup.length; i++) {
			ret += (i + 1);
			ret += ": [";
			ret += cup[i];
			ret += "] ";
		}
		System.out.println(ret);
	}
	
	/**
	 * Prints the values of all Dice on one line
	 * Prints the values of whether that Die is currently kept or not on next line
	 * Everything in standardized format
	 */
	public void PrintWithKeep() {
		String ret = "Dice:	";
		for (int i = 0; i < cup.length; i++) {
			ret += (i + 1);
			ret += ":	[";
			ret += cup[i];
			ret += "]	";
		}
		System.out.println(ret);
		String nextRet = "Keep?:	";
		for (int i = 0; i < kept.length; i++) {
			nextRet += (i + 1);
			nextRet += ":	[";
			if (kept[i]) {
				nextRet += "Y";
			} else {
				nextRet += "N";
			}
			nextRet += "]	";
		}
		System.out.println(nextRet);
	}
	
	/**
	 * Method to check the dice for desired score category
	 * @param index - the category that the player wishes to check
	 * @return The score from the dice being passed into that category
	 */
	public int ScoreInCategory(int index) {
		switch (index) {
		case 0:
			return GetNumberScore(1);
		case 1:
			return GetNumberScore(2);
		case 2:
			return GetNumberScore(3);
		case 3:
			return GetNumberScore(4);
		case 4:
			return GetNumberScore(5);
		case 5:
			return GetNumberScore(6);
		case 6:
			return OfAKind(3);
		case 7:
			return OfAKind(4);
		case 8:
			return FullHouse();
		case 9:
			return FindStraight(false);
		case 10:
			return FindStraight(true);
		case 11:
			return CheckYahtzee();
		case 12:
			return Chance();
		}
		throw new IndexOutOfBoundsException("Please Enter a Valid Category");
	}
	
	/**
	 * This is for checking sets of 1/2/3/4/5/6
	 * @param numIndex - the number on dice that the player is requesting
	 * @return Score of the dice after being passed through with specified numIndex
	 */
	private int GetNumberScore(int numIndex) {
		int score = 0;
		for (Die d : cup) {
			if (d.getFaceUp() == numIndex) {
				score += numIndex;
			}
		}
		return score;
	}
	
	/**
	 * Checks for 3 of a kind or 4 of a kind
	 * @param howMany - Will check for 3 matching or 4 matching
	 * @return Score of the dice after being passed through with specified number of matching
	 */
	private int OfAKind(int howMany) {
		int score = 0;
		for (Die d : cup) {
			score += d.getFaceUp();
		}
		int[] freq = new int[6];
		for (Die d : cup) {
			freq[d.getFaceUp() - 1]++;
			if (freq[d.getFaceUp() - 1] >= howMany) {
				return score;
			}
		}
		return 0;
	}
	
	/**
	 * Checks the dice for a fullHouse by creating a frequency array and checking for 2 and 3 to appear in it
	 * @return Either 25 if there is a full house or 0 if there is not
	 */
	private int FullHouse() {
		boolean hasPair = false;
		boolean hasThree = false;
		int[] freq = new int[6];
		for (Die d : cup) {
			freq[d.getFaceUp() - 1]++;
		}
		for (int i : freq) {
			if (i == 2) {
				hasPair = true;
			} else if (i == 3) {
				hasThree = true;
			}
		}
		if (hasPair && hasThree) {
			return 25;
		} else {
			return 0;
		}
	}
	
	/**
	 * Checks for a straight in the dice
	 * @param isLarge - Set to true if checking for a large straight, false if small straight
	 * @return 40 for successful large straight, 30 for successful small straight, or 0 if neither
	 */
	private int FindStraight(boolean isLarge) {
		int[] nums = new int[5];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = cup[i].getFaceUp();
		}
		Arrays.sort(nums);
		if (isLarge) {
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] - nums[i - 1] != 1) {
					return 0;
				}
			}
			return 40;
		} else {
			// check if 1234, 2345, or 3456
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i : nums) {
				arr.add(i);
			}
			if (arr.contains(1) && arr.contains(2) && arr.contains(3) && arr.contains(4)) {
				return 30;
			} else if (arr.contains(2) && arr.contains(3) && arr.contains(4) && arr.contains(5)) {
				return 30;
			} else if (arr.contains(3) && arr.contains(4) && arr.contains(5) && arr.contains(6)) {
				return 30;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Checks if all five numbers are the same
	 * @return 50 if Yahtzee, 0 if not
	 */
	private int CheckYahtzee() {
		int num = cup[0].getFaceUp();
		for (Die d : cup) {
			if (d.getFaceUp() != num) {
				return 0;
			}
		}
		return 50;
	}
	
	/**
	 * Adds all dice numbers as score
	 * @return sum of all dice
	 */
	private int Chance() {
		int sum = 0;
		for (Die d : cup) {
			sum += d.getFaceUp();
		}
		return sum;
	}
	
	/**
	 * Sets all kept values to false and rolls again
	 * Used after scoring
	 */
	public void CompleteReroll() {
		for (int i = 0; i < cup.length; i++) {
			kept[i] = false;
		}
		RollDice();
	}
	
}
