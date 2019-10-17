package mvc;

public class Home {
	
	private int x_loc;
	private int y_loc;
	private int price;
	private int nb_room;
	
	public Home(int x, int y, int p, int r) {
		x_loc = x;
		y_loc = y;
		price = p;
		nb_room = r;
	}
	
	public int get_x() {
		return x_loc;
	}
	
	public int get_y() {
		return y_loc;
	}
	
	public int get_p() {
		return price;
	}
	
	public int get_r() {
		return nb_room;
	}
	
}
