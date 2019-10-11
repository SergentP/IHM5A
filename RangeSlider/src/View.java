import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {

	private static final int width = 10;
	
	public View() {

		setTitle("Range Slider TP");
		setSize(300, 200);
		setResizable(false);

		JPanel pan = new JPanel();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		View view = new View();
	}
}