import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BattleMenu extends JFrame implements ActionListener
{
	private JButton attack;
	private JButton escape;
	private JPanel menu;
	private JPanel display1;
	private JPanel display2;
	private Container contentPane;
	private JLabel image1;
	private JLabel image2;
	private Characters player, enemy;
	
	public BattleMenu(int width, int height, Characters char1, Characters char2)
	{	
		player = char1;
		enemy = char2;
		
		setTitle("Menu");
		setSize(width, height);
		
		attack = new JButton("Attack");
		attack.addActionListener(this);
		escape = new JButton("Escape");
		
		image1 = new JLabel(new ImageIcon("placeholder.png"));
		display1 = new JPanel();
		display1.add(image1);
		display1.setBackground(Color.DARK_GRAY);
		
		image2 = new JLabel(new ImageIcon("placeholder.png"));
		display2 = new JPanel();
		display2.add(image2);
		display2.setBackground(Color.DARK_GRAY);
		
		menu = new JPanel();
		menu.add(attack);
		menu.add(escape);
		menu.setBackground(Color.DARK_GRAY);
		
		contentPane = getContentPane();
		contentPane.add(menu, BorderLayout.SOUTH);
		contentPane.add(display1, BorderLayout.WEST);
		contentPane.add(display2, BorderLayout.EAST);
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == attack)
		{
			BattleMechanics test = new BattleMechanics(player);
			System.out.println(test.attack(1, enemy));
		}
		else
			System.out.println("You ran");
	}
}
