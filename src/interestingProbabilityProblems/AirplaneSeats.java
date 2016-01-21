package interestingProbabilityProblems;

public class AirplaneSeats {

	/***
	 * The Problem: One hundred people line up to board an airplane. Each has a
	 * boarding pass with assigned seat. However, the first person to board has
	 * lost his boarding pass and takes a random seat. After that, each person
	 * takes the assigned seat if it is unoccupied, and one of unoccupied seats
	 * at random otherwise. What is the probability that the last person to
	 * board gets to sit in his assigned seat?
	 */

	public static void main(String[] args) {

		int numberOfTrials = 10000;
		int numberOfSuccesses = 0;

		for (int i = 0; i < numberOfTrials; i++) {
			int numberOfPassengers = 100;
			int[] seats = setupSeats(numberOfPassengers);

			// The first passenger takes a random seat.
			int firstPassengerRandomSeat = chooseRandomSeat(seats);
			seats = takeSeat(firstPassengerRandomSeat, seats);

			// We begin with the second passenger.
			int passengerNumber = 2;
			while (passengerNumber < numberOfPassengers) {
				// If the passenger's seat is open, take that seat, otherwise,
				// take a random seat.
				seats = seatIsOpen(passengerNumber, seats) ? takeSeat(passengerNumber, seats)
						: takeSeat(chooseRandomSeat(seats), seats);
				passengerNumber++;
			}

			// If the last seat is the seat for the last passenger, the group is successful.
			if (seatIsOpen(passengerNumber++, seats)) {
				numberOfSuccesses++;
			}

		}

		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of times the last passenger found his seat: " + numberOfSuccesses);
		System.out.println("Probability of Success with Educated Guessing: "
				+ (double) (numberOfSuccesses) / (double) (numberOfTrials) * 100 + "%");

	}

	static int chooseRandomSeat(int[] openSeats) {
		int randomSeatIndex = (int) (Math.random() * openSeats.length);
		return openSeats[randomSeatIndex];
	}

	// Check to see if a seat is open.
	static boolean seatIsOpen(int desiredSeat, int[] openSeats) {
		for (int i = 0; i < openSeats.length; i++) {
			if (openSeats[i] == desiredSeat) {
				return true;
			}
		}
		return false;
	}

	// Occupy a seat and return the remaining open seats.
	static int[] takeSeat(int seatNumber, int[] openSeats) {
		int[] remainingSeats = new int[openSeats.length - 1];
		int seatNumberIndex = findSeatIndex(seatNumber, openSeats);
		for (int i = 0; i < seatNumberIndex; i++) {
			remainingSeats[i] = openSeats[i];
		}
		for (int j = seatNumberIndex; j < remainingSeats.length; j++) {
			remainingSeats[j] = openSeats[j + 1];
		}
		return remainingSeats;
	}

	// Find the seat index in the remaining open seats.
	static int findSeatIndex(int seatNumber, int[] seats) {
		int seatNumberIndex = -1;
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == seatNumber) {
				seatNumberIndex = i;
			}
		}
		return seatNumberIndex;
	}

	static int[] setupSeats(int numberOfPassengers) {
		int[] seats = new int[numberOfPassengers];
		for (int i = 0; i < numberOfPassengers; i++) {
			seats[i] = i + 1;
		}
		return seats;
	}

}
