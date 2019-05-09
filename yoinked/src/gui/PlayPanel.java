package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import logic.Boy;

//PlayPanel - Is the panel where you see the actual game in motion,
//all the big part under the stats panel 
public class PlayPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlayPanel() {

		// set the size of the play panel
		this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);

		// set a random background color to distinguish the play panel from the rest
		this.setBackground(Color.DARK_GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		

		// draw the protagonist of the game
		g2.drawImage(boy.getCurrentFrame(), boy.getCurrentX(), boy.getCurrentY(), null);
	}

	// function called by the GameManager to add the boy (protagonist) to the play
	// panel at runtime
	// the PlayPanel needs a reference to the boy since he's drawn a LOT of times
	public void addBoy(Boy boy) {
		this.boy = boy;
	}
	// reference to the protagonist of the game
	private Boy boy;
}
