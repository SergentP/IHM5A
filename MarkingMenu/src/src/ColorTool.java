package src;

import java.awt.Color;

public class ColorTool extends Tool {
	
	private Color color;
	Canvas can;

	public ColorTool(String name, Color c, Canvas can) {
		super(name);
		this.color = c;
		this.can = can;	
	}

	@Override
	public void execute() {
		can.setColor(color);
	}
	
	public Color getColor() {
		return color;
	}

}
