package src;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {
	
	Shape shape;
	Color color;
	
	public ColoredShape(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Shape getShape() {
		return this.shape;
	}

}
