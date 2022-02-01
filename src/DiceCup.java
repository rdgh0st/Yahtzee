import java.util.ArrayList;
import java.util.Arrays;

// TEST TO MAKE SURE IT PUSHES RIGHT
// MAKE PRIVATE SCORING METHODS
public class DiceCup {
	private Die[] cup;
	private boolean[] kept;
	
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
	
	public void RollDice() {
		for (int i = 0; i < kept.length; i++) {
			if (!kept[i]) {
				cup[i].Roll();
			}
			kept[i] = false;
		}
	}
	
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
	
	private int GetNumberScore(int numIndex) {
		int score = 0;
		for (Die d : cup) {
			if (d.getFaceUp() == numIndex) {
				score += numIndex;
			}
		}
		return score;
	}
	
	private int OfAKind(int howMany) {
		int score = 0;
		for (Die d : cup) {
			score += d.getFaceUp();
		}
		int[] freq = new int[6];
		for (Die d : cup) {
			freq[d.getFaceUp()]++;
			if (freq[d.getFaceUp()] >= howMany) {
				return score;
			}
		}
		return 0;
	}
	
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
	
	private int CheckYahtzee() {
		int num = cup[0].getFaceUp();
		for (Die d : cup) {
			if (d.getFaceUp() != num) {
				return 0;
			}
		}
		return 50;
	}
	
	private int Chance() {
		int sum = 0;
		for (Die d : cup) {
			sum += d.getFaceUp();
		}
		return sum;
	}
	
	public void CompleteReroll() {
		for (int i = 0; i < cup.length; i++) {
			kept[i] = false;
		}
		RollDice();
	}
	
}
