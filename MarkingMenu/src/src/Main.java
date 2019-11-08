package src;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.Model.type;
import src.View;

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
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
		
		View main_view = new View(0, null, type.paint);
		main_panel.add(main_view);
		
		frame.getContentPane().add(main_panel,BorderLayout.CENTER);
	}
	
	public static void main(String args[]) {
		affichage();
	}
	
}
