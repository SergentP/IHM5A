package mvc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;

	public static void affichage() {
		JFrame frame = new JFrame();
		frame.setTitle("Range Slider");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		
		frame.setLayout(new BorderLayout());
		
		JPanel price = new View(0,10);
		price.setPreferredSize(new Dimension(300,300));
		price.setLayout(new BoxLayout(price, BoxLayout.PAGE_AXIS));

		frame.getContentPane().add(price, BorderLayout.EAST);

		frame.setVisible(true);
	}
	
	public static void main(String[] args) {

		affichage();
	}
}