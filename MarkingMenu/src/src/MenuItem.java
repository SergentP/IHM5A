package src;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuItem extends JButton {
	
	Tool tool;
	Color color;
	Rectangle rect;
	
	public MenuItem (String s, Tool t) {
		super(s);
		this.setName(s);
		this.tool = t;
	}
	
	public void setRectangle(Rectangle r) {
		rect = r;
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
}
