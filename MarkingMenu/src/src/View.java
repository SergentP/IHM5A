package src;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

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
			controller = new Controller(model, contrtype.paint);
		} else {

			controller = new Controller(model, contrtype.menu);
		}
		
		Tool tools[] = { new Tool("pen", this) {
			public void mouseDragged(MouseEvent e) {
				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					ColoredShape cs = new ColoredShape((shape = path), model.c);
					model.shapes.add(cs);
				}
				path.lineTo(e.getX(), e.getY());
				view.repaint();
			}
		}, new Tool("rect", this) {
			public void mouseDragged(MouseEvent e) {
				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					ColoredShape cs = new ColoredShape((shape = rect), model.c);
					model.shapes.add(cs);
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				view.repaint();
			}
		}, new Tool("ellipse", this) {
			public void mouseDragged(MouseEvent e) {
				Ellipse2D.Double ell = (Ellipse2D.Double) shape;
				if (ell == null) {
					ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					ColoredShape cs = new ColoredShape((shape = ell), model.c);
					model.shapes.add(cs);
				}
				ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				view.repaint();
			}
		} };
		
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
		
		model.tool = tools[0];
		if (this.type == viewtype.paint) {
			addMouseListener(controller);
			addMouseMotionListener(model.tool);
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
