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
	String[] labels;
	ColorTool[] colortools;
	DrawingTool[] shapetools;
	
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
	
	public Model(Canvas can) {
		this.can = can;
		labels = createLabels();
		colortools = createColorTools();
		shapetools = createShapeTools();
		
	}
	
	private String[] createLabels() {
		String labels[] = { "Tools", "Colors" };
		return labels;
	}
	
	private ColorTool[] createColorTools() {
		ColorTool colorTools[] = { new ColorTool("Black", Color.BLACK, can), new ColorTool("Red", Color.RED, can), new ColorTool("Blue", Color.BLUE, can),
				new ColorTool("Green", Color.GREEN, can), new ColorTool("Yellow", Color.YELLOW, can), new ColorTool("Orange", Color.ORANGE, can), new ColorTool("Purple", Color.MAGENTA, can) , new ColorTool("Cyan", Color.CYAN, can)};
		return colorTools;
	}
	
	private DrawingTool[] createShapeTools() {
		DrawingTool tools[] = { new DrawingTool("Pen", can) {
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
		}, new DrawingTool("Rect", can) {
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
		}, new DrawingTool("Ellipse", can) {
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
		}, new DrawingTool("Eraser", can) {
			public void mouseDragged(MouseEvent e) {
				if(!can.clicked) {
					if (findShape(e.getPoint()) != null) {
						can.getShapes().remove(findShape(e.getPoint()));
					}
					can.repaint();
				}
			}
		}};
		return tools;
	}
	
	public ColoredShape findShape(Point p) {
		for (int i = 0; i < can.shapes.size(); i++) {
			if (can.getShapes().get(i).getShape().contains(p)) {
				return can.getShapes().get(i);
			}
		}
		return null;
	}
}