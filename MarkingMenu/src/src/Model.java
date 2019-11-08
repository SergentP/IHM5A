package src;

import java.awt.Point;

public class Model {

	MenuItem items[];

	String labels[];
	
	double pi = Math.PI;
	
	public Point polar(double t, double d) {	      
	      double x = d * Math.cos(t);
	      double y = d * Math.sin(t);
	      
	      return new Point((int) x,(int) y);
	}
	
	Point coord_circ[] = {
		polar(pi,100), 
		polar(0,100), 
		polar(pi*3/2,100), 
		polar(pi*1/2,100),
		polar(pi*3/4,100), 
		polar(pi*1/4,100), 
		polar(pi*7/4,100),  
		polar(pi*5/4,100)
	};
	
	public Model(String l[]) {
		
		this.labels = new String[l.length];
		this.labels = l;

		
	}
	
}
