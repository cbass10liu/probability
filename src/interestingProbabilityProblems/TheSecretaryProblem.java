package interestingProbabilityProblems;

public class TheSecretaryProblem {

	/***
	 * The Problem: Suppose there are N rankable individuals who are seeking to
	 * be hired by you for a secretary position. (By rankable, we mean that each
	 * candidate has a value given to him or her based on his or her skill, and
	 * these values form a totally ordered set.) The candidates are interviewed
	 * in a random order. You know how each candidate has done with respect to
	 * the other candidates you have already interviewed, but you are unaware
	 * how he or she fares against the candidates you have not interviewed yet.
	 * After each interview, you must immediately make a decision to reject or
	 * hire the candidate. Once a candidate is rejected, you cannot go back to
	 * hire him or her. Once a candidate is hired, the interview process stops.
	 * How do you maximize your chances of hiring the best candidate?
	 */

	public static void main(String[] args) {
		int numberOfTrials = 100000;
		int numberOfCandidates = 100;
		int numberOfBestSelections = 0;

		for (int i = 0; i < numberOfTrials; i++) {
			// Set up and randomize candidate interviewing order. The number
			// associated with the candidate is his or her value.
			int[] candidates = setup(numberOfCandidates);
			// Skip the first N/e candidates and pick the candidate who has a
			// higher value than the highest from the N/e set.
			int chosenSecretary = pickSecretary(candidates);
			// We have picked the best candidate if his or her value is the
			// highest of the set of candidates. 
			if (chosenSecretary == numberOfCandidates)
				numberOfBestSelections++;
		}

		// Format results.
		System.out.println("Number of candidates: " + numberOfCandidates);
		System.out.println("Number of trials: " + numberOfTrials);
		System.out.println("Number of times best candidate was selected: " + numberOfBestSelections);
		System.out.println("Percentage of time selecting best candidate: "
				+ (double) (numberOfBestSelections) / (double) (numberOfTrials) * 100 + "%");

	}

	/* Helper methods. */

	// Setup for pickSecretary method.
	public static int[] setup(int n) {
		int[] initializedArray = new int[n];
		for (int i = 0; i < n; i++) {
			initializedArray[i] = i + 1;
		}
		return randomize(initializedArray);
	}

	// Randomize values.
	public static int[] randomize(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int randomIndex = (int) (Math.random() * array.length);
			swap(i, randomIndex, array);
		}
		return array;
	}

	// Helper method for randomizing.
	public static int[] swap(int a, int b, int[] array) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		return array;
	}

	public static int pickSecretary(int[] array) {
		// Automatically skip the first N/e candidates.
		int numberOfAutomaticSkips = (int) (array.length / Math.E);
		// Keep track of the best candidates in the first N/e candidates.
		int currentBest = 0;
		for (int i = 0; i < numberOfAutomaticSkips; i++) {
			if (array[i] > currentBest) {
				currentBest = array[i];
			}
		}
		// Pick the very next best candidate after the N/e candidates.
		for (int j = numberOfAutomaticSkips; j < array.length; j++) {
			if (array[j] > currentBest) {
				return array[j];
			}
		}
		// If all of them are worse, we must hire the last candidate.
		return array[array.length - 1];
	}

}
