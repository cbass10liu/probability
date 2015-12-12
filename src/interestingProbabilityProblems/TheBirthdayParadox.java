package interestingProbabilityProblems;

import java.util.Arrays;

public class TheBirthdayParadox {

	/***
	 * The Problem: Given a set of N individuals, there is some probability that
	 * a pair of individuals will have the same birthday. What is the minimum
	 * number of individuals such that the probability that a pair of them have
	 * the same birthday is greater than 50%?
	 */

	public static void main(String[] args) throws Exception {
		int numberOfTrials = 100000;
		int twentyTwoParticipants = 22;
		int numberOfSameBirthdaysWithTwentyTwoParticipants = 0;
		int twentyThreeParticipants = 23;
		int numberOfSameBirthdaysWithTwentyThreeParticipants = 0;

		for (int i = 0; i < numberOfTrials; i++) {
			if (hasBirthdayPair(twentyTwoParticipants))
				numberOfSameBirthdaysWithTwentyTwoParticipants++;
			if (hasBirthdayPair(twentyThreeParticipants))
				numberOfSameBirthdaysWithTwentyThreeParticipants++;
		}

		// Format results.
		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of Trials with a Shared Birthday (22 Participants): "
				+ numberOfSameBirthdaysWithTwentyTwoParticipants);
		System.out.println("Probability there is a Shared Birthday (22 Participants): "
				+ (double) (numberOfSameBirthdaysWithTwentyTwoParticipants) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Trials with a Shared Birthday (23 Participants): "
				+ numberOfSameBirthdaysWithTwentyThreeParticipants);
		System.out.println("Probability there is a Shared Birthday (23 Participants): "
				+ (double) (numberOfSameBirthdaysWithTwentyThreeParticipants) / (double) (numberOfTrials) * 100 + "%");

	}

	/* Helper methods. */

	static boolean hasBirthdayPair(int numberOfParticipants) {
		// Initialize the participants' birthdays randomly.
		int[] participantBirthdays = initializeBirthdays(numberOfParticipants);
		// Sorting the birthdays will allow for faster comparison matches.
		Arrays.sort(participantBirthdays);
		// Since the birthdays are sorted, each participant will only need
		// to check the participant to his or her immediate right. The last
		// participant obviously does not need to check anyone.
		for (int j = 0; j < numberOfParticipants - 1; j++) {
			if (participantBirthdays[j] == participantBirthdays[j + 1]) {
				// We only care that there is at least one match in a group
				// so we can stop checking for matches once we find the
				// first one.
				return true;
			}
		}
		return false;
	}

	// Return a random number between a range, inclusive.
	static int getRandomValueInRange(int a, int b) {
		return (int) (Math.random() * (b - a)) + a + 1;
	}

	// Initialize birthdays. Each day of the year can be represented by a number
	// between 1 and 365, inclusive. Leap years are intentionally excluded but
	// this simplification will have a minimal effect on the result.
	static int[] initializeBirthdays(int numberOfParticipants) {
		int[] participantBirthdays = new int[numberOfParticipants];
		for (int i = 0; i < numberOfParticipants; i++) {
			participantBirthdays[i] = getRandomValueInRange(1, 365);
		}
		return participantBirthdays;
	}

}
