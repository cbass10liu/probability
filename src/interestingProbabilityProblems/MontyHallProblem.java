package interestingProbabilityProblems;

public class MontyHallProblem {

	/***
	 * The Problem: You are on a game show, and in front of you there are three
	 * doors: behind one door there is a car, and behind the other two there are
	 * goats. Before revealing what is behind the door that you selected, the
	 * game show host reveals that behind another door, there is a goat. The
	 * host then asks you if you would like to switch your selected door. Should
	 * you switch? Does switching increase your chances of winning the car?
	 */

	public static void main(String[] args) {

		int numberOfTrials = 10000;
		int notSwitchingSuccess = 0;
		int switchingSuccess = 0;

		for (int i = 0; i < numberOfTrials; i++) {
			// Set up the doors.
			Door[] doors = setup(3);
			// Find the winning door.
			int winningDoorNumber = winningDoor(doors);
			// Randomly select one door.
			int selectedDoorNumber = (int) (Math.random() * doors.length);
			// Open all except for two doors.
			Door[] unopenedDoors = remainingUnopnedDoors(doors, selectedDoorNumber, winningDoorNumber);
			// If the selected door is the winning door then not switching would
			// be beneficial. Otherwise, switching would be beneficial.
			if (unopenedDoors[0].getPrize() == doors[selectedDoorNumber].getPrize()) {
				notSwitchingSuccess++;
			} else {
				switchingSuccess++;
			}
		}

		// Format results.
		System.out.println("Number of trials: " + numberOfTrials);
		System.out.println("Number of times switching doors wins the prize: " + switchingSuccess);
		System.out.println("Percent of time switching doors wins the prize: "
				+ (double) (switchingSuccess) / (double) (numberOfTrials) * 100 + "%");
		System.out.println("Number of times not switching doors wins the prize: " + notSwitchingSuccess);
		System.out.println("Percent of times not switching doors wins the prize: "
				+ (double) (notSwitchingSuccess) / (double) (numberOfTrials) * 100 + "%");

	}

	static Door[] setup(int numberOfDoors) {
		Door[] doors = new Door[numberOfDoors];
		// All doors except one have a losing prize.
		for (int i = 0; i < numberOfDoors; i++) {
			doors[i] = new Door(Prize.GOAT);
		}
		// Set one random door to have a winning prize.
		int winningDoorNumber = (int) (Math.random() * doors.length);
		doors[winningDoorNumber].setPrize(Prize.CAR);

		return doors;
	}

	static Door[] remainingUnopnedDoors(Door[] originalDoors, int selectedDoor, int winningDoor) {

		// Open all doors except for two of them.
		Door[] remainingDoorNumbers = new Door[2];

		// The first unopened door is the door with the winning prize.
		int winningDoorNumber = winningDoor(originalDoors);
		remainingDoorNumbers[0] = originalDoors[winningDoorNumber];

		// The second unopened door is a random door without the winning prize.
		int randomRemainingDoorNumber = (int) (Math.random() * originalDoors.length);
		while (randomRemainingDoorNumber == winningDoorNumber) {
			randomRemainingDoorNumber = (int) (Math.random() * originalDoors.length);
		}
		remainingDoorNumbers[1] = originalDoors[randomRemainingDoorNumber];

		return remainingDoorNumbers;
	}

	// Find the winning door number.
	static int winningDoor(Door[] doors) {
		for (int i = 0; i < doors.length; i++) {
			if (doors[i].getPrize() == Prize.CAR) {
				return i;
			}
		}
		return -1;
	}

}

enum Prize {
	GOAT, CAR;
}

class Door {

	private Prize prize;

	public Door(Prize prize) {
		this.prize = prize;
	}

	public Prize getPrize() {
		return this.prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

}