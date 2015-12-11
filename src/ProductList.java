import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.Josh.Message;

/**
 * This class defines what a list of products should look like: model, description, and price.
 * @author Josh Jennings
 */
public class ProductList {
	String model, description;
	BigDecimal priceList;
	DecimalFormat priceListFormat;

	public ProductList() {
		Message.consoleMessage("ProductList instance instantiated. Empty.");
		model = "";
		description = "";
		priceList = new BigDecimal(0.0);
	}

	public ProductList(String model, String description, Double priceList) {
		Message.consoleMessage("ProductList instance instantiated. Model:" + model + " | Desc: " + description +  " | Price: " + priceList);
		this.model = model;
		this.description = description;
		this.priceList = new BigDecimal(priceList);
	}

	public String getModel() {
		Message.consoleMessage("Collecting product model: " + this.model);
		return this.model;
	}

	public String getDescription() {
		Message.consoleMessage("Collecting product description: " + this.description);
		return this.description;
	}

	public String getPriceList() {
		//Set locale and number formatter object
		Locale currentLocale = new Locale("en","US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
		//
		Message.consoleMessage("Collecting product price: " + currencyFormatter.format(this.priceList));
		return currencyFormatter.format(this.priceList);
	}

	//public ObservableList<ProductList> loadData() {
		//ObservableList<ProductList> data = new FXCollections.observableArrayList();
	//}
}
