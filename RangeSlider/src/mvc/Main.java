package mvc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	private static final int WINDOW_WIDTH = 820;
	private static final int WINDOW_HEIGHT = 600;

	public static void affichage() {
		JFrame frame = new JFrame();
		frame.setTitle("Range Slider");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		
		frame.setLayout(new BorderLayout());
		
		JPanel price = new View(0,99);
		price.setPreferredSize(new Dimension(240,150));
		JPanel room = new View(0, 10);
		room.setPreferredSize(new Dimension(220,150));
		price.setLayout(new BoxLayout(price, BoxLayout.PAGE_AXIS));

		frame.getContentPane().add(price, BorderLayout.EAST);
		
		JPanel homefinder = new HomeFinder((View) price, (View) room, 10);
		((View)price).control.addHF((HomeFinder)homefinder);
		((View)room).control.addHF((HomeFinder)homefinder);
		homefinder.setPreferredSize(new Dimension(HomeFinder.X_AXIS, HomeFinder.Y_AXIS));
		
		frame.getContentPane().add(homefinder, BorderLayout.CENTER);

		frame.setVisible(true);
	}
	
	public static void main(String[] args) {

		affichage();
	}
}