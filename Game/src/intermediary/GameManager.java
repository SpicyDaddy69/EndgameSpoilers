package intermediary;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import logic.Boy;
import logic.KeyboardController;
import logic.Monster;
import gui.GamePanel;

public class GameManager extends Thread {
	private boolean HasMoved = false;
	private boolean gameIsRunning;
	private GamePanel gamePanel;
	private static final int MAIN_SLEEP_TIME = 18;
	private Boy boy;
	private Monster monster;
	private ArrayList<Monster> monsters;
	private ArrayList<Integer> enemyLoc;
	private int[][] room;
	boolean close = false;

	public GameManager(GamePanel gamePanel) {
		Graphics g;
		int x = 100;
		int y = 100;

		this.boy = new Boy();

		this.gamePanel = gamePanel;
		this.gamePanel.addBoy(boy);

		this.gameIsRunning = true;

		this.enemyLoc = new ArrayList<Integer>();
		this.room = gamePanel.getPlayPanel().getRoom();

		for (int rows = 0; rows < this.room.length; rows++) {
			for (int cols = 0; cols < this.room[rows].length; cols++) {
				if (this.room[rows][cols] == 6) {
					this.enemyLoc.add(x);
					this.enemyLoc.add(y);
				}

				x += 32;
			}
			x = 100;
			y += 32;
		}
		x = 100;
		y = 100;

		this.monsters = new ArrayList<Monster>();

		for (int i = 0; i < enemyLoc.size() / 2; i++) {
			this.monster = new Monster(enemyLoc.get(i * 2), enemyLoc.get((i * 2) + 1));
			this.monsters.add(this.monster);

		}
		System.out.println(monsters);
		this.gamePanel.addMonsters(monsters);
	}

	@Override
	public void run() {
		while (gameIsRunning) {
			manageKeys();
			if (HasMoved)
				moveMonster(100);

			if (CollisionWithMonster()) {
				gamePanel.getPlayPanel().youLose(true);
			}

			if (CollisionWithWall(0)) {
				boy.stop();
			}

			if (CollisionWithDoor(10)) {
				gameIsRunning = false;

				Main test = new Main();
				test.main(null, gamePanel.getPlayPanel().getNumLevels());

				gamePanel.repaintGame();

				gamePanel.getPlayPanel().getGameFrame().dispose();

			}

			gamePanel.repaintGame();

			Toolkit.getDefaultToolkit().sync();
			try {
				Thread.sleep(MAIN_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean CollisionWithDoor(int range) {
		close = false;
		ArrayList<Integer> doorX = gamePanel.getPlayPanel().getDoorX();
		ArrayList<Integer> doorY = gamePanel.getPlayPanel().getDoorY();

		for (int i = 0; i < doorX.size(); i++) {
			int xDiff = Math.abs(boy.getCurrentX() - doorX.get(i));
			int yDiff = Math.abs(boy.getCurrentY() - doorY.get(i));

			if (xDiff < range && yDiff < range) {
				close = true;
			}

			doorX.remove(i);
			doorY.remove(i);

		}

		return close;
	}

	private boolean CollisionWithWall(int range) {
		close = false;
		ArrayList<Integer> borderX = gamePanel.getPlayPanel().getDoorX();
		ArrayList<Integer> borderY = gamePanel.getPlayPanel().getDoorY();

		for (int i = 0; i < borderX.size(); i++) {
			int xDiff = Math.abs(boy.getCurrentX() - borderX.get(i));
			int yDiff = Math.abs(boy.getCurrentY() - borderY.get(i));

			if (xDiff < range && yDiff < range) {
				close = true;
			}

		}

		return close;
	}
	
	private boolean CollisionWithMonster() {
		for (int i = 0; i < monsters.size(); i++)
			if (boy.getCurrentX() == monsters.get(i).getCurrentX()
					&& boy.getCurrentY() == monsters.get(i).getCurrentY())
				return true;
		return false;
	}

	private void moveMonster(int range) {
		for (int i = 0; i < monsters.size(); i++) {
			int xDiff = monsters.get(i).getCurrentX() - boy.getCurrentX();
			int yDiff = monsters.get(i).getCurrentY() - boy.getCurrentY();

			if (xDiff < range && xDiff > 0 && yDiff < range && yDiff > -range) {
				monsters.get(i).move(KeyEvent.VK_A);
				monsters.get(i).stop();

			}
			if (xDiff < 0 && xDiff > -range && yDiff < range && yDiff > -range) {
				monsters.get(i).move(KeyEvent.VK_D);
				monsters.get(i).stop();
			}
			if (yDiff < range && yDiff > 0 && xDiff < range && xDiff > -range) {
				monsters.get(i).move(KeyEvent.VK_W);
				monsters.get(i).stop();
			}
			if (yDiff < 0 && yDiff > -range && xDiff < range && xDiff > -range) {
				monsters.get(i).move(KeyEvent.VK_S);
				monsters.get(i).stop();
			}
		}

	}

	private void manageKeys() {
		HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
		if (currentKeys.contains(KeyEvent.VK_D)) {
			boy.move(KeyEvent.VK_D);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_A)) {
			boy.move(KeyEvent.VK_A);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_W)) {
			boy.move(KeyEvent.VK_W);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_S)) {
			boy.move(KeyEvent.VK_S);
			HasMoved = true;
		}
		if (currentKeys.isEmpty()) {
			boy.stop();
		}

	}


	public Boy getBoy() {
		return boy;
	}
	
}
