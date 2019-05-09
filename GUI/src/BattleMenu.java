import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BattleMenu extends JDialog implements ActionListener
{
	private JButton attack;
	private JButton escape;
	private JPanel menu;
	private JPanel display1;
	private JPanel display2;
	private JPanel display3;
	private Container contentPane;
	private JLabel image1;
	private JLabel image2;
	private JLabel image3;
	private Characters player, enemy;
	private JLabel health1;
	private JLabel health2;
	private JTextArea events;
	private JScrollPane scroll;
	
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
		health1 = new JLabel("Health: " + player.getHealth());
		health1.setForeground(Color.GREEN);
		display1 = new JPanel(new BorderLayout());
		display1.add(image1, BorderLayout.NORTH);
		display1.add(health1, BorderLayout.CENTER);
		display1.setBackground(Color.DARK_GRAY);
		
		image2 = new JLabel(new ImageIcon("placeholder.png"));
		health2 = new JLabel("Health: " + enemy.getHealth());
		health2.setForeground(Color.GREEN);
		display2 = new JPanel(new BorderLayout());
		display2.add(image2, BorderLayout.NORTH);
		display2.add(health2, BorderLayout.CENTER);
		display2.setBackground(Color.DARK_GRAY);
		
		events = new JTextArea(5, 20);
		events.setEditable(false);
		events.setFont(new Font("Serif", Font.PLAIN, 20));
		
		scroll = new JScrollPane(events, scroll.VERTICAL_SCROLLBAR_AS_NEEDED, scroll.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(400, 110));
		
		image3 = new JLabel(new ImageIcon("placeholder.png"));
		display3 = new JPanel(new BorderLayout());
		display3.add(image3, BorderLayout.NORTH);
		display3.add(scroll, BorderLayout.SOUTH);
		display3.setBackground(Color.DARK_GRAY);
		
		menu = new JPanel();
		menu.add(attack);
		menu.add(escape);
		menu.setBackground(Color.DARK_GRAY);
		
		contentPane = getContentPane();
		contentPane.add(menu, BorderLayout.SOUTH);
		contentPane.add(display1, BorderLayout.WEST);
		contentPane.add(display2, BorderLayout.EAST);
		contentPane.add(display3, BorderLayout.CENTER);
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == attack)
		{
			BattleMechanics playerAttack = new BattleMechanics(player);
			BattleMechanics enemyAttack = new BattleMechanics(enemy);
			events.append(playerAttack.attack(1, enemy));
			health1.setText("Health: " + player.getHealth());
			events.append(enemyAttack.attack(1, player));
			health2.setText("Health: " + enemy.getHealth());
		}
		else
			System.out.println("You ran");
	}
}
