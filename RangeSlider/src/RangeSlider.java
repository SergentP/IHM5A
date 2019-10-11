import javax.swing.JSlider;;

public class RangeSlider extends JSlider {
	
	int min;
	int max;

	public RangeSlider(int min, int max) {
		super(min, max);
	}
	
	public void setLowerValue(int min) {
		this.min = min;
	}
	
	public void setUpperValue(int max) {
		this.max = max;
	}
	
	public int getLowerValue() {
		return this.min;
	}
	
	public int getUpperValue() {
		return this.max;
	}
	
}
