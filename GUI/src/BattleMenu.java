import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class BattleMenu extends JDialog implements ActionListener
{
	private JButton attack;
	private JButton escape;
	private JPanel menu;
	private JPanel display1;
	private JPanel display2;
	private JPanel display3;
	private JPanel names1;
	private JPanel names2;
	private Container contentPane;
	private JLabel image1;
	private JLabel image2;
	private JLabel image3;
	private Characters player, enemy;
	private JLabel health1;
	private JLabel health2;
	private JLabel name1;
	private JLabel name2;
	private JTextArea events;
	private JScrollPane scroll;
	private Timer tmr;
	
	public BattleMenu(int width, int height, Characters char1, Characters char2)
	{	
		player = char1;
		enemy = char2;
		
		setTitle("Menu");
		setSize(width, height);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
		
		attack = new JButton("Attack");
		attack.addActionListener(this);
		escape = new JButton("Escape");
		escape.addActionListener(this);
		
		image1 = new JLabel(new ImageIcon("placeholder.png"));
		health1 = new JLabel("Health: " + player.getHealth());
		health1.setForeground(Color.GREEN);
		name1 = new JLabel("Name: " + player.getName());
		name1.setForeground(Color.GREEN);
		names1 = new JPanel(new GridLayout(2,1));
		display1 = new JPanel(new BorderLayout());
		display1.add(image1, BorderLayout.NORTH);
		names1.add(name1);
		names1.add(health1);
		display1.add(names1, BorderLayout.CENTER);
		names1.setBackground(Color.DARK_GRAY);
		display1.setBackground(Color.DARK_GRAY);
		
		image2 = new JLabel(new ImageIcon("placeholder.png"));
		health2 = new JLabel("Health: " + enemy.getHealth());
		health2.setForeground(Color.GREEN);
		name2 = new JLabel("Name: " + enemy.getName());
		name2.setForeground(Color.GREEN);
		names2 = new JPanel(new GridLayout(2,1));
		display2 = new JPanel(new BorderLayout());
		display2.add(image2, BorderLayout.NORTH);
		names2.add(name2);
		names2.add(health2);
		display2.add(names2, BorderLayout.CENTER);
		names2.setBackground(Color.DARK_GRAY);
		display2.setBackground(Color.DARK_GRAY);
		
		events = new JTextArea(3, 20);
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
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == attack)
		{
			BattleMechanics playerAttack = new BattleMechanics(player);
			BattleMechanics enemyAttack = new BattleMechanics(enemy);
			events.append(playerAttack.attack(40, enemy));
			health1.setText("Health: " + player.getHealth());
			events.append(enemyAttack.attack(40, player));
			health2.setText("Health: " + enemy.getHealth());
			if(player.getHealth() <= 0)
			{
				events.append("You won");
				tmr = new Timer(2000, this);
				tmr.addActionListener(this);
				tmr.start();
			}
			else if(enemy.getHealth() <= 0)
			{
				events.append("You died lol");
				tmr = new Timer(2000, this);
				tmr.addActionListener(this);
				tmr.start();
			}
		}
		else if(e.getSource() == escape)
		{
			events.append("\nYou ran");
			tmr = new Timer(2000, this);
			tmr.addActionListener(this);
			tmr.start();
		}
		else if(e.getSource() == tmr)
			dispose();
	}
}
