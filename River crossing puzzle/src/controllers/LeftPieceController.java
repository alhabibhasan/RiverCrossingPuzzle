package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import models.Boat;
import models.Piece;
import views.GUI;
/**
 * The left button controller for the pieces.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class LeftPieceController implements ActionListener {

	private Piece pieceToControl;
	private Boat boatToUse;

	public LeftPieceController(Piece pieceToControl, Boat boatToUse) {
		this.pieceToControl = pieceToControl;
		this.boatToUse = boatToUse;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (Boat.isAtStart() && pieceToControl.startContainsInList(pieceToControl)) {
			if (boatToUse.getPassengers().size() <= 1) {
				boatToUse.addPassenger(pieceToControl);
				pieceToControl.removeFromStartArray(pieceToControl);
				GUI.removeFromStartPanel(pieceToControl.getImage());

				GUI.drawRiver(boatToUse);

			}
		}

		if (Boat.isAtEnd() && boatToUse.containsPassenger(pieceToControl)) {
			pieceToControl.addToEndArray(pieceToControl);
			GUI.drawRiver(boatToUse);
			boatToUse.removePassenger(pieceToControl);

		}

	}

}
