package preliminaries.PlaneSimulator;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the controller for the Horizontal slider.
 *
 */

public class HorizontalSliderListner implements ChangeListener {
	PlaneSimulator ps;

	public HorizontalSliderListner(PlaneSimulator ps) {
		this.ps = ps;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int sliderValue = source.getValue();
				ps.setX(sliderValue); // This value is stored outside of the GUI component.
		}
	}
	
	
	
}
