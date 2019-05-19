package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import javax.swing.JPanel;
import logic.Boy;
import logic.Dungeon;
import logic.Monster;

//PlayPanel - Is the panel where you see the actual game in motion,
//all the big part under the stats panel 
public class PlayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int[][] arr = new Dungeon().getRoom();

	private int x = 100;
	private int y = 100;
	private int numLevels = 0;
	private int numLives = 4;
	private int rightWall = 0;
	private int leftWall = 0;
	private int upperWall = 0;
	private int lowerWall = 0;

	private BufferedImage floor;
	private BufferedImage wall;
	private BufferedImage enemySpawn;
	private BufferedImage door;
	private BufferedImage heart;
	private BufferedImage background;
	private BufferedImage spike;
	private BufferedImage start;

	private ArrayList<Integer> doorX;
	private ArrayList<Integer> doorY;

	private ArrayList<Integer> borderX;
	private ArrayList<Integer> borderY;

	private ArrayList<Integer> spikeX;
	private ArrayList<Integer> spikeY;

	private ArrayList<Integer> heartX;
	private ArrayList<Integer> heartY;

	private GameFrame gameFrame;

	private Clip clip;

	public PlayPanel() {

		loadInformations();
		// set the size of the play panel
		this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);

		// set a random background color to diswtinguish the play panel from the rest
		this.setBackground(Color.BLACK);

		this.setDoubleBuffered(true);

		doorX = new ArrayList<Integer>();
		doorY = new ArrayList<Integer>();

		borderX = new ArrayList<Integer>();
		borderY = new ArrayList<Integer>();

		spikeX = new ArrayList<Integer>();
		spikeY = new ArrayList<Integer>();

		heartX = new ArrayList<Integer>();
		heartY = new ArrayList<Integer>();

	}

	public void setRoom(int[][] room) {
		this.arr = room;
	}

	public ArrayList<Integer> getDoorX() {
		return doorX;
	}

	public ArrayList<Integer> getDoorY() {
		return doorY;
	}

	public ArrayList<Integer> getBorderX() {
		return borderX;
	}

	public ArrayList<Integer> getBorderY() {
		return borderY;
	}

	public ArrayList<Integer> getSpikeX() {
		return spikeX;
	}

	public ArrayList<Integer> getSpikeY() {
		return spikeY;
	}

	public ArrayList<Integer> getHeartX() {
		return heartX;
	}

	public ArrayList<Integer> getHeartY() {
		return heartY;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setNumLevels(int numLevel) {
		this.numLevels = numLevel;
	}

	public void setNumLives(int numLives) {
		this.numLives = numLives;
	}

	public int getNumLevels() {
		return numLevels;
	}

	public ArrayList<Integer> getWallPositions() {
		ArrayList<Integer> walls = new ArrayList<Integer>();
		walls.add(leftWall);
		walls.add(rightWall);
		walls.add(upperWall);
		walls.add(lowerWall);

		return walls;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(2, 2);
		for (int i = 0; i < 1000; i += 32) {
			for (int j = 0; j < 1000; j += 32) {
				g2.drawImage(background, i, j, null);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0) {
					g2.drawImage(floor, x, y, null);
				}

				if (j == 4) {
					g2.drawImage(start, x, y, null);
				}
				if (arr[i][j] == 1) {
					g2.drawImage(wall, x, y, null);
					borderX.add(x);
					borderY.add(y);
				}

				if (arr[i][j] == 6) {
					g2.drawImage(enemySpawn, x, y, null);
				}

				if (arr[i][j] == 3) {
					g2.drawImage(door, x, y, null);
					doorX.add(x);
					doorY.add(y);

				}
				if (arr[i][j] == 7) {
					g2.drawImage(floor, x, y, null);
					g2.drawImage(heart, x, y, null);
					heartX.add(x);
					heartY.add(y);
					heartX.add(i);
					heartY.add(j);

				}
				if (arr[i][j] == 5) {
					g2.drawImage(floor, x, y, null);
					g2.drawImage(spike, x, y, null);
					spikeX.add(x);
					spikeY.add(y);
				}
				if (j == arr[i].length - 1) {
					rightWall = x - 32;
				}
				if (j == 0) {
					leftWall = x + 32;
				}

				x += 32;

			}
			if (i == arr.length - 1) {
				lowerWall = y - 32;
			}
			if (i == 0) {
				upperWall = y + 32;
			}
			x = 100;
			y += 32;
		}
		x = 100;
		y = 100;
		// draw the protagonist of the game
		g2.drawImage(boy.getCurrentFrame(), boy.getCurrentX(), boy.getCurrentY(), null);
		// System.owaawwaut.println(monsters);

		for (int i = 0; i < monsters.size(); i++) {
			g2.drawImage(monsters.get(i).getCurrentFrame(), monsters.get(i).getCurrentX(),
					monsters.get(i).getCurrentY(), null);
		}
		// drawBorders(g2);
		if (collision) {
			g2.drawString("Not Epic", 100, 100);
		}

		int heartLocation = 750;
		for (int i = getNumLives(); i > -1; i--) {
			g2.drawImage(heart, heartLocation, 50, null);
			heartLocation += 32;
		}

		if (getNumLives() < 0) {
			g2.setColor(Color.red);
			g2.setBackground(Color.BLACK);

			g2.drawString("You Lose", 100, 100);
			clip.stop();
			// getGameFrame().dispose();

		}
		g2.setColor(Color.DARK_GRAY);

		g2.setColor(Color.RED);
		g2.drawString(numLevels + "", 50, 50);
		g2.setColor(Color.DARK_GRAY);

	}

	private void drawBorders(Graphics g) {

		for (int i = 0; i < collisionBox.length; i++) {
			for (int j = 0; j < collisionBox[i].length; j++) {
				if (arr[i][j] == 1) {
					collisionBox[i][j] = new Rectangle(x, y, 32, 32);
				}

			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 1) {
					g.setColor(Color.DARK_GRAY);
					g.drawRect(x, y, 32, 32);
				}

				if (arr[i][j] == 3) {
					g.setColor(Color.RED);
					g.drawRect(x, y, 32, 32);
				}

				if (arr[i][j] == 0) {
					g.setColor(Color.BLUE);
					g.drawRect(x, y, 32, 32);
				}
				if (arr[i][j] == 6) {
					g.setColor(Color.GREEN);
					g.drawRect(x, y, 32, 32);
				}

				x += 32;
			}
			x = 100;
			y += 32;
		}
		x = 100;
		y = 100;
	}

	public void loadInformations() {
		try {
			floor = ImageIO.read(getClass().getResource("/textures.blocks/Floor.png"));
			wall = ImageIO.read(getClass().getResource("/textures.blocks/Wall.png"));
			enemySpawn = ImageIO.read(getClass().getResource("/textures.blocks/MossyFloor.png"));
			door = ImageIO.read(getClass().getResource("/textures.blocks/Floor.png"));
			heart = ImageIO.read(getClass().getResource("/textures.blocks/Heart.png"));
			background = ImageIO.read(getClass().getResource("/textures.sprites/PraiseStalin.png"));
			spike = ImageIO.read(getClass().getResource("/textures.blocks/Spike.png"));
			start = ImageIO.read(getClass().getResource("/textures.blocks/start.png"));
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

	public int[][] getRoom() {
		return arr;
	}

	public int getNumLives() {
		return numLives;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public Clip getClip() {
		return clip;
	}

	// reference to the protagonist of the game
	private ArrayList<Monster> monsters = new ArrayList<Monster>();

	public void addMonsters(ArrayList<Monster> monster) {
		monsters = monster;
	}

	public Rectangle bounds() {
		return new Rectangle(x, y, 64, 64);
	}

	private Rectangle[][] collisionBox = new Rectangle[arr.length][arr[0].length];

	private boolean collision = false;

}
