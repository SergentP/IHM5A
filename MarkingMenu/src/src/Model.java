package src;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public class Model {

	enum viewtype {paint, menu};
	enum contrtype {paint, menu};
	
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color c = Color.BLACK;
	Tool tool;
	
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
