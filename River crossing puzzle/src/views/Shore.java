package views;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * This class extends the JPanel class and is used to draw the grass image onto the start and end sides.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class Shore extends JPanel {
     /**
	 * Default serialVersion ID.
	 */
	private static final long serialVersionUID = 1L;
	public Shore(GridLayout layout) {
		super(layout);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon grass = new ImageIcon("res\\grass.jpg");
		grass.paintIcon(this, g, 0, 0);
	}
}
