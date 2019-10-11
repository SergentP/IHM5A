import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {

	private JLabel label = new JLabel("Value : 30");

	public Window() {
		super();
		build();
	}

	private void build() {

		setTitle("RangeSlider_TP");
		setSize(320, 240);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSlider slide = new JSlider();

		slide.setMaximum(200);
		slide.setMinimum(0);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);

		slide.setMinorTickSpacing(10);

		slide.setMajorTickSpacing(20);

		slide.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent event) {

				label.setText("Valeur actuelle : " + ((JSlider) event.getSource()).getValue());

			}

		});

		this.getContentPane().add(slide, BorderLayout.CENTER);

		this.getContentPane().add(label, BorderLayout.SOUTH);

	}
}