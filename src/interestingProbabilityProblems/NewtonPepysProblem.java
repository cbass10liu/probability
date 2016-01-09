package interestingProbabilityProblems;

public class NewtonPepysProblem {

	/***
	 * Which of the following three propositions has the greatest chance of
	 * success?
	 * 
	 * A. Six fair dice are tossed independently and at least one “6” appears.
	 * B. Twelve fair dice are tossed independently and at least two “6”s
	 * appear.
	 * C. Eighteen fair dice are tossed independently and at least three “6”s
	 * appear.
	 */
	
	public static void main(String[] args) {
		
		int numberOfTrials = 1000; 
		int sixDiceSuccess = 0; 
		int twelveDiceSuccess = 0; 
		int eighteenDiceSuccess = 0; 
		
		for (int i = 0; i < numberOfTrials; i ++) {
			// Six fair dice are tossed independently and at least one “6” appears.
			if (arrayContainsNMTimes(tossNDie(6), 6, 1)) {
				sixDiceSuccess ++;
			}
			// Twelve fair dice are tossed independently and at least two “6”s appear.
			if (arrayContainsNMTimes(tossNDie(12), 6, 2)) {
				twelveDiceSuccess ++;
			}
			// Eighteen fair dice are tossed independently and at least three “6”s appear. 
			if (arrayContainsNMTimes(tossNDie(18), 6, 3)) {
				eighteenDiceSuccess ++;
			}
			
		}
		
		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of Trials where (A) succeeded: " + sixDiceSuccess);
		System.out.println("Probability of (A) succeeding: "
				+ (double) (sixDiceSuccess) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Trials where (B) succeeded: " + twelveDiceSuccess);
		System.out.println("Probability of (B) succeeding: "
				+ (double) (twelveDiceSuccess) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Trials where (C) succeeded: " + eighteenDiceSuccess);
		System.out.println("Probability of (C) succeeding: "
				+ (double) (eighteenDiceSuccess) / (double) (numberOfTrials) * 100 + "%");
		
	}
	
	static boolean arrayContainsNMTimes(int[] array, int n, int m) {
		int numberOfInstances = 0; 
		for (int i = 0; i < array.length; i ++) {
			if (array[i] == n) {
				numberOfInstances ++; 
			}
		}
		return (m <= numberOfInstances) ? true : false; 
	}
	
	static int[] tossNDie(int n) {
		int[] rollResults = new int[n];
		for (int i = 0; i < n; i ++) {
			rollResults[i] = tossDie(); 
		}
		return rollResults; 
	}
	
	static int tossDie() {
		return (int) ((Math.random() * 6) + 1);
	}

}
