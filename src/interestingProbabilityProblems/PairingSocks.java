package interestingProbabilityProblems;

public class PairingSocks {

	/***
	 * The Problem: You have n pairs of socks in your drawer ranging in shades
	 * of grey, labeled from 1 (white) to n (black). If you randomly pair up
	 * socks in your drawer, what is the probability that the socks are paired
	 * so that the colors of any pair differ by at most 1?
	 */

	public static void main(String[] args) {

		int numberOfTrials = 10000;
		// We define success as all pairs of socks differing by no more than 1
		// (or some other set threshold) in color.
		int numberOfSuccesses = 0;
		// numberOfPairsOfSocks can be set to any integer; 5 is arbitrarily. 
		int numberOfPairsOfSocks = 5;

		for (int i = 0; i < numberOfTrials; i++) {
			Drawer drawer = new Drawer(numberOfPairsOfSocks);
			if (drawer.socksAllMatchAcceptably())
				numberOfSuccesses++;
		}

		// Format results.
		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println("Number of Successes: " + numberOfSuccesses);
		System.out.println("Number of Failures: " + (numberOfTrials - numberOfSuccesses));
		System.out
				.println("Success Percentage: " + (double) (numberOfSuccesses) / (double) (numberOfTrials) * 100 + "%");
	}

}

class Sock {

	// Each sock is some shade of grey specified numerically.
	private int sockColor;

	public Sock(int sockColor) {
		this.sockColor = sockColor;
	}

	public int getSockColor() {
		return this.sockColor;
	}

	public void setSockColor(int sockColor) {
		this.sockColor = sockColor;
	}

	// By default the acceptable difference is 1.
	public boolean isAcceptablyDifferentFrom(Sock otherSock) {
		return (Math.abs(this.sockColor - otherSock.sockColor) <= 1) ? true : false;
	}

	// We can generalize by specifying the acceptable difference.
	public boolean isAcceptablyDifferentFrom(Sock otherSock, int threshold) {
		return (Math.abs(this.sockColor - otherSock.sockColor) <= threshold) ? true : false;
	}

}

class Drawer {

	// Drawers are specified by the number of pairs of socks.
	private int pairsOfSocks;
	private Sock[] socks;

	public Drawer(int pairOfSocks) {
		this.pairsOfSocks = pairOfSocks;
		this.socks = new Sock[2 * pairsOfSocks];
		this.pairUpSocks();
		this.mixUpSocks();
	}

	public int getPairsOfSocks() {
		return this.pairsOfSocks;
	}

	public void setPairsOfSocks(int pairsOfSocks) {
		this.pairsOfSocks = pairsOfSocks;
	}

	public Sock[] getSocks() {
		return this.socks;
	}

	public Sock getSock(int n) {
		return this.socks[n];
	}

	public void setSocks(Sock[] socks) {
		this.socks = socks;
	}

	public void setSock(int n, int sockColor) {
		this.socks[n].setSockColor(sockColor);
	}

	// To pair up socks we ensure that there are two individual socks of each
	// shade, that is, that there are two socks with the same numerical value.
	public void pairUpSocks() {
		for (int i = 0; i < 2 * this.pairsOfSocks; i += 2) {
			int sockColor = (i / 2) + 1;
			this.socks[i] = new Sock(sockColor);
			this.socks[i + 1] = new Sock(sockColor);
		}
	}

	// We mix up the socks so grabbing them sequentially to form
	// pairs becomes equivalent to grabbing them randomly to form pairs.
	public void mixUpSocks() {
		for (int i = 0; i < this.socks.length; i++) {
			int randomIndex = (int) (Math.random() * this.socks.length);
			this.swap(i, randomIndex);
		}
	}

	public void swap(int a, int b) {
		Sock temp = new Sock(this.socks[a].getSockColor());
		this.socks[a] = this.socks[b];
		this.socks[b] = temp;
	}

	// We check that the sock pairs differ by no more than 1 when paired up.
	public boolean socksAllMatchAcceptably() {
		for (int i = 0; i < 2 * this.pairsOfSocks; i += 2) {
			if (!this.getSock(i).isAcceptablyDifferentFrom(this.getSock(i + 1)))
				return false;
		}
		// Uncomment the next line to see the success drawer configuration for that trial.
		// System.out.println("All pairs of socks match closely: " + this.toString());
		return true;
	}

	// More generally we check that the sock pairs differ by no more than some
	// specified threshold when paired up.
	public boolean socksAllMatchAcceptably(int threshold) {
		for (int i = 0; i < 2 * this.pairsOfSocks; i += 2) {
			if (!this.getSock(i).isAcceptablyDifferentFrom(this.getSock(i + 1), threshold))
				return false;
		}
		// Uncomment the next line to see the success drawer configuration for that trial.
		// System.out.println("All pairs of socks match closely: " + this.toString());
		return true;
	}

	// Mostly used for debugging purposes to see the drawer configuration.
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 2 * this.pairsOfSocks; i++) {
			if (i % 2 == 0) {
				s += "(" + this.socks[i].getSockColor() + ", ";
			} else {
				s += this.socks[i].getSockColor() + "), ";
			}
		}
		return s.substring(0, s.length() - 2);
	}

}
