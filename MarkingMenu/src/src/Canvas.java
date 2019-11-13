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

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	Vector<ColoredShape> shapes;
	Color c;

	public enum MenuState {
		Idle, MenuOpened, ColorMenuOpened, ToolMenuOpened
	};

	MenuState state = MenuState.Idle;

	String labels[] = { "Tools", "Colors" };
	String tools[] = { "Pen", "Rect", "Ellipse" };
	String colors[] = {"Black", "White", "Green", "Blue", "Red", "Yellow"};
	Model model = new Model();
	Controller controller = new Controller(this);
	View menu = new View(2, labels, this, controller);
	View toolMenu = new View(3, tools, this, controller);
	View colorMenu = new View(colors.length, colors, this, controller);

	public Canvas() {
		this.shapes = new Vector<ColoredShape>();
		this.c = Color.BLACK;
		setListener();
	}

	private void setListener() {

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle) {
					state = MenuState.MenuOpened;
					menu.setPoint(e.getPoint());
					menu.printmenu();
				} else if (state != MenuState.Idle && toolMenu.items[0].contains(e.getPoint())){
					state = MenuState.Idle;
				}
				repaint();
			}

			public void mousePressed(MouseEvent e) {
				/*if (e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle) {
					state = MenuState.MenuOpened;
					view.setPoint(e.getPoint());
				}
				repaint();*/
			}

			public void mouseReleased(MouseEvent e) {
				/*state = MenuState.Idle;
				repaint();*/
			}

			public void mouseDragged(MouseEvent e) {
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent me) {
				repaint();
			}

			public void mouseDragged(MouseEvent me) {
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
			menu.paintComponent(g2);
			break;
		case ToolMenuOpened:
			toolMenu.paintComponent(g2);
		default:
			break;
		}
	}

}
