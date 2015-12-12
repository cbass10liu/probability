package interestingProbabilityProblems;

public class NumbersInBoxes {

	/***
	 * The Problem: A group of 100 individuals play the following game: in a
	 * room, there are 100 boxes, and inside each box, there is a slip of paper
	 * containing the number of that box (Box 1 has a slip of paper inside that
	 * reads "1", Box 2 has a slip of paper inside that reads "2", and so on).
	 * The slips of paper are then randomly mixed so that each box contains a
	 * random number (still one slip of paper per box). The goal of the game is
	 * for each individual to find his number (individual 1 must find the slip
	 * of paper which reads "1", individual 2 must find the slip of paper which
	 * reads "2", and so on). Only one individual is allowed in the room at a
	 * time, and he is only allowed to open up to 50 boxes. After he is done
	 * (either by finding his number or by opening up 50 boxes), the room is
	 * reset exactly as it was before he went inside. The group of 100
	 * individuals wins if every single person finds his own number. How do they
	 * maximize their chances of winning?
	 */

	public static void main(String[] args) {
		int numberOfBoxes = 100;
		int[] numbers = initializeBoxes(numberOfBoxes);
		int allowedToOpen = numberOfBoxes / 2;
		int numberOfTrials = 100000;
		int numberOfCycleSuccesses = 0;
		int numberOfRandomSuccesses = 0;

		for (int i = 0; i < numberOfTrials; i++) {
			// The numbers within the boxes are shuffled.
			int[] shuffledNumbers = shuffle(numbers);

			// Searching using cycle algorithm.
			boolean cycleResult = cycleSuccess(allowedToOpen, shuffledNumbers);
			if (cycleResult == true)
				numberOfCycleSuccesses++;

			// Searching using random algorithm.
			boolean randomResult = randomSearchSuccess(allowedToOpen, shuffledNumbers);
			if (randomResult == true)
				numberOfRandomSuccesses++;

		}

		// Format results.
		System.out.println("Number of Successes by Cycle Algorithm: " + numberOfCycleSuccesses);
		System.out.println("Number of Failures by Cycle Algorithm: " + (numberOfTrials - numberOfCycleSuccesses));
		System.out.println("Cycle Algorithm Winning Percentage: "
				+ (double) (numberOfCycleSuccesses) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Successes by Random Algorithm: " + numberOfRandomSuccesses);
		System.out.println("Number of Failures by Random Algorithm: " + (numberOfTrials - numberOfRandomSuccesses));
		System.out.println("Random Algorithm Winning Percentage: "
				+ (double) (numberOfRandomSuccesses) / (double) (numberOfTrials) * 100 + "%");

	}

	/* Helper methods. */

	// Initialize boxes by assigning one unique number to each box.
	static int[] initializeBoxes(int numberOfBoxes) {
		int[] numbers = new int[numberOfBoxes];
		for (int i = 0; i < numberOfBoxes; i++) {
			numbers[i] = i + 1;
		}
		return numbers;
	}

	// Method to simulate randomizing boxes by swapping that box number with
	// another random box number.
	static int[] shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int randomIndex = (int) (Math.random() * array.length);
			swap(i, randomIndex, array);
		}
		return array;
	}

	// Helper method for randomizing boxes.
	static int[] swap(int a, int b, int[] array) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		return array;
	}

	// Simulates searching in a cycle.
	static boolean cycleSearch(int start, int searchUntil, int[] array) {
		int searched = 1;
		// Start at that individual's own box number.
		int current = array[start];
		while (searched <= searchUntil) {
			// Array index starts at 0, so we add 1.
			if (current == start + 1) {
				return true;
			} else {
				// If the individual does not find his number in the box, he
				// goes to the box number inside his current box.
				current = array[current - 1];
				searched++;
			}
		}
		return false;
	}

	// Simulates full cycle searching.
	static boolean cycleSuccess(int allowedToOpen, int[] array) {
		for (int i = 0; i < array.length; i++) {
			// If any one individual fails, the whole group fails.
			if (cycleSearch(i, allowedToOpen, array) == false) {
				return false;
			}
		}
		// If no one fails, everyone has found his own number.
		return true;
	}

	// Picks n number of random boxes.
	static int[] randomBoxes(int allowedToOpen, int[] array) {
		int[] randomBoxes = new int[allowedToOpen];
		int[] shuffledBoxes = shuffle(array);
		for (int i = 0; i < allowedToOpen; i++) {
			randomBoxes[i] = shuffledBoxes[i];
		}
		return randomBoxes;
	}

	// Simulates picking random boxes and searching in those.
	static boolean randomSearch(int start, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == start + 1) {
				return true;
			}
		}
		return false;
	}

	// Simulates full randomized search.
	static boolean randomSearchSuccess(int allowedToOpen, int[] array) {
		for (int i = 0; i < array.length; i++) {
			int[] randomBoxes = randomBoxes(allowedToOpen, array);
			if (randomSearch(i, randomBoxes) == false) {
				return false;
			}
		}
		return true;
	}

}
