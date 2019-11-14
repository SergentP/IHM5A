package src;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import src.Canvas.MenuState;

public class Controller implements MouseListener, MouseMotionListener{

	Canvas canvas;

	public Controller(Canvas c) {
		canvas = c;
	}
	
	public void handleMoved(MouseEvent me) {
		switch (canvas.state) {
		case MenuOpened:
			if (canvas.menu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.menu.contain(me.getPoint());
				if (mI.getName().equals("Tools")) {
					canvas.state = MenuState.ToolMenuOpened;
					canvas.toolMenu.setPoint(me.getPoint());
					canvas.menu.clearmenu();
					canvas.toolMenu.printmenu();
				}
				else if (mI.getName().equals("Colors")) {
					canvas.state = MenuState.ColorMenuOpened;
					canvas.colorMenu.setPoint(me.getPoint());
					canvas.menu.clearmenu();
					canvas.colorMenu.printmenu();
				}
			}
			break;
		case ColorMenuOpened:
			if (canvas.colorMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.colorMenu.contain(me.getPoint());
				canvas.clicked = false;
				canvas.colorMenu.clearmenu();
				canvas.state = MenuState.Idle;
				System.out.println("color " + mI.getName() + " selected");
			}
			break;
		case ToolMenuOpened:
			if (canvas.toolMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.toolMenu.contain(me.getPoint());
				canvas.clicked = false;
				canvas.toolMenu.clearmenu();
				canvas.state = MenuState.Idle;
				System.out.println("tool " + mI.getName() + " selected");
			}
			break;
		default:
			break;
		}
		canvas.repaint();
	}

	public void handleDragged(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		MenuItem button = (MenuItem) e.getSource();
		switch (canvas.state) {
		case MenuOpened:
			if (button.getName().equals("Tools")) {
				canvas.state = MenuState.ToolMenuOpened;
				canvas.toolMenu.setPoint(button.getLocation());
				canvas.menu.clearmenu();
				canvas.toolMenu.printmenu();
			} else if (button.getName().equals("Colors")) {
				canvas.state = MenuState.ColorMenuOpened;
				canvas.colorMenu.setPoint(button.getLocation());
				canvas.menu.clearmenu();
				canvas.colorMenu.printmenu();
			}
			break;
		case ColorMenuOpened:
			break;
		case ToolMenuOpened:
			if (button.getName().toLowerCase().equals("pen")) {
				canvas.clicked = false;
				canvas.toolMenu.clearmenu();
				canvas.state = MenuState.Idle;
				System.out.println("tool selected");
			}
			break;
		default:
			break;
		}
		canvas.repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
