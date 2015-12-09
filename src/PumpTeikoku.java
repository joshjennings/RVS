/**
 * This class defines what a Teikoku pump is as a sub-class of Pump.
 * @author Josh Jennings
 */
public class PumpTeikoku extends Pump {

	private String modelTeikoku;

	public PumpTeikoku() {
		super();
		this.modelTeikoku = "";
	}

	public PumpTeikoku(String modelRVS, Double price, String modelTeikoku) {
		super(modelRVS, price);
		this.modelTeikoku = modelTeikoku;
	}
}
