package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Boat;
import models.Piece;
import views.GUI;
/**
 * The left button controller for the boat.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class LeftBoatController implements ActionListener {

	private Boat boatToControl;

	public LeftBoatController(Boat boatToControl) {
		this.boatToControl = boatToControl;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Boat.checkContainsFarmer()) {
			if (boatToControl.getX() - 1000 < 0) {
				// if the boat is already at the left, edge the position will be set as 0 to ensure that it doesnt beyond the left edge.
				boatToControl.setX(0);
				GUI.drawRiver(boatToControl);

			} else {
				// other wise the boat is moved left by 1000 units.
				boatToControl.setX(boatToControl.getX() - 1000);
				GUI.drawRiver(boatToControl);
			}

			if (boatToControl.getX() <= 100) {
				// if the boat's x value is less than 100, it is considered to be at the end side.
				boatToControl.setIsAtEnd(true);
				boatToControl.setIsAtStart(false);
				GUI.drawRiver(boatToControl);
			}

			System.out.println("End: " + Boat.isAtEnd());
			System.out.println("Start: " + Boat.isAtStart());
		}
		

	}

}
