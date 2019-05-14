package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.Boy;
import logic.Dungeon;
import logic.Monster;

//PlayPanel - Is the panel where you see the actual game in motion,
//all the big part under the stats panel 
public class PlayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int[][] arr = new Dungeon().getRoom();

	private int x = 608;
	private int y = 188;

	private BufferedImage floor;
	private BufferedImage wall;
	private BufferedImage enemySpawn;
	private BufferedImage door;

	public PlayPanel() {

		loadInformations();
		// set the size of the play panel
		this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);

		// set a random background color to diswtinguish the play panel from the rest
		this.setBackground(Color.BLACK);
	
		
		this.setDoubleBuffered(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 1) {					
					if(arr[i][j] == 1)
					g2.drawImage(wall, x, y, null);
				}

				if (arr[i][j] == 0)
					g2.drawImage(floor, x, y, null);
				
				if(arr[i][j] == 6)
				{
					g2.drawImage(enemySpawn, x, y, null);
					enemyLoc.add(x);
					enemyLoc.add(y);
					
				}
				
				if(arr[i][j] == 3)
					g2.drawImage(door, x, y, null);
				x += 64;

			}
			x = 608;
			y += 64;
		}
		x = 608;
		y = 188;
		// draw the protagonist of the game
		g2.drawImage(boy.getCurrentFrame(), boy.getCurrentX(), boy.getCurrentY(), null);
		g2.drawImage(monster.getCurrentFrame(), monster.getCurrentX(), monster.getCurrentY(), null);
	}

	public void loadInformations() {
		try {
			floor = ImageIO.read(getClass().getResource("/cobblestone.png"));
			wall = ImageIO.read(getClass().getResource("/stonebrick_carved.png"));
			enemySpawn = ImageIO.read(getClass().getResource("/cobblestone_mossy.png"));
			door = ImageIO.read(getClass().getResource("/trapdoor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// function called by the GameManager to add the boy (protagonist) to the play
	// panel at runtime
	// the PlayPanel needs a reference to the boy since he's drawn a LOT of times
	public void addBoy(Boy boy) {
		this.boy = boy;
	}

	// reference to the protagonist of the game
	private Boy boy;
	public void addMonster(Monster monster) {
		this.monster = monster;
	}
	
	public ArrayList<Integer> getEnemyLoc()
	{
		return enemyLoc;
	}
	// reference to the protagonist of the game
	private Monster monster;
	ArrayList<Integer> enemyLoc = new ArrayList<Integer>();

	
}
