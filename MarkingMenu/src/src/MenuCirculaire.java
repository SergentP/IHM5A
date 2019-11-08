package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MenuCirculaire extends JFrame {

	class MenuItem extends JButton {
		
		Point p;
		
		public MenuItem (String name) {
			super(name);
		}
	}
	
	MenuItem items[];
	JPanel panel;
	
	
	public Point polar(double t, double d) {	      
	      double x = d * Math.cos(t);
	      double y = d * Math.sin(t);
	      
	      return new Point((int) x,(int) y);
	}

	double pi = Math.PI;
	
	Point coord_circ[] = {
		polar(pi,100), 
		polar(0,100), 
		polar(pi*3/2,100), 
		polar(pi*1/2,100),
		polar(pi*3/4,100), 
		polar(pi*1/4,100), 
		polar(pi*7/4,100),  
		polar(pi*5/4,100)
	};
	
	private class ML implements MouseListener {
		public void mouseClicked(MouseEvent e) {System.out.println("button pressed");}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	
	public MenuCirculaire(String title, int nb_b, String label[], JPanel p) {
		super(title);
		panel = p;
		items = new MenuItem[nb_b];
		for (int i = 0; i < nb_b; i++) {
			items[i] = new MenuItem(label[i]);
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		panel.setLayout(null);
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(380 + coord_circ[i].x, 200 + coord_circ[i].y, 50, 30);
			} else {
				items[i].setBounds(380, 300 + (i-7)*40, 50, 30);
			}
			
			items[i].addMouseListener(new ML());
			panel.add(items[i]);
		}
		add(panel);

		pack();
		setVisible(true);
	}

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JPanel panel = new JPanel() {	
					public void paintComponent(Graphics g) {
						super.paintComponent(g);	
						Graphics2D g2 = (Graphics2D)g;
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
						                    RenderingHints.VALUE_ANTIALIAS_ON);
				
						g2.setColor(Color.WHITE);
						g2.fillRect(0, 0, getWidth(), getHeight());
					}
				};
				String labels[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
				new MenuCirculaire("Menu Circulaire", labels.length, labels, panel);
			}
		});
	}
	
}
