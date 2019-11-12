package src;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.event.MouseInputListener;

class Tool {
	Point o;
	Shape shape;
	Model model;
	public String name;

	public Tool(String name, Model model) {
		this.name = name;
		this.model = model;
	}

}