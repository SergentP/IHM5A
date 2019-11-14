//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

package src;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Color c = Color.BLACK;
	Canvas can;

	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			can.removeMouseListener(tool);
			can.removeMouseMotionListener(tool);
			tool = this;
			can.addMouseListener(tool);
			can.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3) {
	            System.out.println("Right Click!");
	          }
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				ColoredShape cs = new ColoredShape((shape = path), c);
				shapes.add(cs);
			}
			path.lineTo(e.getX(), e.getY());
			can.repaint();
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				ColoredShape cs = new ColoredShape((shape = rect), c);
				shapes.add(cs);
			}
			rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			can.repaint();
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double ell = (Ellipse2D.Double) shape;
			if (ell == null) {
				ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				ColoredShape cs = new ColoredShape((shape = ell), c);
				shapes.add(cs);
			}
			ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			can.repaint();
		}
	} };
	Tool tool;

	JPanel panel;

	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));

		final JButton red = new JButton();
		red.setBackground(Color.RED);
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = Color.RED;
			}
		});

		final JButton black = new JButton();
		black.setBackground(Color.BLACK);
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = Color.BLACK;
			}
		});

		add(new JToolBar() {
			{
				add(black);
				add(red);

			}
		}, BorderLayout.SOUTH);

		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);

		/*can = new Canvas(shapes);
		
		add(can);*/

		pack();
		setVisible(true);
	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Paint paint = new Paint("paint");
			}
		});
	}
}