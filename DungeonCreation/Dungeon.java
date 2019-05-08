
public class Dungeon {
	private int rows;
	private int cols;
	private static int[][] room;

	public static void main(String[] args) {
		Dungeon temp = new Dungeon();

		for (int rows = 0; rows < room.length; rows++) {
			for (int cols = 0; cols < room[rows].length; cols++)
				System.out.print(room[rows][cols] + " ");
			System.out.println();
		}
	}

	public Dungeon() {
		rows = (int) (Math.random() * 10) + 5;
		cols = (int) (Math.random() * 10) + 5;
		room = new int[rows][cols];
		generateBorder();
		generateDoors();
		enemyLocationSpawn();
	}

	public Dungeon(int rows, int cols) {
		this.rows = (int) (Math.random() * (rows - 5)) + 5;
		this.cols = (int) (Math.random() * (cols - 5)) + 5;
		room = new int[this.rows][this.cols];
		generateBorder();
		generateDoors();
		enemyLocationSpawn();
	}

	public int[][] getRoom() {
		return room;
	}

	public void generateBorder() {
		for (int rows = 0; rows < this.rows; rows++) {
			for (int cols = 0; cols < this.cols; cols++) {
				if (cols == 0 || rows == 0 || cols == this.cols - 1 || rows == this.rows - 1) {
					room[rows][cols] = 1;
				}
			}
		}
	}

	public void generateDoors() {
		int numDoors = (int) (Math.random() * 3) + 1;
		for (int rows = 0; rows < this.rows; rows++) {
			for (int cols = 0; cols < this.cols; cols++) {
				if (rows == this.rows / 2 && cols == 0) {
					room[rows][cols] = 3;
				}
				if (numDoors >= 1) {
					if (rows == this.rows - 1 && cols == this.cols / 2) {
						room[rows][cols] = 3;
					}

				}
				if (numDoors >= 2) {
					if (rows == 0 && cols == this.cols / 2) {
						room[rows][cols] = 3;
					}
				}
				if (numDoors >= 3) {
					if (rows == this.rows / 2 && cols == this.cols - 1)
						room[rows][cols] = 3;
				}
			}
		}
	}

	public void enemyLocationSpawn() {
		int numEnemies = (int) (Math.random() * 3) + 1;
		while (numEnemies > 0) {
			int xPos = (int) (Math.random() * this.rows - 3) + 3;
			int yPos = (int) (Math.random() * this.cols - 3) + 3;
			if (room[xPos][yPos] != 6) {
				room[xPos][yPos] = 6;
				numEnemies--;
			}

		}
	}
}
