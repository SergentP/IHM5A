package src;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuItem extends JButton {
	
	Tool tool;
	Color color;
	
	public MenuItem (String s, Tool t, Color c) {
		super(s);
		this.setName(s);
		tool = t;
		color = c;
	}
}
