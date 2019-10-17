package mvc;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomeFinder extends JPanel{
	
	private View price;
	private View room;
	
	static final int X_AXIS = 600;
	static final int Y_AXIS = 600;

	private int nb_house;
	private Home houses[];
	
	public HomeFinder(View p, View r, int n) {
		
		Color background = new Color(44,181,85);
		this.setBackground(background);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		price = p;
		room = r;
		nb_house = n;
		houses = new Home[nb_house];
		for (int i =0; i < n; i++) {
			houses[i] = new Home((int)(Math.random()*X_AXIS), (int)(Math.random()*Y_AXIS), (int)(Math.random()*price.model.get_max_val()), (int)(Math.random()*room.model.get_max_val()));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		int p_lvalue = price.model.get_lvalue();
		int p_rvalue = price.model.get_rvalue();
		int r_lvalue = room.model.get_lvalue();
		int r_rvalue = room.model.get_rvalue();

		for (int i = 0; i < nb_house; i++) {
			int p = houses[i].get_p();
			int r = houses[i].get_r();
			if (p_lvalue < p && p < p_rvalue && r_lvalue < r && r < r_rvalue) {
				g.drawOval(houses[i].get_x(), houses[i].get_y(), 10, 10);
			}
		}
		
	}
	
}
