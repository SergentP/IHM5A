package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class Main extends JFrame {

	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Boolean expert_mode = false;
	Canvas can;

	/*class Tool extends AbstractAction implements MouseInputListener {
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
				ColoredShape cs = new ColoredShape((shape = path), can.getColor());
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
				ColoredShape cs = new ColoredShape((shape = rect), can.getColor());
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
				ColoredShape cs = new ColoredShape((shape = ell), can.getColor());
				shapes.add(cs);
			}
			ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			can.repaint();
		}
	} };
	Tool tool;*/
	
	public Main(String title) {

		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		can = new Canvas(shapes);
		
		final JButton red = new JButton();
		red.setBackground(Color.RED);
		red.setPreferredSize(new Dimension(20,20));
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				can.setColor(Color.RED);
				System.out.println("color Red selected");
			}
		});

		final JButton black = new JButton();
		black.setBackground(Color.BLACK);
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				can.setColor(Color.BLACK);
				System.out.println("color Black selected");
			}
		});
		
		final JButton expert = new JButton("Mode Expert");
		expert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!can.getClicked()) {
					expert_mode = !expert_mode; 
					can.setModeExpert(expert_mode);
					if(expert_mode) {
						expert.setBackground(Color.GREEN);
					} else {
						expert.setBackground(Color.WHITE);
					}
				}
			}
		});
		
		add(new JToolBar() {
			{
				add(expert);

			}
		}, BorderLayout.WEST);

		add(new JToolBar() {
			{
				add(black);
				add(red);

			}
		}, BorderLayout.SOUTH);

		/*add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);*/
		
		add(can);

		pack();
		setVisible(true);

	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Main main = new Main("paint");
			}
		});
	}

}
