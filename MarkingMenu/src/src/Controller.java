package src;

import java.awt.Point;

import src.Canvas.MenuState;

public class Controller {

	View menu;
	View toolMenu;

	public Controller(View menu, View toolMenu) {
		this.menu = menu;
		this.toolMenu = toolMenu;
	}
	
	public MenuState move(MenuState state, Point p) {
		switch (state) {
		case MenuOpened:
			if(menu.items[0].contains(p)) {
				System.out.println("Tools menu opened");
				state = MenuState.ToolMenuOpened;
				toolMenu.setPoint(p);
				return state;
			}
		default:
			break;
		}
		return state;
	}

}
