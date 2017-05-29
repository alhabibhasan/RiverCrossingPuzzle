package preliminaries.PlaneSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the controller for the Button.
 *
 */

public class ButtonListner implements ActionListener {
	PlaneSimulator ps;

	public ButtonListner(PlaneSimulator ps) {
		this.ps = ps;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Here we are following MVC by storing the information in another class
		 * and not in the GUI components. 
		 */
		ps.setX(5);
		ps.setY(0);
		ps.setSpeed(0);
		ps.setElevation(0);
		ps.setReset(true);
	}

}

