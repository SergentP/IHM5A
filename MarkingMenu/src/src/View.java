package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import src.MenuCirculaire.MenuItem;
import src.Model;
import src.Model.contrtype;
import src.Model.viewtype;

@SuppressWarnings("serial")
public class View extends JPanel{
	
	Model model;
	Controller controller;
	viewtype type;
	
	public View (int nb_b, String label[], Point p, viewtype type) {
		
		model = new Model(label,(type == viewtype.paint));
		this.type = type;
		if (type == viewtype.paint) {
			controller = new Controller(model, contrtype.paint, null);
		} else {

			controller = new Controller(model, contrtype.menu, null);
		}
		
		
//		final JButton red = new JButton();
//		red.setBackground(Color.RED);
//		red.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				model.c = Color.RED;
//			}
//		});
//
//		final JButton black = new JButton();
//		black.setBackground(Color.BLACK);
//		black.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				model.c = Color.BLACK;
//			}
//		});
//
//		add(new JToolBar() {
//			{
//				add(black);
//				add(red);
//
//			}
//		}, BorderLayout.SOUTH);
//
//		add(new JToolBar() {
//			{
//				for (AbstractAction tool : tools) {
//					add(tool);
//				}
//			}
//		}, BorderLayout.NORTH);
		
		if (this.type == viewtype.paint) {
			addMouseListener(controller);
			addMouseMotionListener(controller);
		} else {
			MenuItem[] items = new MenuItem[nb_b];
			for (int i = 0; i < nb_b; i++) {
				items[i] = new MenuItem(label[i]);
			}
			setLayout(null);
			for (int i = 0; i < items.length; i++) {
				if (i < 8) {
					items[i].setBounds(p.x + Model.coord_circ[i].x, p.y + Model.coord_circ[i].y, 50, 30);
				} else {
					items[i].setBounds(p.x, p.y + (i-7)*40, 50, 30);
				}
				items[i].addMouseListener(controller);
				add(items[i]);
			}
			setVisible(true);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.type == viewtype.paint) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, getWidth(), getHeight());

			for (ColoredShape shape : model.shapes) {
				g2.setColor(shape.getColor());
				g2.draw(shape.getShape());
			}
		}
	}
	
}
