package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class View {
	
	Model model;
	Controller controller;
	
	int nb_b;
	MenuItem[] items;
	String[] labels;
	Point p;
	Canvas c;
	
	public View (int nb_b, String label[], Canvas ca, Controller control) {
		
		model = new Model();
		controller = control;
		
		p = new Point();
//		
//		items = new Rectangle[nb_b];
		labels = new String[nb_b];
		
		for (int i = 0; i < label.length; i++) {
			labels[i] = new String(label[i]);
		}
		c = ca;	
		items = new MenuItem[nb_b];
		for (int i = 0; i < nb_b; i++) {
			items[i] = new MenuItem(labels[i], null, null);
		}	
	}
	
	public void printmenu() {
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(p.x - 40 + Model.coord_circ[i].x, p.y - 15 + Model.coord_circ[i].y, 80, 30);
				items[i].addMouseListener(controller);
			} else {
				items[i].setBounds(p.x + 30, p.y + (i-7)*40, 80, 30);
			}
			c.add(items[i]);
		}
	}
	
	public void clearmenu() {
		for (int i = 0; i < items.length; i++) {
			c.remove(items[i]);
		}
	}
	
	public void setPoint(Point p) {
		this.p = p;
	}
	
	public Point getPoint() {
		return this.p;
	}
	
	public void paintComponent(Graphics g) {
		
//		for (int i = 0; i < items.length; i++) {
//			if (i < 8) {
//				items[i] = new Rectangle((p.x - 25) + Model.coord_circ[i].x, (p.y - 15) + Model.coord_circ[i].y, 50, 30);
//			} else {
//				items[i] = new Rectangle(p.x, p.y + (i-5)*50, 50, 30);
//			}
//		}
//		
//		for (int i = 0; i < items.length; i++) {
//			g2.setColor(Color.LIGHT_GRAY);
//			g2.fill(items[i]);
//			g2.setColor(Color.BLACK);
//			g2.draw(items[i]);
//			g2.drawString(labels[i], (int) (items[i].getCenterX() - 16), (int) items[i].getCenterY() + 4);
//		}
	}
	
}
