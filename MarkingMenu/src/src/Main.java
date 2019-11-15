package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class Main extends JFrame {

	Vector<ColoredShape> shapes = new Vector<ColoredShape>();
	Boolean expert_mode = false;
	Canvas can;
	
	public Main(String title) {

		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);

		can = new Canvas(shapes);
		
		final JButton red = new JButton();
		red.setBackground(Color.RED);
		red.setPreferredSize(new Dimension(20,20));
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				can.setColor(Color.RED);
				System.out.println("color Red selected");
			}
		});

		final JButton black = new JButton();
		black.setBackground(Color.BLACK);
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				can.setColor(Color.BLACK);
				System.out.println("color Black selected");
			}
		});
		
		final JButton expert = new JButton("Mode Expert");
		expert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!can.getClicked()) {
					expert_mode = !expert_mode; 
					can.setModeExpert(expert_mode);
					if(expert_mode) {
						expert.setBackground(Color.GREEN);
					} else {
						expert.setBackground(Color.WHITE);
					}
				}
			}
		});
		
		add(new JToolBar() {
			{
				add(expert);

			}
		}, BorderLayout.NORTH);

		add(new JToolBar() {
			{
				add(black);
				add(red);

			}
		}, BorderLayout.SOUTH);

		/*add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);*/
		
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
