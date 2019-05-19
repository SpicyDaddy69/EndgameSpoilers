package gui;

import characters.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CharacterCreation extends JDialog implements ActionListener {
	private JTextField enterName;
	private JPanel buttons;
	private JButton accept;
	private JButton cancel;

	public CharacterCreation(int width, int height) {
		setTitle("Enter a Name");
		setSize(width, height);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		enterName = new JTextField("");
		accept = new JButton("Accept");
		cancel = new JButton("Cancel");
		buttons = new JPanel(new GridLayout(1, 2));
		buttons.add(accept);
		buttons.add(cancel);
		this.getContentPane().add(enterName, BorderLayout.CENTER);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			this.dispose();
		}

		else if (e.getSource() == accept) {
			Characters player = new Human(enterName.getText());
			dispose();
		}
	}
}
