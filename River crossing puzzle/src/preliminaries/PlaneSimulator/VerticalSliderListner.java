package preliminaries.PlaneSimulator;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the controller for the VerticalSlider.
 */

public class VerticalSliderListner implements ChangeListener {
	PlaneSimulator ps;

	public VerticalSliderListner(PlaneSimulator ps) {
		this.ps = ps;
	}

	@Override
	public void stateChanged(ChangeEvent e) {	//sets the speed and the value of y as the value obtained by adjusting the slider
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int sliderValue = source.getValue();
				ps.setSpeed(sliderValue); // Store the value of the speed outside of the GUI component in the PlaneSimulator class.
		}
	}
}