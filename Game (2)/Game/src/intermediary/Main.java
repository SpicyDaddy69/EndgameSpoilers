package intermediary;

import javax.sound.sampled.Clip;

import gui.GameFrame;
import gui.GamePanel;

public class Main {
	public static void run(String[] args,int currentLevel, int numLives, Clip clip) {
		// initialize the gamePanel
		GamePanel gamePanel = new GamePanel();
		

		// initialize and start the main thread of the game
		GameManager gameManager = new GameManager(gamePanel);
		gameManager.start();

		// start-up the game main frame
		GameFrame gameFrame = new GameFrame(gamePanel);
		
		gamePanel.getPlayPanel().setGameFrame(gameFrame);
		gamePanel.getPlayPanel().setNumLevels(currentLevel + 1);
		gamePanel.getPlayPanel().setNumLives(numLives - 1);
		gamePanel.getPlayPanel().setClip(clip);
		
	}
	
			
	
}
