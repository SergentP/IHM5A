package src;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class Main extends JFrame {

	Canvas can;

	public Main(String title) {

		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		can = new Canvas();
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
