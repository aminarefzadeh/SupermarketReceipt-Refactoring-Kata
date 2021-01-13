package main.java.dojo.supermarket.model.offers;

public class TwoForAmountOffer extends Offer {
    private double amount ;

    public TwoForAmountOffer(Product product, double amount) {
        super(product);
        this.amount = amount;
    }

    Discount getDiscount(SupermarketCatalog catalog, double quantity){
        double unitPrice = catalog.getUnitPrice(this.product);
        int quantityAsInt = (int) quantity;
        Discount discount = null;
        x = 2;
        if (quantityAsInt >= 2) {
            double total = this.argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(this.product, "2 for " + this.argument, -discountN);
        }
        return discount;
    }
}
