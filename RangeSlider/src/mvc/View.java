package mvc;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {
	
	Model model;
	Controller control;

	JLabel left_label;
	JLabel right_label;

	Rectangle left_button;
	Rectangle right_button;
	Rectangle2D unselected_range;
	Rectangle2D selected_range;

	public View(int min, int max) {

		model = new Model(min, max, min, max);
		control = new Controller(model);
		
		left_label = new JLabel();
		right_label = new JLabel();

		this.add(left_label);
		this.add(right_label);
		
		unselected_range = new Rectangle2D.Double(model.get_lbutton_x(), model.get_height(), model.get_rbutton_x() + model.get_button_width(), model.get_height());

		this.addMouseListener(control);
		this.addMouseMotionListener(control);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		left_button = new Rectangle(model.get_lbutton_x(), model.get_height(), model.get_button_width(), model.get_height());
		right_button = new Rectangle(model.get_rbutton_x(), model.get_height(), model.get_button_width(), model.get_height());
		selected_range = new Rectangle2D.Double(left_button.getCenterX() + model.get_button_width()/2, model.get_height(), (right_button.getCenterX() - left_button.getCenterX() - model.get_button_width()), model.get_height());
		
		left_label.setBounds(left_button.getBounds());
		left_label.setHorizontalAlignment(JLabel.CENTER);
		left_label.setText(Integer.toString(model.get_lvalue()));
		
		right_label.setBounds(right_button.getBounds());
		right_label.setHorizontalAlignment(JLabel.CENTER);
		right_label.setText(Integer.toString(model.get_rvalue()));
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(unselected_range);
		
		g2d.setColor(Color.GRAY);
		g2d.draw(unselected_range);

		g2d.setColor(Color.GRAY);
		g2d.fill(selected_range);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(selected_range);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(left_button);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(left_button);
		
		g2d.setColor(Color.WHITE);
		g2d.fill(right_button);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(right_button);
		
		g.drawString(Integer.toString(model.get_min_val()), model.get_min_x(), model.get_height() - 5);
		g.drawString(Integer.toString(model.get_max_val()), model.get_max_x() + 15, model.get_height() - 5);
		
	}

}