package mvc;
import java.awt.Point;
import java.awt.event.*;
import mvc.Model;


public class Controller implements MouseListener, MouseMotionListener{

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
			int min = model.get_lbutton_x() + Model.BUTTON_WIDTH;
			int max = model.get_rbutton_x();
			if ((p.x < min) || (max - p.x) > (p.x - min)) {
				int new_lv = model.get_min_val() + Math.round((p.x - Model.BUTTON_WIDTH/2) * (model.get_max_val()- model.get_min_val()) / (Model.SLIDER_WIDTH - Model.BUTTON_WIDTH));;
				if (new_lv <= model.get_rvalue()) {
					model.set_lbutton_x(p.x - Model.BUTTON_WIDTH/2);
					model.set_lvalue(Math.max(new_lv, model.get_min_val()));
				}
			} else {
				int new_rv = model.get_min_val() + Math.round((p.x - 3 * Model.BUTTON_WIDTH/2) * (model.get_max_val()- model.get_min_val())/(Model.SLIDER_WIDTH - Model.BUTTON_WIDTH));
				if (new_rv >= model.get_lvalue()) {
					model.set_rbutton_x(p.x - Model.BUTTON_WIDTH/2);
					model.set_rvalue(Math.min(new_rv, model.get_max_val()));
				}
			}
			
			//			int dist_to_lbutton = p.x - (int) view.left_button.getCenterX();
//			int dist_to_rbutton = (int) view.right_button.getCenterX() - p.x;
//			int val = (int)Math.round((p.x) * (model.get_max_val() - model.get_min_val())/Model.SLIDER_WIDTH + model.get_min_val());
//			
//			if (dist_to_lbutton < dist_to_rbutton) {
//				model.set_lbutton_x(p.x - Model.BUTTON_WIDTH/2);
//				model.set_lvalue(val);
//			} else if (p.x < model.max_x){
//				model.set_rbutton_x(p.x - Model.BUTTON_WIDTH/2);
//				model.set_rvalue(val);
//			}
		}
		view.repaint();
		hf.repaint();
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
		if (model.left_button_pressed && p.x <= model.get_rbutton_x() - Model.BUTTON_WIDTH/2 && p.x >= model.min_x + Model.BUTTON_WIDTH/2) {
			model.set_lbutton_x(p.x - Model.BUTTON_WIDTH/2);
			int val = model.get_min_val() + Math.round((p.x - Model.BUTTON_WIDTH/2) * (model.get_max_val()- model.get_min_val()) / (Model.SLIDER_WIDTH - Model.BUTTON_WIDTH));
			model.set_lvalue(val);
		}
		if (model.right_button_pressed && p.x >= model.get_lbutton_x() + Model.BUTTON_WIDTH*3/2 && p.x <= model.max_x + Model.BUTTON_WIDTH/2) {
			model.set_rbutton_x(p.x - Model.BUTTON_WIDTH/2);
			int val = model.get_min_val() + Math.round((p.x - 3 * Model.BUTTON_WIDTH/2) * (model.get_max_val()- model.get_min_val())/(Model.SLIDER_WIDTH - Model.BUTTON_WIDTH));
			model.set_rvalue(val);
		}
		view.repaint();
		hf.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
