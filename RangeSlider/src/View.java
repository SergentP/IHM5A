import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class View extends JComponent {

	private int max_val;
	private int min_val;
	private int inc;

	private static final int SLIDER_WIDTH = 200;
	private static final int BUTTON_WIDTH = 25;
	private static final int HEIGHT = 20;

	private int min_x = BUTTON_WIDTH;
	private int max_x = min_x + SLIDER_WIDTH;

	private int left_button_x;
	private int right_button_x;

	public JLabel left_label;
	public JLabel right_label;
	
	Rectangle2D left_button;
	Rectangle2D right_button;
	Rectangle2D left_range;
	Rectangle2D selected_range;
	Rectangle2D right_range;

	public View(int min, int max) {

		min_val = min;
		max_val = max;
		
		inc = SLIDER_WIDTH / (max_val - min_val);
		
		left_button_x = min_x;
		right_button_x = min_x + SLIDER_WIDTH;

		left_label = new JLabel(Integer.toString(min));
		right_label = new JLabel(Integer.toString(max));
		
		left_button = new Rectangle2D.Double(left_button_x - BUTTON_WIDTH / 2, HEIGHT, BUTTON_WIDTH, HEIGHT);
		right_button = new Rectangle2D.Double(right_button_x - BUTTON_WIDTH / 2, HEIGHT, BUTTON_WIDTH, HEIGHT);
		left_range = new Rectangle2D.Double(min_x, HEIGHT, left_button_x - BUTTON_WIDTH / 2 - min_x, HEIGHT);
		selected_range = new Rectangle2D.Double(left_button_x + BUTTON_WIDTH / 2, HEIGHT, right_button_x - left_button_x - BUTTON_WIDTH, HEIGHT);
		right_range = new Rectangle2D.Double(right_button_x + BUTTON_WIDTH / 2, HEIGHT, max_x - right_button_x - BUTTON_WIDTH / 2, HEIGHT);

	}

	public JLabel getLeftLabel() {
		return left_label;
	}

	public JLabel getRightLabel() {
		return right_label;
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fill(left_button);
		g2d.fill(right_button);
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(left_range);
		g2d.fill(right_range);

		g2d.setColor(Color.GRAY);
		g2d.fill(selected_range);
	}

}