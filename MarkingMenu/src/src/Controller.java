package src;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

import src.Canvas.MenuState;

//import src.Canvas.MenuState;

public class Controller implements MouseListener, MouseMotionListener{

	Canvas canvas;

	public Controller(Canvas c) {
		canvas = c;
	}
	
//	public MenuState move(MenuState state, Point p) {
//		switch (state) {
//		case MenuOpened:
//			if(menu.items[0].contains(p)) {
//				System.out.println("Tools menu opened");
//				state = MenuState.ToolMenuOpened;
//				toolMenu.setPoint(p);
//				toolMenu.printmenu();
//				return state;
//			}
//		default:
//			break;
//		}
//		return state;
//	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
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
			
			System.out.println("color selected");
			break;
		case ToolMenuOpened:
			System.out.println("tool selected");
			break;
		default:
			break;
		}
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
