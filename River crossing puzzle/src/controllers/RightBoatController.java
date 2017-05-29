package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Boat;
import views.GUI;
/**
 * The left button controller for the boat.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class RightBoatController implements ActionListener {

	private Boat boatToControl;

	public RightBoatController(Boat boatToControl) {
		this.boatToControl = boatToControl;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Boat.checkContainsFarmer()) {
			// ensuring that the boat doesnt go beyond the right edge of the river.
			if (boatToControl.getX() + 1000 > 1000) {
			boatToControl.setX(900);
			GUI.drawRiver(boatToControl);
		} else {
			boatToControl.setX(boatToControl.getX() + 900);

		}

		if (boatToControl.getX() >= 900) {
			boatToControl.setIsAtEnd(false);
			boatToControl.setIsAtStart(true);
			GUI.drawRiver(boatToControl);
		}

		System.out.println("End: " + Boat.isAtEnd());
		System.out.println("Start: " + Boat.isAtStart());

		}
		
	}

}
