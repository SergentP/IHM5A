import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private static final int WINDOW_WIDTH = 250;
	private static final int WINDOW_HEIGHT = 200;

	public Main() {

		this.setTitle("Range Slider");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());
		
		Container c = this.getContentPane();

		JPanel rangeSlider = createRangeSlider(0,100);

		c.add(rangeSlider, BorderLayout.CENTER);

		this.setVisible(true);
	}

	private JPanel createRangeSlider(int min, int max) {
		
		JPanel panel = new JPanel(new BorderLayout());
		View rangeSlider = new View(min, max);
		panel.add(rangeSlider, BorderLayout.CENTER);

		return panel;
	}

	public static void main(String[] args) {

		Main main = new Main();
	}
}