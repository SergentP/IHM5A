package src;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class View {
	
	Model model;
	Controller controller;
	
	int nb_b;
	MenuItem[] items;
	String[] labels;
	Rectangle[] item_zones;
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
		
		item_zones = new Rectangle[nb_b];
	}
	
	public void printmenu(boolean em) {
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(p.x - 40 + Model.coord_circ[i].x, p.y - 15 + Model.coord_circ[i].y, 80, 20);
				items[i].addMouseListener(controller);
			} else {
				items[i].setBounds(p.x - 40, p.y + (i-5)*50, 80, 20);
			}
			if(!em) {
				c.add(items[i]);
			}
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
	
	public MenuItem contain(Point p) {
		for(int i = 0; i< items.length; i++) {
			if(items[i].getRectangle().contains(p)) {
				return items[i];
			}
		}
		return null;
	}
	
	public void paintComponent(Graphics g, boolean em) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				item_zones[i] = new Rectangle(p.x - 45 + Model.coord_circ[i].x, p.y - 20 + Model.coord_circ[i].y, 90, 30);
			} else {
				item_zones[i] = new Rectangle(p.x - 45, p.y + (i-5)*50 - 5, 90, 30);
			}
			items[i].setRectangle(item_zones[i]);
		}
		
		for (int i = 0; i < items.length; i++) {
			if (em) {
				AlphaComposite acomp = AlphaComposite.getInstance(
		                AlphaComposite.SRC_OVER, 0.01f);
		        g2.setComposite(acomp);
			} else {
				g2.setColor(Color.LIGHT_GRAY);
			}
			g2.fill(item_zones[i]);
		}
	}
	
}
