package src;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.event.MouseInputListener;

@SuppressWarnings("serial")
class Tool extends AbstractAction implements MouseInputListener {
	Point o;
	Shape shape;
	View view;
	Model model;

	public Tool(String name, View view) {
		super(name);
		this.view = view;
		model = view.model;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("using tool " + this);
		view.removeMouseListener(model.tool);
		view.removeMouseMotionListener(model.tool);
		model.tool = this;
		view.addMouseListener(model.tool);
		view.addMouseMotionListener(model.tool);
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
}