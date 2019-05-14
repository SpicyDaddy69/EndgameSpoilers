package intermediary;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import logic.Boy;
import logic.KeyboardController;
import logic.Monster;
import gui.GamePanel;

//the GameManager is the main thread of the game, it calls repaints 
//for the play panel and statsPanel when necessary and manages keys 
//pressed, associating them to actual actions 
public class GameManager extends Thread {
	public GameManager(GamePanel gamePanel) {
		int x = 608;
		int y = 188;
		// initialize the protagonist of the game
		this.boy = new Boy();
		// stores the gamePanel and adds the boy to it
		this.gamePanel = gamePanel;
		this.gamePanel.addBoy(boy);

		// while you're playing the game, the gameIsRunning is set to true
		this.gameIsRunning = true;
		
		this.enemyLoc = new ArrayList<Integer>();
		this.room = gamePanel.getPlayPanel().getRoom();
		
		for(int rows = 0; rows < this.room.length; rows++)
		{
			for(int cols = 0; cols <  this.room[rows].length; cols++)
			{
				if(this.room[rows][cols] == 6)
				{
					this.enemyLoc.add(x);
					this.enemyLoc.add(y);
				}
				x += 64;
			}
			x = 608;
			y += 64;
		}
		x = 608;
		y = 188;
		
		this.monsters = new ArrayList<Monster>();
		
		for(int i = 0; i < enemyLoc.size() / 2; i++)
		{
			this.monster = new Monster(enemyLoc.get(i * 2), enemyLoc.get((i * 2) + 1));
			this.monsters.add(this.monster);

		}
		System.out.println(monsters);
		this.gamePanel.addMonsters(monsters);
	}

	@Override
	public void run() {
		while (gameIsRunning) {
			// manage the keys currently pressed
			manageKeys();
			moveMonster(100);

			gamePanel.repaintGame();
			Toolkit.getDefaultToolkit().sync();
			try {
				Thread.sleep(MAIN_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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

	// the function manages the keys currently pressed associating concrete
	// actions to them
	private void manageKeys() {
		// get the currently pressed keys from the KeyboardController
		HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();

		// manage the two possible run direction
		if (currentKeys.contains(KeyEvent.VK_D)) {
			// move right
			
				boy.move(KeyEvent.VK_D);
		}if (currentKeys.contains(KeyEvent.VK_A)) {
			// move left
			
				boy.move(KeyEvent.VK_A);
		}if (currentKeys.contains(KeyEvent.VK_W)) {
			// move left
			
				boy.move(KeyEvent.VK_W);
		}if (currentKeys.contains(KeyEvent.VK_S)) {
			// move left
			
					boy.move(KeyEvent.VK_S);
		}if (currentKeys.isEmpty()) {
			// if the player is not pressing keys, the protagonist stands still
			boy.stop();
		}

	}

	public Boy getBoy() {
		return boy;
	}


	// variable set to 'true' if the game is running, 'false' otherwise
	private boolean gameIsRunning;

	// reference to the gamePanel
	private GamePanel gamePanel;

	// main sleep time of the GameManager thread - in this case
	// the gameManager does all he has to do and then waits for 18ms
	// before starting once again
	private static final int MAIN_SLEEP_TIME = 18;

	// reference to the game main character
	private Boy boy;
	private Monster monster;
	private ArrayList<Monster> monsters;
	private ArrayList<Integer> enemyLoc;
	private int[][] room;

	private int minXValue = 672;
	private int maxXValue = 1208;
	private int minYValue = 252;
	private int maxYValue = 788;
}
