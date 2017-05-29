package views;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import models.Boat;
import models.Piece;
/**
 * A class which extends the JPanel allowing us to draw a custom panel with our own images and background.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class River extends JPanel {
	/**
	 * Default serialVersion ID.
	 */
	private static final long serialVersionUID = 1L;
	private Boat boat;

	public River(Boat boat) {
		this.boat = boat;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon river = new ImageIcon("res\\river.jpg");
		river.paintIcon(this, g, 0, 0);

		ImageIcon boatImage = boat.getBoatImage();

		boatImage.paintIcon(this, g, boat.getX(), 300);

		int n = 0;

		for (Piece passenger : boat.getPassengers()) {
			ImageIcon passengerIcon = passenger.getImageIcon();
			passengerIcon.paintIcon(this, g, boat.getX() + n, 300);
			n += 70;
		}

	}
}
