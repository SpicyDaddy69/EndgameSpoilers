package intermediary;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import logic.Boy;
import logic.KeyboardController;
import gui.GamePanel;

//the GameManager is the main thread of the game, it calls repaints 
//for the play panel and statsPanel when necessary and manages keys 
//pressed, associating them to actual actions 
public class GameManager extends Thread {
	public GameManager(GamePanel gamePanel) {

		// initialize the protagonist of the game
		this.boy = new Boy();

		// stores the gamePanel and adds the boy to it
		this.gamePanel = gamePanel;
		this.gamePanel.addBoy(boy);

		// while you're playing the game, the gameIsRunning is set to true
		this.gameIsRunning = true;
	}

	@Override
	public void run() {
		while (gameIsRunning) {
			// manage the keys currently pressed
			manageKeys();

			gamePanel.repaintGame();

			try {
				Thread.sleep(MAIN_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		} else if (currentKeys.contains(KeyEvent.VK_A)) {
			// move left
			boy.move(KeyEvent.VK_A);
		} else if (currentKeys.contains(KeyEvent.VK_W)) {
			// move left
			boy.move(KeyEvent.VK_W);
		} else if (currentKeys.contains(KeyEvent.VK_S)) {
			// move left
			boy.move(KeyEvent.VK_S);
		} else if (currentKeys.isEmpty()) {
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
	// the gameManager does al he has to do and then waits for 18ms
	// before starting once again
	private static final int MAIN_SLEEP_TIME = 18;

	// reference to the game main character
	private Boy boy;
}
