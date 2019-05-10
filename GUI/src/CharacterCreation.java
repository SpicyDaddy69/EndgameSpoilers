import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CharacterCreation extends JDialog implements ActionListener
{
	private JPanel menu;
	private JPanel title;
	private JPanel center;
	private JPanel textFields;
	private JPanel classArea;
	private ButtonGroup classSelection;
	private JRadioButton barbarian;
	private JRadioButton ranger;
	private JRadioButton rogue;
	private JRadioButton wizard;
	private JTextField enterName;
	private JTextField enterStrength;
	private JTextField enterEndurance;
	private JLabel classSelect;
	private JButton accept;
	private JButton cancel;
	private Container contentPane;
	private int strength;
	private int endurance;
	private String name;
	private Characters player;
	
	public CharacterCreation(int width, int height)
	{
		setTitle("Character Creation");
		setSize(width, height);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		classSelect = new JLabel("Select a Class");
		
		barbarian = new JRadioButton("Barbarian");
		ranger = new JRadioButton("Ranger");
		rogue = new JRadioButton("Rogue");
		wizard = new JRadioButton("Wizard");
		
		enterName = new JTextField("Enter a Name");
		enterStrength = new JTextField("Enter strength <1-20>");
		enterEndurance = new JTextField("Enter endurance <1-20>");
		
		accept = new JButton("Accept");
		accept.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		classSelection = new ButtonGroup();
		classSelection.add(barbarian);
		classSelection.add(ranger);
		classSelection.add(rogue);
		classSelection.add(wizard);
		
		title = new JPanel();
		title.add(classSelect);
		
		classArea = new JPanel(new GridLayout(2,2));
		classArea.add(barbarian);
		classArea.add(ranger);
		classArea.add(rogue);
		classArea.add(wizard);
		
		textFields = new JPanel(new GridLayout(3,1));
		textFields.add(enterName);
		textFields.add(enterStrength);
		textFields.add(enterEndurance);
		
		center = new JPanel(new GridLayout(2,1));
		center.add(classArea);
		center.add(textFields);
		
		menu = new JPanel();
		menu.add(accept);
		menu.add(cancel);
		
		contentPane = getContentPane();
		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(center, BorderLayout.CENTER);
		contentPane.add(menu, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cancel)
		{
			this.dispose();
		}
		
		else if(e.getSource() == accept)
		{
			name = "No input";
			strength = 0;
			endurance = 0;
			if(!enterName.getText().equals("Enter a Name"))
				name = enterName.getText();
			if(!enterStrength.getText().equals("Enter strength <1-20>"))
				strength = Integer.parseInt(enterStrength.getText());
			if(!enterEndurance.getText().equals("Enter endurance <1-20>"))
				endurance = Integer.parseInt(enterEndurance.getText());
			if(barbarian.isSelected())
				player = new Barbarian(strength, endurance, name);
			else if(ranger.isSelected())
				player = new Ranger(strength, endurance, name);
			else if(rogue.isSelected())
				player = new Rogue(strength, endurance, name);
			else if(wizard.isSelected())
				player = new Wizard(strength, endurance, name);
			else
				player = new Characters(strength, endurance, name);
			
			dispose();
		}
	}
}
