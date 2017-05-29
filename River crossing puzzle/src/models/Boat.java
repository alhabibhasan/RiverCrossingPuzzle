package models;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * This class is used to model the boat which would be used in the game. 
 * @author Muhammed Hasan, Samuel Singh.
 *
 */
public class Boat extends Observable {
	private ImageIcon imageOfBoat;
	private int x, movesMade;
	private Piece piece;

	private static ArrayList<Piece> passengers;
	private static boolean isAtEnd, isAtStart;

	public Boat(Piece piece) {
		setX(900); // we use the setX method so all changes are validated and checked.
		isAtStart = true;
		passengers = new ArrayList<Piece>();
		this.imageOfBoat = new ImageIcon("res/boat.png");
		this.piece = piece;
		this.movesMade = 0;

	}

	/**
	 * 
	 * @param passenger The passenger to add
	 * @return True or false depending on whether the passenger is null or an actual piece which can be added in.
	 */
	public boolean addPassenger(Piece passenger) {
		if (passenger != null) {
				passengers.add(passenger);
				return true;
			}
		return false;
	}

	/**
	 * Outputs all passengers of the boat to the console.
	 */
	public void printPassengers() {
		for (Piece passenger : passengers) {
			System.out.println(passenger.toString());
		}
	}

	/**
	 * 
	 * @param passenger Removes this passenger from the arraylist so long as it exists and is not null.
	 */
	public void removePassenger(Piece passenger) {
		
		if (passenger != null) {
			passengers.remove(passenger);
		}
	}
	/**
	 * Checks whether a given piece is a passenger within the passengers array list.
	 * @param checkContains
	 * @return True or false depending on whether the piece is contained.
	 */
	public boolean containsPassenger(Piece checkContains) {
		if (checkContains != null) {
			return passengers.contains(checkContains);
		}

		return false;
	}
	/**
	 * 
	 * @return The array list of passengers.
	 */
	public ArrayList<Piece> getPassengers() {
		return passengers;
	}

	/**
	 * 
	 * @return Boolean value indicating whether the boat is adjacent to the end side or not.
	 */
	public static boolean isAtEnd() {
		return isAtEnd;
	}

	/**
	 * 
	 * @return Boolean value indicating whether the boat is adjacent to the start side or not. 
	 */
	public static boolean isAtStart() {
		return isAtStart;

	}

	/**
	 * 
	 * @param isAtStart Boolean value to set the isAtStart field to.
	 */
	public void setIsAtStart(boolean isAtStart) {
		Boat.isAtStart = isAtStart;
		
		setChanged();
		notifyObservers(this);
		piece.boatMoved();
		
	}


	/**
	 * 
	 * @param isAtStart Boolean value to set the isAtStart field to.
	 */
	public void setIsAtEnd(boolean isAtEnd) {
		Boat.isAtEnd = isAtEnd;
		setChanged();
		notifyObservers(this);
		piece.boatMoved();
	
	
	}

	/**
	 * 
	 * @return An object of type ImageIcon containing the image of the boat.
	 */
	public ImageIcon getBoatImage() {
		return imageOfBoat;
	}

	/**
	 * 
	 * @return Current x position of the boat.
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * This method takes an integer value, which it validates and assigns as the x coordinate of the boat. It then informs the observers.
	 * @param x
	 * @return true or false depending on whether the change was successful. True = success.
	 */
	public boolean setX(int x) {
		if (x > -1) {
			this.x = x;
			this.movesMade--;
			setChanged();
			notifyObservers(this);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return True or false, depending on whether the boat contains the farmer or not.
	 */
	public static boolean checkContainsFarmer() {
		for (Piece p : passengers) {
			if (p.getName().equalsIgnoreCase("farmer")) {
				System.out.println("Boat Contains farmer");
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return The total number of moves made * -1.
	 */
	public String getMovesMade() {
		return String.valueOf(movesMade);
	}
}
