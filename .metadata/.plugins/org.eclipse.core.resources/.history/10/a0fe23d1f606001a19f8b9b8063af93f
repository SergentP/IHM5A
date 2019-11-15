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
public class MenuCirculaire extends JPanel {

	static class MenuItem extends JButton {
		
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
	
	public static class ML implements MouseListener {
		public void mouseClicked(MouseEvent e) {System.out.println("button pressed");}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
	
	public MenuCirculaire(int nb_b, String label[], Canvas c) {
		items = new MenuItem[nb_b];
		for (int i = 0; i < nb_b; i++) {
			items[i] = new MenuItem(label[i]);
		}
		for (int i = 0; i < items.length; i++) {
			if (i < 8) {
				items[i].setBounds(380 + coord_circ[i].x, 200 + coord_circ[i].y, 50, 30);
			} else {
				items[i].setBounds(380, 300 + (i-7)*40, 50, 30);
			}
			items[i].addMouseListener(new ML());
			c.add(items[i]);
		}
	}
	
//	private static final int WINDOW_WIDTH = 1000;
//	private static final int WINDOW_HEIGHT = 650;
//
//	public static void main(String argv[]) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				
//				JFrame frame = new JFrame("menucirculaire");
//				frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//				frame.setResizable(true);
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//				frame.setLocationRelativeTo(null);
//				
//				String labels[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
//				JPanel menucirc = new MenuCirculaire(labels.length, labels);
//				frame.add(menucirc);
//				
//				frame.setVisible(true);
//			}
//		});
//	}
//	
}
