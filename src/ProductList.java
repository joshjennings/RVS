import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class defines what a list of products should look like: model, description, and price.
 * @author Josh Jennings
 */
public class ProductList {
    String model, description;
    Double priceList;

    public ProductList() {
        model = "";
        description = "";
        priceList = 0.0;
    }

    public ProductList(String model, String description, double priceList) {
        this.model = model;
        this.description = description;
        this.priceList = priceList;
    }

	public String getModel() {
        return this.model;
    }

    public String getDescription() {
        return this.description;
    }

	public Double getPriceList() {
		return this.priceList;
	}

    //public ObservableList<ProductList> loadData() {
		//ObservableList<ProductList> data = new FXCollections.observableArrayList();
    //}
}
