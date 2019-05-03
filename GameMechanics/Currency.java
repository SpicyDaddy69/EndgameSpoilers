public class Currency {
	private int gold;

	public Currency() {
		gold = 0;
	}

	public int getGold() {
		return gold;
	}

	public void addGold(int amount) {
		gold += amount;
	}
}
