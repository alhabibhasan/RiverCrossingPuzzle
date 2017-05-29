package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Boat;
import models.Piece;
import views.GUI;
/**
 * The right button controller for the boat.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class RightPieceController implements ActionListener {

	private Piece pieceToControl;
	private Boat boatToUse;

	public RightPieceController(Piece pieceToControl, Boat boatToUse) {
		this.pieceToControl = pieceToControl;
		this.boatToUse = boatToUse;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Boat.isAtStart() && boatToUse.containsPassenger(pieceToControl)) {
			pieceToControl.addToStartArray(pieceToControl);
			boatToUse.removePassenger(pieceToControl);
			GUI.drawRiver(boatToUse);
		}

		if (Boat.isAtEnd() && pieceToControl.endContainsInList(pieceToControl)) {
			if (boatToUse.getPassengers().size() <= 1) {
				boatToUse.addPassenger(pieceToControl);
				pieceToControl.removeFromEndArray(pieceToControl);
				GUI.removeFromEndPanel(pieceToControl.getImage());
			}
		}

	}

}
