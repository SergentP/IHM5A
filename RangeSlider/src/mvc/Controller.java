package mvc;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.*;
import mvc.Model;


public class Controller implements MouseListener, MouseMotionListener{

	enum State {IDLE, PRESSED, DRAGGED};
	State state = State.IDLE;
	Point2D p;
	public static final int D_DRAG = 5;
	Model model;
	HomeFinder hf;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void addHF(HomeFinder hf) {
		this.hf = hf;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		View view = (View) e.getSource();
		Point p = e.getPoint();
		if ((view.selected_range.contains(p) || view.unselected_range.contains(p))) {
			int dist_to_lbutton = p.x - (int) view.left_button.getCenterX();
			int dist_to_rbutton = (int) view.right_button.getCenterX() - p.x;
			int val = (int)Math.round((p.x) * (model.get_max_val() - model.get_min_val())/(Model.SLIDER_WIDTH - Model.BUTTON_WIDTH) + model.get_min_val());
			
			if (dist_to_lbutton < dist_to_rbutton) {
				model.set_lbutton_x(p.x);
				model.set_lvalue(val);
			} else if (p.x < model.max_x){
				model.set_rbutton_x(p.x);
				model.set_rvalue(val);
			}
		}
		
		view.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		View view = (View) e.getSource();
		Point p = e.getPoint();
		if (view.left_button.contains(p)) {
			model.left_button_pressed = true;

		}
		if (view.right_button.contains(p)) {
			model.right_button_pressed = true;
		}
		model.lastpoint = p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		model.left_button_pressed = false;
		model.right_button_pressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		View view = (View) e.getSource();
		Point p = e.getPoint();
		if (model.left_button_pressed && p.x <= model.get_rbutton_x() - Model.BUTTON_WIDTH && p.x >= model.min_x) {
			model.set_lbutton_x(p.x);
			int val = (int)Math.round((p.x) * (model.get_max_val() - model.get_min_val())/(Model.SLIDER_WIDTH - Model.BUTTON_WIDTH) + model.get_min_val());

			if (p.x > model.lastpoint.x) {
				model.set_lvalue(val);
			}
			if (p.x < model.lastpoint.x) {
				model.set_lvalue(val);
			}
		}
		if (model.right_button_pressed && p.x >= model.get_lbutton_x() + Model.BUTTON_WIDTH && p.x <= model.max_x) {
			model.set_rbutton_x(p.x);
			int val = (int)Math.round((p.x) * (model.get_max_val()- model.get_min_val())/(Model.SLIDER_WIDTH) + model.get_min_val());
			
			if (p.x > model.lastpoint.x) {
				model.set_rvalue(val);
			}
			if (p.x < model.lastpoint.x) {
				model.set_rvalue(val);
			}
		}
		model.lastpoint = p;
		view.repaint();
		hf.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	

	/*private void moveSlider(int x) {
		int min = model.get_lvalue();
		int max = model.get_rvalue();
		if ((x < min) ||(max - x) > (x - min)) {
			model.set_ming(Math.max(x, model.get_ming()));
		} else {
			model.set_maxg(Math.min(x, model.get_maxg()));
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
		
	}*/
}
