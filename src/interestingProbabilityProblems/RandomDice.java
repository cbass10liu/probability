package interestingProbabilityProblems;

public class RandomDice {

	/***
	 * The Problem: A group of 100 individuals are playing a game. Each
	 * individual simultaneously rolls a regular six-sided die. The participants
	 * are sitting in a circle in such a way so that each of them cannot see
	 * their own die roll, but can see the rolls of all ninety-nine of the other
	 * individuals. After all the dice rolls, each of the hundred individual
	 * writes down a number from one through six. The participants may choose
	 * their number based on the dice rolls of everyone else, but are not
	 * allowed to communicate in any way or to see what the others are writing.
	 * 
	 * The hundred individual are then simultaneously examined. The group wins
	 * if every individual correctly guessed the number of his or her own die;
	 * if even one individual writes down a number that does not match his or
	 * her die roll, the group loses. What strategy maximizes the group's
	 * chances of winning?
	 */

	public static void main(String[] args) {

		int numberOfTrials = 10000;
		int numberOfEducatedGuessSuccess = 0;
		int numberOfRandomGuessSuccess = 0; 
		int numberOfDie = 100;
		int numberOfSidesOnDie = 6;

		for (int i = 0; i < numberOfTrials; i++) {

			// The complete result of rolling the dice.
			int rolledResults[] = tossNDie(numberOfDie, numberOfSidesOnDie);
			// Have each person guess randomly what his or her roll is. 
			int randomGuessing[] = tossNDie(numberOfDie, numberOfSidesOnDie);
			// If all of them are correct, they are successful. 
			if (sameRolls(rolledResults, randomGuessing)) {
				numberOfRandomGuessSuccess++;
			}
			
			// Calculate the expected sum of all the dice.
			float expectedSum = expectedSum(6, numberOfDie);
			int numberOfIndividualSuccesses = 0;

			for (int j = 0; j < numberOfDie; j++) {
				int educatedGuess = 0;
				// Get the actual value of the individual's roll.
				int actualRoll = rolledResults[j];
				// Add up the other rolls.
				int sumOfOtherRolls = sumOfOtherRolls(j, rolledResults);

				// If the difference between the expected sum and the sum of the
				// other rolls is greater than or equal to 6, our best bet is to
				// guess 6.
				if (6 <= ((int) expectedSum - sumOfOtherRolls)) {
					educatedGuess = 6;
				}
				// If the difference between the expected sum and the sum of the
				// other rolls is less than or equal to 1, our best bet is to
				// guess 1.
				else if (((int) expectedSum - sumOfOtherRolls) <= 1) {
					educatedGuess = 1;
				}
				// Otherwise, guess the difference between the expected sum and
				// the sum of the other rolls.
				else {
					educatedGuess = (int) (expectedSum - sumOfOtherRolls);
				}

				if (educatedGuess != actualRoll) {
					// If even one individual fails, the group fails, so there
					// is no need to continue.
					break;
				} else {
					numberOfIndividualSuccesses++;
				}

			}

			// The group is successful iff every individual is successful.
			if (numberOfIndividualSuccesses == 100) {
				numberOfEducatedGuessSuccess++;
			}

		}

		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of Correct Educated Guesses: " + numberOfEducatedGuessSuccess);
		System.out.println("Probability of Success with Educated Guessing: "
				+ (double) (numberOfEducatedGuessSuccess) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Correct Random Guesses: " + numberOfRandomGuessSuccess);
		System.out.println("Probability of Success with Random Guessing: "
				+ (double) (numberOfRandomGuessSuccess) / (double) (numberOfTrials) * 100 + "%");

	}

	static int sumOfOtherRolls(int n, int[] rolledResults) {
		int sum = 0;
		for (int i = 0; i < rolledResults.length; i++) {
			sum += rolledResults[i];
		}
		return sum - rolledResults[n];
	}

	static float expectedSum(int nSidedDie, int numberOfDie) {
		return expectedRollValue(nSidedDie) * numberOfDie;
	}

	static float expectedRollValue(int nSidedDie) {
		float sum = 0;
		for (int i = 0; i < nSidedDie; i++) {
			sum += (i + 1);
		}
		return sum / nSidedDie;
	}

	// Results after tossing n die where each die has m sides.
	static int[] tossNDie(int numberOfDie, int numberOfSides) {
		int[] rolledResults = new int[numberOfDie];
		for (int i = 0; i < numberOfDie; i++) {
			rolledResults[i] = tossDie(numberOfSides);
		}
		return rolledResults;
	}

	static int tossDie(int numberOfSides) {
		return (int) ((Math.random() * numberOfSides) + 1);
	}
	
	static boolean sameRolls(int[] firstSet, int[] secondSet) {
		if (firstSet.length != secondSet.length) {
			return false; 
		} else {
			for (int i = 0; i < firstSet.length; i ++) {
				if (firstSet[i] != secondSet[i]) {
					return false; 
				}
			}
			return true; 
		}
	}

}
