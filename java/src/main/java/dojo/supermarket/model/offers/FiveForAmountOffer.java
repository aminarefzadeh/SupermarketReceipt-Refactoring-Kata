package main.java.dojo.supermarket.model.offers;

public class FiveForAmountOffer extends Offer {
    private double amount ;

    public FiveForAmountOffer(Product product, double amount) {
        super(product);
        this.amount = amount;
    }

    Discount getDiscount(SupermarketCatalog catalog, double quantity){
        double unitPrice = catalog.getUnitPrice(this.product);
        int quantityAsInt = (int) quantity;
        Discount discount = null;
        int numberOfXs = quantityAsInt / 5;
        if (quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (this.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
            discount = new Discount(this.product, x + " for " + this.argument, -discountTotal);
        }
        return discount;
    }
}
