package logic;

public class Dungeon {
	private int rows;
	private int cols;
	private static int[][] room;
	private int enemyCounter = 0;
	private boolean isCreated;

	public Dungeon() {
		rows = 11;
		cols = 21;
		room = new int[rows][cols];
		createRoom();

		while (!isCreated) {
			for (int[] temp : room)
				for (int temp2 : temp) {
					if (temp2 == 6) {
						enemyCounter++;
					}
				}
			if (enemyCounter == 0) {
				resetRoom();
				createRoom();
			} else
				isCreated = true;
		}

	}

	public void createRoom() {
		enemyLocationSpawn();
		chestSpawn();
		spikeSpawn();
		generateBorder();
		generateDoors();
	}

	public void resetRoom() {
		for (int rows = 0; rows < this.rows; rows++) {
			for (int cols = 0; cols < this.cols; cols++) {
				room[rows][cols] = 0;
			}
		}
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
		for (int rows = 0; rows < this.rows; rows++) {
			for (int cols = 0; cols < this.cols; cols++) {
				if (rows == this.rows / 2 && cols == this.cols - 1)
					room[rows][cols] = 3;
			}
			
		}
	}

	public void enemyLocationSpawn() {
		int numEnemies = (int) (Math.random() * 5) + 5;
		while (numEnemies > 0) {

			int xPos = (int) (Math.random() * rows);
			int yPos = (int) (Math.random() * (cols - 7)) + 5;
			System.out.println(yPos);

			if (room[xPos][yPos] != 6) {
				room[xPos][yPos] = 6;
				numEnemies--;
			}

		}
	}

	public void chestSpawn() {
		int spawnChance = (int) (Math.random() * 100);
		int xPos = (int) (Math.random() * rows);
		int yPos = (int) (Math.random() * (cols- 5)) + 5;

		if (room[xPos][yPos] != 6 && spawnChance < 5) {
			room[xPos][yPos] = 7;
		}
	}
	
	public void spikeSpawn()
	{
		int numSpikes = (int) (Math.random() * 5) + 5;
		while (numSpikes > 0) {

			int xPos = (int) (Math.random() * rows);
			int yPos = (int) (Math.random() * (cols - 7)) + 5 ;
			System.out.println(yPos);

			if (room[xPos][yPos] != 6) {
				room[xPos][yPos] = 5;
				numSpikes--;
			}

		}
	}
}