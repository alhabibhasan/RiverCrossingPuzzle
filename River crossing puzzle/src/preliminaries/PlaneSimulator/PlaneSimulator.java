package preliminaries.PlaneSimulator;

/**
 * This class is used to store the values from the GUI
 * so that it is not stored in the GUI components themselves and
 * in doing so we follow MVC. This class acts as the model.
 */

public class PlaneSimulator {
	int x = 5, y = 0,speed = 0, elevation = 0;
	boolean reset;

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}


	
}
