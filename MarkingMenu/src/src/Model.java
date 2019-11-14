package src;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Model {
	
	
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();

	Tool tools[] = {new Tool("Pen", this), new Tool("Rectangle", this), new Tool("Ellipse", this)};
	Tool tool = tools[0];
	
	Color colors[] = {Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.WHITE, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.MAGENTA, Color.PINK};
	Color c = colors[0];
	
	MenuItem items[];
	String labels[];
	
	static double pi = Math.PI;
	
	public static Point polar(double t, double d) {	      
	      double x = d * Math.cos(t);
	      double y = d * Math.sin(t);
	      
	      return new Point((int) x,(int) y);
	}
	
	static Point coord_circ[] = {
		polar(pi,120), 
		polar(0,120), 
		polar(pi*3/2,120), 
		polar(pi*1/2,120),
		polar(pi*3/4,120), 
		polar(pi*1/4,120), 
		polar(pi*7/4,120),  
		polar(pi*5/4,120)
	};
	
	public Model() {}
	
}
