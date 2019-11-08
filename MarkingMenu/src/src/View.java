package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import src.MenuCirculaire.ML;
import src.MenuCirculaire.MenuItem;
import src.Model;

@SuppressWarnings("serial")
public class View extends JPanel{
	
	public View (int nb_b, String label[]) {
		MenuItem[] items = new MenuItem[nb_b];
		for (int i = 0; i < nb_b; i++) {
			items[i] = new MenuItem(label[i]);
		}
		setMinimumSize(new Dimension(800, 600));
		setLayout(null);
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(380 + Model.coord_circ[i].x, 200 + Model.coord_circ[i].y, 50, 30);
			} else {
				items[i].setBounds(380, 300 + (i-7)*40, 50, 30);
			}
			
			items[i].addMouseListener(new ML());
			add(items[i]);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		                    RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());
	}
	
}
