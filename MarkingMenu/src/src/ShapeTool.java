package src;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class ShapeTool extends Tool implements MouseInputListener {

	public static ShapeTool tool;
	
	Point o;
	Shape shape;
	Canvas can;

	public ShapeTool(String name, Canvas can) {
		super(name);
		this.can = can;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		o = e.getPoint();
	}

	public void mouseReleased(MouseEvent e) {
		shape = null;
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Point getPoint() {
		return o;
	}
	
	public void setPoint(Point p) {
		this.o = p;
	}

	@Override
	public void execute() {
		System.out.println("using tool " + this.name);
		can.removeMouseListener(tool);
		can.removeMouseMotionListener(tool);
		tool = this;
		can.addMouseListener(tool);
		can.addMouseMotionListener(tool);
	}

}
