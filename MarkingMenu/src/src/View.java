package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class View {
	
	Model model;
	Controller controller;
	
	Rectangle[] items;
	String[] labels;
	Point p;
	
	public View (int nb_b, String label[]) {
		
		model = new Model(label);
		
		p = new Point();
		
		items = new Rectangle[nb_b];
		labels = new String[nb_b];
		
		for (int i = 0; i < label.length; i++) {
			labels[i] = new String(label[i]);
		}
		
	}

	
	public void setPoint(Point p) {
		this.p = p;
	}
	
	public Point getPoint() {
		return this.p;
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i] = new Rectangle((p.x - 25) + Model.coord_circ[i].x, (p.y - 15) + Model.coord_circ[i].y, 50, 30);
			} else {
				items[i] = new Rectangle(p.x, p.y + (i-5)*50, 50, 30);
			}
		}
		
		for (int i = 0; i < items.length; i++) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.fill(items[i]);
			g2.setColor(Color.BLACK);
			g2.draw(items[i]);
			g2.drawString(labels[i], (int) (items[i].getCenterX() - 16), (int) items[i].getCenterY() + 4);
		}
	}
	
}
