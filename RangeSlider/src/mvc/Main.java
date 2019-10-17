package mvc;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		layout.setHgap(50);
		frame.setLayout(layout);

		JPanel sliders = new JPanel();
		sliders.setLayout(new BoxLayout(sliders, BoxLayout.PAGE_AXIS));
		
		View price = new View(0,99);
		price.setPreferredSize(new Dimension(300,150));
		JLabel price_label = new JLabel("Price");
		
		sliders.add(price_label);
		sliders.add(price);
		
		View room = new View(0, 10);
		room.setPreferredSize(new Dimension(300,150));
		JLabel room_label = new JLabel("Room number");
		
		sliders.add(room_label);
		sliders.add(room);
		
		JPanel homefinder = new HomeFinder(price, room, 100);
		price.control.addHF((HomeFinder)homefinder);
		room.control.addHF((HomeFinder)homefinder);
		homefinder.setPreferredSize(new Dimension(HomeFinder.X_AXIS, HomeFinder.Y_AXIS));
		
		frame.getContentPane().add(sliders, BorderLayout.EAST);
		frame.getContentPane().add(homefinder, BorderLayout.CENTER);

		
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {

		affichage();
	}
}