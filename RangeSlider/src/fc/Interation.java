package fc;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class Interation implements MouseListener{

	enum State {IDLE, PRESSED, DRAGGED};
	State state = State.IDLE;
	Point2D p;
	public static final int D_DRAG = 5;
	
	public Interation() {
		
	}

	public void handle(MouseEvent e) {
		
		switch (state) {
			
		case IDLE:
			switch (e.paramString()) {
			
			case "MOUSE_PRESSED":
				p.setLocation(e.getX(), e.getY());
				if (e.getButton() == MouseEvent.BUTTON1) {
					//move le slider le plus proche sur le pointeur
					state = State.PRESSED;
				}
			break;
			}
		break;
		
		case PRESSED:
			switch (e.paramString()) {
			
			case "MOUSE_DRAGGED":
				p.setLocation(e.getX(), e.getY());
				if (p.distance(e.getX(), e.getY()) >= D_DRAG) {
					state = State.DRAGGED;
				}
				//move le slider le plus proche sur le pointeur
			break;
				
			case "MOUSE_RELEASED":
				//confirmer la valeur du slider
				state = State.IDLE;
			break;
			}
		break;
			
		case DRAGGED:
			switch (e.paramString()) {
			
			case "MOUSE_RELEASED":
				//confirmer la valeur du slider
				state = State.IDLE;
			break;
			
			case "MOUSE_DRAGGED":
				if (p.distance(e.getX(), e.getY()) < D_DRAG) {
					state = State.PRESSED;
				} else {
					//move le slider le plus proche sur le pointeur
				}
					
			break;
			
			}
		break;
			
		}						
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
