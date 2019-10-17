package mvc;

import java.awt.Point;

public class Model {
	
	static final int SLIDER_WIDTH = 200;
	static final int BUTTON_WIDTH = 33;
	static final int HEIGHT = 20;
	
	private int max_val; // minimum of the slider values
	private int min_val; // maximum of the slider values

	int min_x; // minimum of the slider pixels
	int max_x; // maximum of the slider pixels
	
	private int lvalue; // value of the left slider
	private int rvalue; // value of the right slider

	private int lbutton_x; // value of the external side of the left button
	private int rbutton_x; // value of the internal side of the left button
	
	boolean left_button_pressed;
	boolean right_button_pressed;

	Point lastpoint;
	
	public Model(int min, int max, int l, int r) {
		min_val = min;
		max_val = max;
		lvalue = l;
		rvalue = r;
		lbutton_x = 0;
		rbutton_x = SLIDER_WIDTH;
		min_x = 0;
		max_x = SLIDER_WIDTH;
		left_button_pressed = false;
		right_button_pressed = false;
	}
	
// getters & setters //
//
	public int get_min_val() {
		return min_val;
	}
	
	public int get_max_val() {
		return max_val;
	}
	
	public void set_min_val(int x) {
		min_val = x;
	}
	
	public void set_max_val(int x) {
		max_val = x;
	}
	
	public int get_lvalue() {
		return lvalue;
	}
	
	public int get_rvalue() {
		return rvalue;
	}
	
	public void set_lvalue(int x) {
		lvalue = x;
	}

	public void set_rvalue(int x) {
		rvalue = x;
	}
	
	public int get_lbutton_x() {
		return lbutton_x;
	}
	
	public int get_rbutton_x() {
		return rbutton_x;
	}
	
	public void set_lbutton_x(int x) {
		lbutton_x = x;
	}
	
	public void set_rbutton_x(int x) {
		rbutton_x = x;
	}
	
}
