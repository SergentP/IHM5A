package mvc;
import java.awt.event.*;
import java.awt.geom.*;
import mvc.Model;


public class Controller implements MouseListener{

	enum State {IDLE, PRESSED, DRAGGED};
	State state = State.IDLE;
	Point2D p;
	public static final int D_DRAG = 5;
	public Model model;
	
	public Controller(Model model) {
		this.model = model;
	}

	private void moveSlider(int x) {
		int min = model.getMin();
		int max = model.getMax();
		if ((x < min) ||(max - x) > (x - min)) {
			model.setMin(Math.max(x, model.ming));
		} else {
			model.setMax(Math.min(x, model.maxg));
		}
	}
	
	public void handle(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		p.setLocation(x, y);
		
		switch (state) {
			
			case IDLE:
				switch (e.paramString()) {
				
				case "MOUSE_PRESSED":
					if (e.getButton() == MouseEvent.BUTTON1) {
						moveSlider(x);
						state = State.PRESSED;
					}
				break;
				}
			break;
			
			case PRESSED:
				switch (e.paramString()) {
				
				case "MOUSE_DRAGGED":
					if (p.distance(x, y) >= D_DRAG) {
						state = State.DRAGGED;
					}
					moveSlider(x);
				break;
					
				case "MOUSE_RELEASED":
					state = State.IDLE;
				break;
				}
			break;
				
			case DRAGGED:
				switch (e.paramString()) {
				
				case "MOUSE_RELEASED":
					state = State.IDLE;
				break;
				
				case "MOUSE_DRAGGED":
					if (p.distance(x, y) < D_DRAG) {
						state = State.PRESSED;
					} else {
						moveSlider(x);
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
