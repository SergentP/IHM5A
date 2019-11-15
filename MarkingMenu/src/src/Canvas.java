package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	Vector<ColoredShape> shapes;
	Color c = Color.BLACK;
	boolean clicked;
	boolean expert_mode;
	Point actual_point;

	public enum MenuState {
		Idle, MenuOpened, ColorMenuOpened, ToolMenuOpened
	};

	MenuState state = MenuState.Idle;

	Controller controller = new Controller(this);
	View menu = new View("basic", this, controller);
	View toolMenu = new View("tool", this, controller);
	View colorMenu = new View("color", this, controller);

	public Canvas(Vector<ColoredShape> shapes) {
		actual_point = new Point();
		this.shapes = shapes;
		this.c = Color.BLACK;
		this.clicked = false;
		setListener();
	}
	
	public void setModeExpert(boolean em) {
		this.expert_mode = em;
	}
	
	public boolean getClicked() {
		return this.clicked;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public Vector<ColoredShape> getShapes() {
		return shapes;
	}
	
	public Point getPoint() {
		return actual_point;
	}
	
	public void setPoint(Point p) {
		this.actual_point = p;
	}

	private void setListener() {

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle && !expert_mode) {
					clicked = true;
					state = MenuState.MenuOpened;
					menu.setOldPoint(e.getPoint());
					menu.setPoint(e.getPoint());
					if(!expert_mode) {
						menu.printmenu();
					}
				}
				repaint();
			}

			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle && expert_mode) {
					clicked = true;
					state = MenuState.MenuOpened;
					menu.setOldPoint(e.getPoint());
					menu.setPoint(e.getPoint());
				}
				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3 && expert_mode) {
					clicked = false;
					if(expert_mode) {
						state = MenuState.Idle;
					}
				}
				repaint();
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent me) {
				setPoint(me.getPoint());
				/*if(expert_mode) {
					controller.handleMoved(me);
				}*/
				repaint();
			}

			public void mouseDragged(MouseEvent me) {
				if(SwingUtilities.isRightMouseButton(me) && expert_mode) {
					menu.setOldPoint(me.getPoint());
					colorMenu.setOldPoint(menu.getOldPoint());
					toolMenu.setOldPoint(menu.getOldPoint());
					controller.handleDragged(me);
				}
				repaint();
			}
			
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (ColoredShape shape : shapes) {

			g2.setColor(shape.getColor());
			g2.draw(shape.getShape());
		}

		switch (state) {
		case MenuOpened:
			menu.paintComponent(g2, expert_mode);
			break;
		case ToolMenuOpened:
			toolMenu.paintComponent(g2, expert_mode);
			break;
		case ColorMenuOpened:
			colorMenu.paintComponent(g2, expert_mode);
			break;
		default:
			break;
		}
	}

}
