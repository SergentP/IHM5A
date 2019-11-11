package src;

import java.awt.BorderLayout;
import java.awt.Point;


import javax.swing.BoxLayout;
import javax.swing.JFrame;

import src.View;
import src.Model.viewtype;

public class Main {
	
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 650;
	
	public static void affichage() {
		JFrame frame = new JFrame();
		frame.setTitle("Range Slider");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		
		String labels[] = {"Tools", "Colours"};
		
		View paint = new View(0, labels, new Point(0,0), viewtype.paint);
		
		paint.setLayout(new BoxLayout(paint, BoxLayout.PAGE_AXIS));
		
		frame.getContentPane().add(paint,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		affichage();
	}
	
}
