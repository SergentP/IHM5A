package mvc;

public class Model {
	
	private int maxg;
	private int ming;
	
	private int lvalue;
	private int rvalue;

	private int lbutton_x;
	private int rbutton_x;
	
	public Model(int min, int max, int l, int r, int lx, int rx) {
		ming = min;
		maxg = max;
		lvalue = l;
		rvalue = r;
		lbutton_x = lx;
		rbutton_x = rx;
	}
	
// getters & setters //
//
	public int get_ming() {
		return ming;
	}
	
	public int get_maxg() {
		return maxg;
	}
	
	public void set_ming(int x) {
		ming = x;
	}
	
	public void set_maxg(int x) {
		maxg = x;
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
