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

			if (CollisionWithMonster() || CollisionWithSpike(10)) {
				gameIsRunning = false;
				if (gamePanel.getPlayPanel().getNumLevels() > 0) {
					Main test = new Main();
					test.run(null, gamePanel.getPlayPanel().getNumLevels() - 1, gamePanel.getPlayPanel().getNumLives(),gamePanel.getPlayPanel().getClip());
				}
				gamePanel.getPlayPanel().getGameFrame().dispose();

			}

			if (CollisionWithDoor(40)) {
				gameIsRunning = false;

				Main test = new Main();
				test.run(null, gamePanel.getPlayPanel().getNumLevels(), gamePanel.getPlayPanel().getNumLives() + 1,gamePanel.getPlayPanel().getClip()
					);

				gamePanel.repaintGame();

				gamePanel.getPlayPanel().getGameFrame().dispose();

			}
			
			if(CollisionWithHeart(10))
			{
				gamePanel.getPlayPanel().setNumLives(gamePanel.getPlayPanel().getNumLives() + 1);
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
	
	private boolean CollisionWithHeart(int range)
	{
		ArrayList<Integer> heartX = gamePanel.getPlayPanel().getDoorX();
		ArrayList<Integer> heartY = gamePanel.getPlayPanel().getDoorY();
		

		for (int i = 0; i < heartX.size() - 1; i++) {
			int xDiff = Math.abs(boy.getCurrentX() - heartX.get(i));
			int yDiff = Math.abs(boy.getCurrentY() - heartY.get(i));

			if (xDiff < range && yDiff < range) {
				room[heartX.get(1)][heartY.get(1)] = 0;
				gamePanel.getPlayPanel().setRoom(room);
				return true;
			}
		}
		return false;
		
	

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

	private boolean CollisionWithRightWall() {

		ArrayList<Integer> walls = gamePanel.getPlayPanel().getWallPositions();
		if (boy.getCurrentX() == walls.get(1) - 2)
			return true;
		return false;
	}

	private boolean CollisionWithLeftWall() {
		ArrayList<Integer> walls = gamePanel.getPlayPanel().getWallPositions();
		if (boy.getCurrentX() == walls.get(0) - 2)
			return true;
		return false;
	}

	private boolean CollisionWithUpperWall() {
		ArrayList<Integer> walls = gamePanel.getPlayPanel().getWallPositions();
		if (boy.getCurrentY() == walls.get(2) - 2) {
			return true;
		}
		return false;
	}

	private boolean CollisionWithLowerWall() {
		ArrayList<Integer> walls = gamePanel.getPlayPanel().getWallPositions();
		if (boy.getCurrentY() == walls.get(3) - 2)
			return true;
		return false;
	}

	private boolean CollisionWithMonster() {
		for (int i = 0; i < monsters.size(); i++)
			if (boy.getCurrentX() == monsters.get(i).getCurrentX()
					&& boy.getCurrentY() == monsters.get(i).getCurrentY())
				return true;
		return false;
	}

	private boolean CollisionWithSpike(int range) {
		ArrayList<Integer> spikeX = gamePanel.getPlayPanel().getSpikeX();
		ArrayList<Integer> spikeY = gamePanel.getPlayPanel().getSpikeY();

		for (int i = 0; i < spikeX.size(); i++) {
			int xDiff = Math.abs(boy.getCurrentX() - spikeX.get(i));
			int yDiff = Math.abs(boy.getCurrentY() - spikeY.get(i));

			if (xDiff < range && yDiff < range) {
				return true;
			}
		}
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
		// System.out.println(!CollisionWithWall());
		if (currentKeys.contains(KeyEvent.VK_D) && !CollisionWithRightWall()) {
			boy.move(KeyEvent.VK_D);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_A) && !CollisionWithLeftWall()) {
			boy.move(KeyEvent.VK_A);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_W) && !CollisionWithUpperWall()) {
			boy.move(KeyEvent.VK_W);
			HasMoved = true;
		}
		if (currentKeys.contains(KeyEvent.VK_S) && !CollisionWithLowerWall()) {
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
