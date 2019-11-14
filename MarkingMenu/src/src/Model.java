package src;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Model {
	
	Canvas can;
	MenuItem items[];
	String labels[];
	ColorTool[] colortools;
	ShapeTool[] shapetools;
	
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
	
	public Model(Canvas can) {
		this.can = can;
		colortools = createColorTools();
		shapetools = createShapeTools();
		
	}
	
	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { new ColorTool("black", Color.BLACK, can), new ColorTool("red", Color.RED, can), new ColorTool("blue", Color.BLUE, can),
				new ColorTool("green", Color.GREEN, can) };
		return colorTools;
	}
	
	private ShapeTool[] createShapeTools() {
		ShapeTool tools[] = { new ShapeTool("pen", can) {
			public void mouseDragged(MouseEvent e) {
				if(!can.clicked) {
					Path2D.Double path = (Path2D.Double) shape;
					if (path == null) {
						path = new Path2D.Double();
						path.moveTo(o.getX(), o.getY());
						ColoredShape cs = new ColoredShape((shape = path), can.getColor());
						can.getShapes().add(cs);
					}
					path.lineTo(e.getX(), e.getY());
					can.repaint();
				}
			}
		}, new ShapeTool("rect", can) {
			public void mouseDragged(MouseEvent e) {
				if(!can.clicked) {
					Rectangle2D.Double rect = (Rectangle2D.Double) shape;
					if (rect == null) {
						rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
						ColoredShape cs = new ColoredShape((shape = rect), can.getColor());
						can.getShapes().add(cs);
					}
					rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
							abs(e.getY() - o.getY()));
					can.repaint();
				}
			}
		}, new ShapeTool("ellipse", can) {
			public void mouseDragged(MouseEvent e) {
				if(!can.clicked) {
					Ellipse2D.Double ell = (Ellipse2D.Double) shape;
					if (ell == null) {
						ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
						ColoredShape cs = new ColoredShape((shape = ell), can.getColor());
						can.getShapes().add(cs);
					}
					ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
							abs(e.getY() - o.getY()));
					can.repaint();
				}
			}
		} };
		return tools;
	}
}
