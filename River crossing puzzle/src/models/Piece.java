package models;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import views.GUI;
/**
 * This class is used to model a piece within the game.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class Piece extends Observable {

	private JLabel piece;
	private String name;
	private Piece eats;
	private String imageFilePath;

	
	private static ArrayList<Piece> startSide;
	private static ArrayList<Piece> endSide;

	public Piece(JLabel piece, String name, Piece eats, String imageFilePath) {

		endSide = new ArrayList<Piece>();
		startSide = new ArrayList<Piece>();

		this.piece = piece;
		this.name = name;
		this.eats = eats;
		this.imageFilePath = imageFilePath;

		createImage();

	}

	/**
	 * Check if the start side contains a given piece.
	 * @param toCheck The piece to check.
	 * @return true or false is returned depending on whether the piece is contained in the start side.
	 */
	public boolean startContainsInList(Piece toCheck) {
		if (toCheck == null)
			return false;
		return startSide.contains(toCheck);
	}

	/**
	 * Adding to the start side.
	 * @param toAdd The value to add
	 */
	public void addToStartArray(Piece toAdd) {
		if (toAdd != null) {
			startSide.add(toAdd);
			if (checkForFailState()) {
				returnFail();
			}
			setChanged();
			notifyObservers(this);
		}
	}

	/**
	 * Removing from the start side.
	 * @param toRemove The piece to remove from the start side.
	 */
	public void removeFromStartArray(Piece toRemove) {
		if (toRemove != null) {
			startSide.remove(toRemove);
			if (checkForFailState()) {
				returnFail();
			}
			setChanged();
			notifyObservers(this);
		}

	}

	/**
	 * Checking that the end side contains the given piece in the end side.
	 * @param toCheck The piece to check.
	 * @return True or false depending on whether the piece is contained in the start and end side.
	 */
	public boolean endContainsInList(Piece toCheck) {
		if (toCheck == null)
			return false;
		return endSide.contains(toCheck);
	}

	/**
	 * The adding to the end array
	 * @param toAdd The piece to add to the endside.
	 */
	public void addToEndArray(Piece toAdd) {
		if (toAdd != null) {
			endSide.add(toAdd);
			if (checkForFailState()) {
				returnFail();
			}
			setChanged();
			notifyObservers(this);
		}
	}

	/**
	 * Removing from the end side.
	 * @param toRemove the piece to be removed from the endside.
	 */
	public void removeFromEndArray(Piece toRemove) {
		if (toRemove != null) {
			endSide.remove(toRemove);
			if (checkForFailState()) {
				returnFail();
			}
			setChanged();
			notifyObservers(this);
		}
	}

	/**
	 * This method is called by the boat every time the boat is moved in order to check for a fail state.
	 */
	public void boatMoved() {
		checkForFailState();
	}
	
	private void returnFail() {
		// this method informs all observers that a fial state has been reached by sending them a null value.
		setChanged();
		notifyObservers(null);
	}
	private boolean checkForFailState() {
		// check start side

		for (Piece startFirst : startSide) {
			for (Piece startSecond : startSide) {
				if (startFirst.getEats() != null) {
					if (startFirst.getEats().equals(startSecond) && !checkContainsFarmer(0) && !Boat.isAtStart()) {
						// if the startside contains 2 pieces who eat each other, a fail state will be signalled also, the farmer must be absent 
						// from the start or end side and the farmer must not be on the boat adjacent to the given side.
						returnFail();
					}
				}
			}
		}

		// check end side
		for (Piece endFirst : endSide) {
			for (Piece endSecond : endSide) {
				if (endFirst.getEats() != null) {
					if (endFirst.getEats().equals(endSecond) && !checkContainsFarmer(1) && !Boat.isAtEnd())
						returnFail();
				}
			}
		}

		return false;
	}

	/**
	 * Checks whether the specified side contains the farmer or not.
	 * 
	 * @param side
	 *            0 checks the start side. 1 check the end side.
	 * @return true or false depending on if the farmer is there or not.
	 */
	private boolean checkContainsFarmer(int side) {
		if (side == 0) {
			for (Piece p : startSide) {
				if (p.toString().contains("Farm") || p.toString().contains("farm") && Boat.isAtStart() && Boat.checkContainsFarmer()) {
					System.out.println("Startside contains farmer");
					return true;
				} 
			}
		}

		if (side == 1) {
			for (Piece p : endSide) {
				if (p.toString().contains("Farm") || p.toString().contains("farm") && Boat.isAtEnd() && Boat.checkContainsFarmer()) {
					System.out.println("Endside contains farmer");
					return true;
				} 
			}
		}

		return false;
	}

	/**
	 * Set the image icon of the piece label to the image specified in the imageFilePath.
	 */
	public void createImage() {
		this.piece.setIcon(new ImageIcon(imageFilePath));
	}

	/**
	 * 
	 * @return Returns the arraylist representing the startside
	 */
	public ArrayList<Piece> getStartSide() {
		return Piece.startSide;
	}

	/**
	 * 
	 * @return Returns the arraylist representing the endside.
	 */
	public ArrayList<Piece> getEndSide() {
		return Piece.endSide;
	}

	/**
	 * 
	 * @return The piece that the current piece eats.
	 */
	public Piece getEats() {
		return this.eats;
	}
	
	/**
	 * Outputs the contents of the start side arraylist to the console.
	 */
	public void printStart() {
		for (Piece p : startSide) {
			System.out.println(p.toString());
		}
	}

	/**
	 * 
	 * @return The JLabel containing the image of the piece.
	 */
	public JLabel getImage() {
		createImage();
		return piece;
	}

	/**
	 * 
	 * @return The name of the piece
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Piece [name=" + name + ", eats=" + eats + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eats == null) ? 0 : eats.hashCode());
		result = prime * result + ((imageFilePath == null) ? 0 : imageFilePath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (eats == null) {
			if (other.eats != null)
				return false;
		} else if (!eats.equals(other.eats))
			return false;
		if (imageFilePath == null) {
			if (other.imageFilePath != null)
				return false;
		} else if (!imageFilePath.equals(other.imageFilePath))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		return true;
	}

	/**
	 * 
	 * @return The image specified in the imageFilePath in the form of an ImageIcon.
	 */
	public ImageIcon getImageIcon() {
		// TODO Auto-generated method stub
		return new ImageIcon(imageFilePath);
	}

}
