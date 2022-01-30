
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
			RollDice();
			return;
		}
		if (i < 1 || i > 5) {
			throw new IndexOutOfBoundsException("Please Enter a Valid Die Index");
		} else {
			kept[i - 1] = true;
		}
	}
	
	public void RollDice() {
		for (int i = 0; i < kept.length; i++) {
			if (!kept[i]) {
				cup[i].Roll();
			}
			kept[i] = true;
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
	
	public int Score(int index) {
		int score = 0;
		switch (index) {
		case 1:
			for (Die d : cup) {
				if (d.getFaceUp() == 1) {
					score++;
				}
			}
			break;
		case 2:
			for (Die d : cup) {
				if (d.getFaceUp() == 2) {
					score += 2;
				}
			}
			break;
		case 3:
			for (Die d : cup) {
				if (d.getFaceUp() == 3) {
					score += 3;
				}
			}
			break;
		case 4:
			for (Die d : cup) {
				if (d.getFaceUp() == 4) {
					score += 4;
				}
			}
			break;
		case 5:
			for (Die d : cup) {
				if (d.getFaceUp() == 5) {
					score += 5;
				}
			}
			break;
		case 6:
			for (Die d : cup) {
				if (d.getFaceUp() == 6) {
					score += 6;
				}
			}
			break;
		case 7:
			for (Die d : cup) {
				score += d.getFaceUp();
			}
			int[] freq = new int[6];
			for (Die d : cup) {
				freq[d.getFaceUp()]++;
				if (freq[d.getFaceUp()] >= 3) {
					break;
				}
			}
			score = 0;
			break;
		case 8:
			for (Die d : cup) {
				score += d.getFaceUp();
			}
			int[] freq2 = new int[6];
			for (Die d : cup) {
				freq2[d.getFaceUp()]++;
				if (freq2[d.getFaceUp()] >= 4) {
					break;
				}
			}
			score = 0;
			break;
		case 9:
			break;
		case 10:
			
			break;
		case 11:
			
			break;
		case 12:
			
			break;
		case 13:
			
			break;
		}
	}
	
}
