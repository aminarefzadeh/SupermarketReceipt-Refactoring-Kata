package main.java.dojo.supermarket.model.offers;

public class TenPercentDiscountOffer extends Offer {

    public TenPercentDiscountOffer(Product product) {
        super(product);
    }

    Discount getDiscount(SupermarketCatalog catalog, double quantity){
        double unitPrice = catalog.getUnitPrice(this.product);
        discount = new Discount(this.product, this.argument + "% off", -quantity * unitPrice * this.argument / 100.0);
        return discount;
    }
}
