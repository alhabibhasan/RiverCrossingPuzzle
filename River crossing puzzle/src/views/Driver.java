package views;

import javax.swing.JLabel;

import controllers.LeftBoatController;
import controllers.LeftPieceController;
import controllers.RightBoatController;
import controllers.RightPieceController;
import models.Boat;
import models.Piece;
/**
 * The class contains the Main method and is used as a starting point for the program
 * @author ASUS
 *
 */
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Piece farmer = new Piece(new JLabel(), "Farmer", null, "res/farmer.png");
		Piece bean = new Piece(new JLabel(), "Bean", null, "res/bean.png");
		Piece duck = new Piece(new JLabel(), "Duck", bean, "res/duck.png");
		Piece fox = new Piece(new JLabel(), "Duck", duck, "res/fox.png");

		Piece blankStart = new Piece(new JLabel(), "do not delete", null, "res/blank.png");
		Piece blankEnd = new Piece(new JLabel(), "do not delete", null, "res/blank.png");

		Boat boat = new Boat(farmer);

		GUI g = new GUI(bean, duck, fox, farmer, boat);
		g.createGUI();
		g.setVisible(true);

		farmer.addObserver(g);
		bean.addObserver(g);
		duck.addObserver(g);
		fox.addObserver(g);
		boat.addObserver(g);

		farmer.addToStartArray(blankStart);
		farmer.addToEndArray(blankEnd);

		farmer.addToStartArray(farmer);
		farmer.addToStartArray(bean);
		farmer.addToStartArray(duck);
		farmer.addToStartArray(fox);

	}

}
