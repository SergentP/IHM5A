package src;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import src.Model.contrtype;
import src.Model.viewtype;

public class Controller implements MouseListener, MouseMotionListener{
	
	Model model;
	contrtype type;
	
	public Controller(Model model, contrtype type) {
		this.model = model;
		this.type = type;
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {
		View view = (View) e.getSource();
		Point p = e.getPoint();
		View menu_circ = new View(0, model.labels, p, viewtype.menu);
		view.add(menu_circ);
		System.out.println("test");
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	
}
