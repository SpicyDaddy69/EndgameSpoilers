package gui;

import javax.swing.JFrame;

//the main game frame of the game 
public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public GameFrame(GamePanel gamePanel){
		
		//set the size of the frame
		this.setSize(WIDTH,HEIGHT);
			
		//make the frame visible
		this.setVisible(true);
		
		
		this.add(gamePanel);
		
		gamePanel.grabFocus();
		gamePanel.requestFocus();
	
	}
	
	public static final int WIDTH=1920;
	public static final int HEIGHT=1080;
}
