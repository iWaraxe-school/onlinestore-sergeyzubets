package by.issoft.domain;

import by.issoft.domain.utilities.PresentProductsAsTable;
import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;

import java.text.DecimalFormat;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.ProductConstants.PriceConstants.*;

public class Product {
    private ProductName name;
    private Rate rate;
    private Price price;

    public Product(ProductName name, Rate rate, Price price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
        return this.name.getProductName();
    }

    public void setName(ProductName name) {
        this.name = ProductName.of(name.toString());
    }

    public int getRate() {
        return this.rate.getValue();
    }

    public void setRate(Rate rate) {
        this.rate = Rate.of(rate.getValue());
    }

    public int getPrice() {
        return price.getValue();
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: " + getName()
                + " | Rate: " + this.getRate()
                + " | Price: " + new DecimalFormat(PRICE_PRINT_PATTERN).format(this.getPrice());
    }

    public String toStringAsTable(List<Product> listOfProducts) {
        return new PresentProductsAsTable().getProductsAsTable(listOfProducts);
    }

}
