package src;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import src.Model.contrtype;
import src.Model.viewtype;

public class Controller implements MouseListener, MouseMotionListener{
	
	Model model;
	contrtype type;
	Tool tool;
	Point p;
	
	public Controller(Model model, contrtype type, Tool tool) {
		this.model = model;
		this.type = type;
		this.tool = tool;
	}

	public void mouseDragged(MouseEvent e) {
		View view = (View) e.getSource();
		Point o = e.getPoint();
		if (type == contrtype.tool) {
			if (tool.name == "Rectangle") {
				Rectangle2D.Double rect = (Rectangle2D.Double) tool.shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					ColoredShape cs = new ColoredShape((tool.shape = rect), model.c);
					model.shapes.add(cs);
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				view.repaint();
			} else if (tool.name == "Ellipse") {
				Ellipse2D.Double ell = (Ellipse2D.Double) tool.shape;
				if (ell == null) {
					ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					ColoredShape cs = new ColoredShape((tool.shape = ell), model.c);
					model.shapes.add(cs);
				}
				ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				view.repaint();
			} else {
				Path2D.Double path = (Path2D.Double) tool.shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					ColoredShape cs = new ColoredShape((tool.shape = path), model.c);
					model.shapes.add(cs);
				}
				path.lineTo(e.getX(), e.getY());
				view.repaint();
			}
		}
	}

	public void mouseMoved(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {
		if (type == contrtype.paint) {
			View view = (View) e.getSource();
			Point p = e.getPoint();
			View menu_circ = new View(0, model.labels, p, viewtype.menu);
			view.add(menu_circ);
			System.out.println("test");
		} else if (type == contrtype.menu) {
			
		}
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	
}
