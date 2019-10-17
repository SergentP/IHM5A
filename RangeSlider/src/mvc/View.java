package mvc;
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
	
	private Model model;
	private Controller control;

	static final int SLIDER_WIDTH = 200;
	static final int BUTTON_WIDTH = 20;
	static final int HEIGHT = 20;

	int min_x = 0;
	int max_x = SLIDER_WIDTH;

	Point lastpoint;

	JLabel left_label;
	JLabel right_label;

	Rectangle left_button;
	Rectangle right_button;
	Rectangle2D unselected_range;
	Rectangle2D selected_range;
	
	boolean left_button_pressed = false;
	boolean right_button_pressed = false;

	public View(int min, int max) {

		model = new Model(min, max, min, max, 0, SLIDER_WIDTH);
		control = new Controller(model);
		
		left_label = new JLabel(""+ model.get_lvalue());
		right_label = new JLabel(""+model.get_rvalue());

		this.add(left_label);
		this.add(right_label);
		
		unselected_range = new Rectangle2D.Double(model.get_lbutton_x(), HEIGHT, model.get_rbutton_x() + BUTTON_WIDTH, HEIGHT);

		this.addMouseListener(control);
		this.addMouseMotionListener(control);

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
		if (left_button_pressed && p.x <= model.get_rbutton_x() - BUTTON_WIDTH && p.x >= min_x) {
			model.set_lbutton_x(p.x);
			int val = (int)Math.round((p.x) * (model.get_maxg() - model.get_ming())/SLIDER_WIDTH + model.get_ming());

			if (p.x > lastpoint.x) {
				model.set_lvalue(val);
			}
			if (p.x < lastpoint.x) {
				model.set_lvalue(val);
			}
		}
		if (right_button_pressed && p.x >= model.get_lbutton_x() + BUTTON_WIDTH && p.x <= max_x) {
			model.set_rbutton_x(p.x);
			int val = (int)Math.round((p.x) * (model.get_maxg()- model.get_ming())/SLIDER_WIDTH + model.get_ming());
			
			if (p.x > lastpoint.x) {
				model.set_rvalue(val);
			}
			if (p.x < lastpoint.x) {
				model.set_rvalue(val);
			}
		}
		lastpoint = p;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		left_button = new Rectangle(model.get_lbutton_x(), HEIGHT, BUTTON_WIDTH, HEIGHT);
		right_button = new Rectangle(model.get_rbutton_x(), HEIGHT, BUTTON_WIDTH, HEIGHT);
		selected_range = new Rectangle2D.Double(left_button.getCenterX(), HEIGHT, (right_button.getCenterX() - left_button.getCenterX()), HEIGHT);
		
		left_label.setBounds(left_button.getBounds());
		left_label.setHorizontalAlignment(JLabel.CENTER);
		left_label.setText(""+model.get_lvalue());
		
		right_label.setBounds(right_button.getBounds());
		right_label.setHorizontalAlignment(JLabel.CENTER);
		right_label.setText(Integer.toString(model.get_rvalue()));
		
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