import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final int WINDOW_WIDTH = 220;
	private static final int WINDOW_HEIGHT = 100;

	public Main() {

		this.setTitle("Range Slider");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);
		
		Container c = this.getContentPane();
		
		c.add(new View(0,99));

		this.setVisible(true);
	}

	public static void main(String[] args) {

		Main main = new Main();
	}
}