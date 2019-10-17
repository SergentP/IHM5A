import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

	private int max_val;
	private int min_val;
	private int lvalue;
	private int rvalue;

	private static final int SLIDER_WIDTH = 200;
	private static final int BUTTON_WIDTH = 20;
	private static final int HEIGHT = 20;
	private static final int OFFSET = 30;

	private int min_x = BUTTON_WIDTH;
	private int max_x = min_x + SLIDER_WIDTH;

	private int left_button_x;
	private int right_button_x;
	private Point lastpoint;

	private boolean left_button_pressed = false;
	private boolean right_button_pressed = false;

	public JLabel left_label;
	public JLabel right_label;

	Rectangle left_button;
	Rectangle right_button;
	Rectangle2D unselected_range;
	Rectangle2D selected_range;

	public View(int min, int max) {

		min_val = min;
		max_val = max;

		lvalue = min;
		rvalue = max;

		left_button_x = 0;
		right_button_x = SLIDER_WIDTH;
		
		left_label = new JLabel(""+lvalue);
		right_label = new JLabel(""+rvalue);

		this.add(left_label);
		this.add(right_label);
		
		unselected_range = new Rectangle2D.Double(left_button_x, HEIGHT, right_button_x + BUTTON_WIDTH, HEIGHT);

		setListener();

	}

	private void setListener() {

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}

			public void mouseReleased(MouseEvent e) {
				left_button_pressed = false;
				right_button_pressed = false;

			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				dragged(e);

			}
		});
	}

	private void pressed(MouseEvent e) {

		Point p = e.getPoint();
		if (left_button.contains(p)) {
			System.out.println("Left button clicked");
			left_button_pressed = true;

		}
		if (right_button.contains(p)) {
			System.out.println("Right button clicked");
			right_button_pressed = true;
		}
		lastpoint = p;
	}

	private void dragged(MouseEvent e) {
		Point p = e.getPoint();
		if (left_button_pressed && p.x <= right_button_x - BUTTON_WIDTH && p.x >= min_x-BUTTON_WIDTH) {
			left_button_x = p.x;
			int val = (int)Math.round((p.x) * (max_val - min_val)/SLIDER_WIDTH + min_val);

			if (p.x > lastpoint.x) {
				lvalue = val;
			}
			if (p.x < lastpoint.x) {
				lvalue = val;
			}
		}
		if (right_button_pressed && p.x >= left_button_x + BUTTON_WIDTH && p.x <= max_x-BUTTON_WIDTH) {
			right_button_x = p.x;
			int val = (int)Math.round((p.x) * (max_val- min_val)/SLIDER_WIDTH + min_val);
			
			if (p.x > lastpoint.x) {
				rvalue = val;
			}
			if (p.x < lastpoint.x) {
				rvalue = val;
			}
		}
		lastpoint = p;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		left_button = new Rectangle(left_button_x, HEIGHT, BUTTON_WIDTH, HEIGHT);
		right_button = new Rectangle(right_button_x, HEIGHT, BUTTON_WIDTH, HEIGHT);
		selected_range = new Rectangle2D.Double(left_button.getCenterX(), HEIGHT, (right_button.getCenterX() - left_button.getCenterX()), HEIGHT);
		
		left_label.setBounds(left_button.getBounds());
		left_label.setHorizontalAlignment(JLabel.CENTER);
		left_label.setText(""+lvalue);
		
		right_label.setBounds(right_button.getBounds());
		right_label.setHorizontalAlignment(JLabel.CENTER);
		right_label.setText(Integer.toString(rvalue));
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(unselected_range);

		g2d.setColor(Color.GRAY);
		g2d.fill(selected_range);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(left_button);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(right_button);
		
	}

}