package interestingProbabilityProblems;

public class BertrandsBoxParadox {

	/***
	 * The Problem: Given three boxes (1) a box with two gold coins (2) a box
	 * with two silver coins (3) a box with one gold coin and one silver coin,
	 * suppose a box is chosen at random and a coin is chosen at random from
	 * that box. Given that the selected coin is gold, what is the probability
	 * that the other coin in the box is also gold?
	 */

	public static void main(String[] args) {
		int numberOfTrials = 10000;
		// We only count a trial if the first coin chosen is gold.
		int chosenCoinIsGold = 0;
		int goldBox = 0;
		int mixedBox = 0;
		Box[] boxes = setup();

		while (chosenCoinIsGold < numberOfTrials) {
			// First choose a box.
			Box box = chooseBox(boxes);
			// Then choose a coin in that box.
			Coin coin = box.chooseRandomCoin();
			// We are only concerned with trials where the chosen coin is gold.
			if (coin == Coin.GOLD) {
				chosenCoinIsGold++;
				// Give that the chosen coin is gold, we are successful only if
				// the other coin is also gold.
				if (box.isGoldBox(box)) {
					goldBox++;
				} else {
					mixedBox++;
				}
			}
		}

		System.out.println("Number of Trials: " + numberOfTrials);
		System.out.println(
				"Number of Trials where the Gold Box is selected given that the selected coin is gold: " + goldBox);
		System.out.println("Probability of selecting the Gold Box given that the selected coin is gold: "
				+ (double) (goldBox) / (double) (numberOfTrials) * 100 + "%");
		System.out.println(
				"Number of Trials where the Mixed Box is selected given that the selected coin is gold: " + mixedBox);
		System.out.println("Probability of selecting the Mixed Box given that the selected coin is gold: "
				+ (double) (mixedBox) / (double) (numberOfTrials) * 100 + "%");

	}

	/* Helper methods. */

	public static Box[] setup() {
		Box[] boxSetup = new Box[3];
		boxSetup[0] = new Box(Coin.GOLD, Coin.GOLD);
		boxSetup[1] = new Box(Coin.SILVER, Coin.SILVER);
		boxSetup[2] = new Box(Coin.GOLD, Coin.SILVER);
		return boxSetup;
	}

	public static Box chooseBox(Box[] boxes) {
		int numberOfBoxes = boxes.length;
		return boxes[(int) (Math.random() * numberOfBoxes)];
	}

}

// A coin can either be gold or silver.
enum Coin {
	GOLD, SILVER;
}

// Box class.
class Box {

	private Coin firstCoin;
	private Coin secondCoin;

	public Box(Coin firstCoin, Coin secondCoin) {
		this.firstCoin = firstCoin;
		this.secondCoin = secondCoin;
	}

	public Coin getFirstCoin() {
		return firstCoin;
	}

	public Coin getSecondCoin() {
		return secondCoin;
	}

	public void setFirstCoin(Coin coin) {
		this.firstCoin = coin;
	}

	public void setSecondCoin(Coin coin) {
		this.secondCoin = coin;
	}

	public Coin chooseRandomCoin() {
		return Math.random() < 0.5 ? this.getFirstCoin() : this.getSecondCoin();
	}

	public boolean isGoldBox(Box box) {
		return (box.getFirstCoin() == Coin.GOLD && box.getSecondCoin() == Coin.GOLD) ? true : false; 
	}

	public boolean isSilverBox(Box box) {
		return (box.getFirstCoin() == Coin.SILVER && box.getSecondCoin() == Coin.SILVER) ? true: false; 
	}

	public boolean isMixedBox(Box box) {
		return  (!(isGoldBox(box) || isSilverBox(box))) ? true : false; 
	}

}
