package preliminaries.PlaneSimulator;

import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;

/**
 * This class is the driving class to run the program and
 * is also the view.
 */

public class MainInterface {
	public static void main(String[] args) throws InterruptedException {
		int secCount = 0, y = 0, elevation = 0;
		boolean planeInAir = false;

		PlaneSimulator ps = new PlaneSimulator();

		JFrame myFrame = new JFrame("Plane Simulator"); // creating a new frame
		myFrame.setLayout(new GridLayout(2, 1)); // setting the overall layout
													// to grid layout because
													// upper part of the layout
													// is the text area

		JTextArea textField = new JTextArea(); // make text area for upper part
												// of grid
		textField.setFont(new Font("Serif", Font.ITALIC, 20)); // setting the
																// font of the
																// text in the
																// text area
		JScrollPane scrollPaneText = new JScrollPane(textField); // adding the
																	// text area
																	// to the
																	// scroll
																	// pane so
																	// that it
																	// can be
																	// seen as
																	// loop is
																	// going to
																	// run
																	// infinitely
		myFrame.add(scrollPaneText);

		JPanel southPartOfFrame = new JPanel(); // a panel is created for the
												// south part of the plane so
												// that it can be set to border
												// layout and divided to three
												// parts
		southPartOfFrame.setLayout(new BorderLayout());

		JPanel jpNorth = new JPanel(); // the frame is divided into three parts
		JPanel jpCenter = new JPanel(); // north with the horizontal slider,
										// center with the vertical slider
		JPanel jpSouth = new JPanel(); // and south with the reset button

		JSlider horizontalSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5); // makes
																				// the
																				// horizontal
																				// slider
		horizontalSlider.setMajorTickSpacing(1);
		horizontalSlider.setPaintLabels(true);
		horizontalSlider.setPaintTicks(true);
		horizontalSlider.setPreferredSize(new Dimension(500, 50)); // dimensions
																	// for
																	// horizontal
																	// slider
		jpNorth.add(horizontalSlider);
		southPartOfFrame.add(jpNorth, BorderLayout.NORTH);
		horizontalSlider.addChangeListener(new HorizontalSliderListner(ps));

		JSlider verticalSlider = new JSlider(JSlider.VERTICAL, 0, 10, 0); // makes
																			// the
																			// vertical
																			// slider
		verticalSlider.setMajorTickSpacing(1);
		verticalSlider.setPaintLabels(true);
		verticalSlider.setPaintTicks(true);
		verticalSlider.setPreferredSize(new Dimension(50, 300)); // dimensions
																	// set for
																	// the
																	// vertical
																	// slider
		jpCenter.add(verticalSlider);
		southPartOfFrame.add(jpCenter, BorderLayout.CENTER);
		verticalSlider.addChangeListener(new VerticalSliderListner(ps)); // passing the planeSimulator variable into
																		 // the vertical slider listener class

		JButton resetButton = new JButton("Reset"); // makes the button for
													// reset
		resetButton.setPreferredSize(new Dimension(500, 20)); // dimensions set
																// for the reset
																// button
		jpSouth.add(resetButton);
		southPartOfFrame.add(jpSouth, BorderLayout.SOUTH);
		resetButton.addActionListener(new ButtonListner(ps));

		myFrame.add(southPartOfFrame);

		myFrame.pack();
		myFrame.setVisible(true);

		String concatText = "";

		while (y <= 100 && planeInAir == false) { // loop to output the
													// seconds along with
													// the changes made in
													// it with the sliders
													// and reset button
			secCount++;
			if (ps.getY() >= 50 && ps.getSpeed() == 10 && ps.getX() == 5) { // checks if throttle is not 0,
																			// speed is 10 and x is 5

				elevation = elevation + 1; // increment the value of
											// elevation and set it to ps
				ps.setElevation(elevation);

				if (elevation == 6) {
					planeInAir = true;
				}

			}

			if (ps.isReset() == true) {
				secCount = 1;
				y = 0;
				horizontalSlider.setValue(5);
				verticalSlider.setValue(0);
				ps.setReset(false);
				concatText = ""; // this part makes the concat text start from
									// the beginning because its initializing it
									// to blank
			} 

			y = y + ps.getSpeed(); // increment the value of y by 10 and set
									// it to
									// ps
			ps.setY(y);
			
			concatText += "Seconds: " + secCount + "\nX: " + ps.getX() + " Y: " + ps.getY() + " Speed: " + ps.getSpeed() + " Elevation: " + ps.getElevation() + "\n";
			textField.setText(concatText);
			TimeUnit.SECONDS.sleep(1);
		}

		if (planeInAir == true) {
			textField.setText(concatText + "\nPlane in the air");
			//textField.setText(concatText + "");
			//textField.setText(concatText + "");
		} else {
			textField.setText(concatText + "\nPlane could not take off");
			//textField.setText("");
		}

	}

}
