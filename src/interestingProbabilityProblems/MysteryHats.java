package interestingProbabilityProblems;

public class MysteryHats {

	/***
	 * The Problem: Three players enter a room and a red or blue hat is placed
	 * on each person’s head. The color of each hat is determined by (an
	 * independent) coin toss. No communication of any sort is allowed, except
	 * for an initial strategy session before the game begins. Once they have
	 * had a chance to look at the other hats (but not their own), the players
	 * must simultaneously guess the color of their own hats or pass. What
	 * strategy maximizes the probability that at least one person guesses
	 * correctly and no-one guesses incorrectly?
	 */

	public static void main(String[] args) {
		int numberOfTrials = 1000;
		int numberOfEducatedGuessSuccesses = 0;
		int numberOfRandomGuessSuccesses = 0;
		int numberOfParticipants = 3;

		for (int i = 0; i < numberOfTrials; i++) {
			// Setup the participants each with a hat.
			Participant[] participants = setupParticipants(numberOfParticipants);
			for (int j = 0; j < participants.length; j++) {
				// First get the participant's actual hat color.
				Hat hat = participants[j].getHat();
				// Then make an educated guess based on the other participants'
				// hat colors.
				Hat educatedGuess = participants[j].guessOwnHat(participants[j].getOtherParticipants(participants));
				// Since we have three participants, if one of them guesses
				// correctly, the other two must be wearing the same colored
				// hat, which entails that from either other of the
				// participants' perspectives, the other two hats are of the
				// same color. Therefore if one participant guesses correctly,
				// the other two participants must have skipped, so we can deem
				// one successful guess as a success for the group.
				if (educatedGuess == hat) {
					numberOfEducatedGuessSuccesses++;
				}

				// Another strategy is just for the first participant to guess
				// randomly with the other two participants skipping
				// automatically.
				if (j == 0) {
					if (participants[j].randomGuess() == hat) {
						numberOfRandomGuessSuccesses++;
					}
				}

			}
		}

		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of Correct Educated Guesses: " + numberOfEducatedGuessSuccesses);
		System.out.println("Probability of Success with Educated Guessing: "
				+ (double) (numberOfEducatedGuessSuccesses) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of Correct Random Guesses: " + numberOfRandomGuessSuccesses);
		System.out.println("Probability of Success with Random Guessing: "
				+ (double) (numberOfRandomGuessSuccesses) / (double) (numberOfTrials) * 100 + "%");

	}

	static Participant[] setupParticipants(int numberOfParticipants) {
		Participant[] participants = new Participant[numberOfParticipants];
		for (int i = 0; i < numberOfParticipants; i++) {
			participants[i] = new Participant(i);
		}
		return participants;
	}

}

enum Hat {
	BLACK, WHITE;
}

class Participant {

	// Randomly assign a hat to each participant.
	private Hat hat = Math.random() < 0.5 ? Hat.BLACK : Hat.WHITE;
	private int position;

	public Participant(int position) {
		this.position = position;
	}

	public Hat getHat() {
		return this.hat;
	}

	public void setHat(Hat hat) {
		this.hat = hat;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Hat randomGuess() {
		return Math.random() < 0.5 ? Hat.BLACK : Hat.WHITE;
	}

	// Participants are only allowed to see what the other participants' hat
	// colors are.
	public Participant[] getOtherParticipants(Participant[] participants) {
		Participant[] otherParticipants = new Participant[participants.length - 1];
		for (int i = 0; i < this.position; i++) {
			otherParticipants[i] = participants[i];
		}
		for (int j = this.position; j < otherParticipants.length; j++) {
			otherParticipants[j] = participants[j + 1];
		}
		return otherParticipants;
	}

	// If a participant sees that the other two participants have matching color
	// hats, he guesses the opposite color for his own hat. Otherwise, if the
	// other two participants have different colored hats, he skips.
	public Hat guessOwnHat(Participant[] otherParticipants) {
		if (otherParticipants[0].getHat() == otherParticipants[1].getHat()) {
			return otherParticipants[0].getHat() == Hat.BLACK ? Hat.WHITE : Hat.BLACK;
		} else {
			return null;
		}
	}

}
