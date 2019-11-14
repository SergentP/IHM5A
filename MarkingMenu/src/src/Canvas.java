package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	Vector<ColoredShape> shapes;
	Color c;
	boolean clicked;
	boolean expert_mode;

	public enum MenuState {
		Idle, MenuOpened, ColorMenuOpened, ToolMenuOpened
	};

	MenuState state = MenuState.Idle;

	String labels[] = { "Tools", "Colors" };
	String tools[] = { "Pen", "Rect", "Ellipse" };
	String colors[] = {"Black", "White", "Green", "Blue", "Red", "Yellow", "Pink", "Pink", "Pink"};
	Model model = new Model();
	Controller controller = new Controller(this);
	View menu = new View(2, labels, this, controller);
	View toolMenu = new View(3, tools, this, controller);
	View colorMenu = new View(colors.length, colors, this, controller);

	public Canvas(Vector<ColoredShape> shapes) {
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

	private void setListener() {

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				clicked = true;
				if (e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle) {
					state = MenuState.MenuOpened;
					menu.setPoint(e.getPoint());
					menu.printmenu(expert_mode);
				}
				repaint();
			}

			public void mousePressed(MouseEvent e) {
				/*if (e.getButton() == MouseEvent.BUTTON3 && state == MenuState.Idle && !clicked) {
					state = MenuState.MenuOpened;
					menu.setPoint(e.getPoint());
					menu.printmenu();
				}
				repaint();*/
			}

			public void mouseReleased(MouseEvent e) {
				/*if (e.getButton() == MouseEvent.BUTTON3 && state != MenuState.Idle && !clicked) {
					state = MenuState.Idle;
					menu.clearmenu();
				}
				repaint();*/
			}

			public void mouseDragged(MouseEvent e) {
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent me) {
				if(expert_mode) {
					//System.out.println("Here (" + me.getPoint().x + "," + me.getPoint().y + ")");
					controller.handleMoved(me);
				}
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
