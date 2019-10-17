package mvc;
import java.awt.Container;
import javax.swing.JFrame;

public class Main {

	private static final int WINDOW_WIDTH = 220;
	private static final int WINDOW_HEIGHT = 100;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Range Slider");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		
		Container c = frame.getContentPane();
		
		c.add(new View(0,99));

		frame.setVisible(true);
	}
}