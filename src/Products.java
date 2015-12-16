/**
 * This class is initiated during startup and pre-loads the standard products into a list.
 *
 * @author Josh Jennings
 */
public class Products {

	String model;
	int diameter;
	char orientation;

	/**
	 * Standard Products() constructor.
	 */
	public Products() {

	}

	public Products(String model, int diameter, char orientation) {
		this.model = model;
		this.diameter = diameter;
		this.orientation = orientation;
	}
}
