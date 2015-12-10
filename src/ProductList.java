import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;

/**
 * This class defines what a list of products should look like: model, description, and price.
 * @author Josh Jennings
 */
public class ProductList {
	String model, description;
	BigDecimal priceList;
	DecimalFormat priceListFormat;

	public ProductList() {
		model = "";
		description = "";
		priceList = new BigDecimal(0.0);
	}

	public ProductList(String model, String description, Double priceList) {
		this.model = model;
		this.description = description;
		this.priceList = new BigDecimal(priceList);
	}

	public String getModel() {

		return this.model;
	}

	public String getDescription() {
		return this.description;
	}

	public Currency getPriceList() {
		priceListFormat = new DecimalFormat(this.priceList.toString());
		return priceListFormat.getCurrency();
	}

	//public ObservableList<ProductList> loadData() {
		//ObservableList<ProductList> data = new FXCollections.observableArrayList();
	//}
}
