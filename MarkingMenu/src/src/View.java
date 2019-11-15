package src;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

public class View {
	
	Model model;
	Controller controller;
	
	int nb_b;
	MenuItem[] items;
	Rectangle[] item_zones;
	Point p;
	Point old_p;
	Canvas can;
	String type;
	
	public View (String type, Canvas c, Controller control) {
		
		this.can = c;
		model = new Model(can);
		controller = control;
		
		p = new Point();
		old_p = new Point();
		this.type = type;
		
		if (type.equals("basic")) {
			nb_b = 2;
		} else if (type.equals("color")) {
			nb_b = model.colortools.length;
		} else if (type.equals("tool")) {
			nb_b = model.shapetools.length;
		}

		items = new MenuItem[nb_b];
		
		for (int i = 0; i < nb_b; i++) {
			if (type.equals("basic")) {
				items[i] = new MenuItem(model.labels[i], null);
			} else if (type.equals("color")) {
				items[i] = new MenuItem(model.colortools[i].name, model.colortools[i]);
			} else if (type.equals("tool")) {
				items[i] = new MenuItem(model.shapetools[i].name, model.shapetools[i]);
			}
		}
		
		item_zones = new Rectangle[nb_b];
	}
	
	public void printmenu() {
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(p.x - 40 + Model.coord_circ[i].x, p.y - 15 + Model.coord_circ[i].y, 90, 20);
			} else {
				items[i].setBounds(p.x - 40, p.y + (i-5)*50, 80, 20);
			}
			items[i].addMouseListener(controller);
			can.add(items[i]);
		}
	}
	
	public void printItem(MenuItem item) {
		for (int i = 0; i < items.length; i++) {
			if(items[i].equals(item)) {
				if (i < 8) {
					items[i].setBounds(p.x - 40 + Model.coord_circ[i].x, p.y - 15 + Model.coord_circ[i].y, 90, 20);
				} else if (items[i].equals(item)){
					items[i].setBounds(p.x - 40, p.y + (i-5)*50, 80, 20);
				}
				can.add(items[i]);
			}
		}
	}
	
	public void clearmenu() {
		for (int i = 0; i < items.length; i++) {
			can.remove(items[i]);
		}
	}
	
	public void setPoint(Point p) {
		this.p = p;
	}
	
	public void setOldPoint(Point p) {
		this.old_p = p;
	}
	
	public Point getOldPoint() {
		return this.old_p;
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
	
	public boolean isPresent(String s) {
		for(int i = 0; i < items.length; i++) {
			if (items[i].getName().equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	//Paint the menu on the canvas with different options whether the expert mode is on or off
	
	public void paintComponent(Graphics g, boolean em) {
		
		Graphics2D g2 = (Graphics2D) g;

		Stroke stroke = new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		g2.setColor(Color.BLACK);
		g2.setStroke(stroke);

		g2.drawLine(p.x, p.y, can.getPoint().x, can.getPoint().y);
		
		if(em) {
			
			g2.drawLine(p.x, p.y, old_p.x, old_p.y);
			
			for (int i = 0; i < items.length; i++) {
				if (i < 8) {
					item_zones[i] = new Rectangle(p.x - 45 + Model.coord_circ[i].x, p.y - 20 + Model.coord_circ[i].y, 90, 30);
				} else {
					item_zones[i] = new Rectangle(p.x - 45, p.y + (i-5)*50 - 5, 90, 30);
				}
				items[i].setRectangle(item_zones[i]);
			}
			
			for (int i = 0; i < items.length; i++) {
				AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0);
		        g2.setComposite(acomp);
				g2.fill(item_zones[i]);
			}
			
		} else if (!em) {
			g2.drawOval(p.x-5, p.y-5, 10, 10);
		}
		
	}
	
}
