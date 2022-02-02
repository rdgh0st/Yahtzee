import java.util.*;

/**
 * 
 * @author Rob Davis
 * The Die class contains information about an individual Die including its shown number, and a method to roll them
 *
 */

public class Die {

	private int sides;
	private int faceUp;
	private Random random;
	
	/**
	 * Default Constructor, Creates 6-Sided Die
	 */
	public Die() {
		this.sides = 6;
		random = new Random();
	}
	
	/**
	 * Creates Die with custom number of sides
	 * @param Number sides for die
	 */
	public Die(int sides) {
		this.sides = sides;
	}
	
	/**
	 * Returns a random number from 1 to the number of sides on the dice
	 */
	public void Roll() {
		this.faceUp = random.nextInt(this.sides) + 1;
	}
	
	/**
	 * Returns what the die is currently showing, throws error if dice has not been rolled yet
	 * @return Current number shown by die
	 */
	public int getFaceUp() {
		if (this.faceUp == 0) {
			throw new IndexOutOfBoundsException("Dice must be rolled first!");
		}
		return this.faceUp;
	}
	
	@Override public String toString() {
		return Integer.toString(faceUp);
	}
	
}
