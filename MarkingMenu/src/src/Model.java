package src;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Model {
	
	static final int WINDOW_WIDTH = 1000;
	static final int WINDOW_HEIGHT = 650;

	enum viewtype {paint, menu};
	enum contrtype {paint, menu, tool};
	
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
		polar(pi,100), 
		polar(0,100), 
		polar(pi*3/2,100), 
		polar(pi*1/2,100),
		polar(pi*3/4,100), 
		polar(pi*1/4,100), 
		polar(pi*7/4,100),  
		polar(pi*5/4,100)
	};
	
	public Model(String l[], boolean paint) {
		labels = new String[l.length];
		items = new MenuItem[l.length];
		labels = l;
		for (int i = 0; i < labels.length; i++) {
			items[i] = new MenuItem(labels[i]);
		}
	}
	
}
