package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.Boy;
import logic.KeyboardController;
import logic.Monster;

//the game panel on which we will draw the true panels of the game
//it serves just as an interlayer between the frame and the mosaic 
//of panels the player will see, it comunicates with the statsPanel
//and the playPanel throwing them informations coming from the logic
//side of the game
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GamePanel() {
		this.setRequestFocusEnabled(true);
		this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		this.add(playPanel);
		playPanel.setLocation(0, 50);

		keyboardController = new KeyboardController();
		this.addKeyListener(keyboardController);

	}

	public void addMonsters(ArrayList<Monster> monster) {
		playPanel.addMonsters(monster);
	}

	public void addBoy(Boy boy) {
		this.boy = boy;
		playPanel.addBoy(boy);
	}

	public void repaintGame() {
		playPanel.removeAll();
		playPanel.updateUI();
		playPanel.revalidate();
		playPanel.repaint();
	}

	public PlayPanel getPlayPanel() {
		return playPanel;
	}

	private KeyboardController keyboardController;
	private PlayPanel playPanel = new PlayPanel();
	@SuppressWarnings("unused")
	private Boy boy;
	private Monster monster;
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
}
