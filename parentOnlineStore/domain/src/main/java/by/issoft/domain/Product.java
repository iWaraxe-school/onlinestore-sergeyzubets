package by.issoft.domain;

import by.issoft.domain.utilities.Price;
import by.issoft.domain.utilities.ProductName;
import by.issoft.domain.utilities.Rate;
import by.issoft.store.utilities.StoreConstants;
import java.text.DecimalFormat;
import java.util.Currency;

public class Product {
    private static final String pricePrintPattern = StoreConstants.ProductConstants.PriceConstants.PRICE_PRINT_PATTERN;
    private final String categoryName;
    private final Currency productPriceCurrency;
    private ProductName name;
    private Rate rate;
    private Price price;

    public Product (ProductName name, Rate rate, Price price, String categoryName, Currency productPriceCurrency) {
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.categoryName = categoryName;
        this.productPriceCurrency = productPriceCurrency;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Currency getProductPriceCurrency() {
        return productPriceCurrency;
    }

    public ProductName getName() {
        return this.name;
    }

    public void setName(ProductName name) {
        this.name = ProductName.of(name.toString());
    }

    public Rate getRate() {
        return this.rate;
    }

    public void setRate(Rate rate) {
        this.rate = Rate.of(rate.getValue());
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " Category: " + this.getCategoryName()
                + " | Product: " + getName()
                + " | Rate: " + this.getRate().getValue()
                + " | Price, " + this.getProductPriceCurrency()
                + ": " + new DecimalFormat(pricePrintPattern).format(this.getPrice().getValue());
    }

}