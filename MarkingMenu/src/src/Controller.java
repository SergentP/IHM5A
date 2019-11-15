package src;

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
					canvas.toolMenu.setOldPoint(canvas.menu.getOldPoint());
					canvas.toolMenu.setPoint(me.getPoint());
				}
				else if (mI.getName().equals("Colors")) {
					canvas.state = MenuState.ColorMenuOpened;
					canvas.colorMenu.setOldPoint(canvas.menu.getOldPoint());
					canvas.colorMenu.setPoint(me.getPoint());
				}
			}
			break;
		case ColorMenuOpened:
			if (canvas.colorMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.colorMenu.contain(me.getPoint());
				canvas.clicked = false;
				mI.tool.execute();
				canvas.state = MenuState.Idle;
				System.out.println("color " + mI.getName() + " selected");
			}
			break;
		case ToolMenuOpened:
			if (canvas.toolMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.toolMenu.contain(me.getPoint());
				canvas.clicked = false;
				mI.tool.execute();
				canvas.state = MenuState.Idle;
			}
			break;
		default:
			break;
		}
		canvas.repaint();
	}

	public void handleDragged(MouseEvent me) {
		switch (canvas.state) {
		case MenuOpened:
			if (canvas.menu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.menu.contain(me.getPoint());
				if (mI.getName().equals("Tools")) {
					canvas.state = MenuState.ToolMenuOpened;
					canvas.toolMenu.setOldPoint(canvas.menu.getOldPoint());
					canvas.toolMenu.setPoint(me.getPoint());
				}
				else if (mI.getName().equals("Colors")) {
					canvas.state = MenuState.ColorMenuOpened;
					canvas.colorMenu.setOldPoint(canvas.menu.getOldPoint());
					canvas.colorMenu.setPoint(me.getPoint());
				}
			}
			break;
		case ColorMenuOpened:
			if (canvas.colorMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.colorMenu.contain(me.getPoint());
				mI.tool.execute();
				canvas.state = MenuState.Idle;
				System.out.println("color " + mI.getName() + " selected");
			}
			break;
		case ToolMenuOpened:
			if (canvas.toolMenu.contain(me.getPoint()) != null) {
				MenuItem mI = canvas.toolMenu.contain(me.getPoint());
				mI.tool.execute();
				canvas.state = MenuState.Idle;
			}
			break;
		default:
			break;
		}
		canvas.repaint();
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
			if (canvas.colorMenu.isPresent(button.getName())) {
				button.tool.execute();
				canvas.colorMenu.clearmenu();
				canvas.state = MenuState.Idle;
				canvas.clicked = false;
				System.out.println("color " + button.getName() + " selected");
			}
			break;
		case ToolMenuOpened:
			if (canvas.toolMenu.isPresent(button.getName())) {
				button.tool.execute();
				canvas.toolMenu.clearmenu();
				canvas.state = MenuState.Idle;
				canvas.clicked = false;
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
