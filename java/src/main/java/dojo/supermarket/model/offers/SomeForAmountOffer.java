package main.java.dojo.supermarket.model.offers;

public class SomeForAmountOffer extends Offer {
    private int number ;
    private double amount ;

    public SomeForAmountOffer(Product product, int number, double amount) {
        super(product);
        this.amount = amount;
        this.number = number;
    }

    Discount getDiscount(SupermarketCatalog catalog, double quantity){
        double unitPrice = catalog.getUnitPrice(this.product);
        int quantityAsInt = (int) quantity;
        Discount discount = null;
        if (quantityAsInt >= this.number) {
            double total = this.amount * (quantityAsInt / this.number) + quantityAsInt % this.number * unitPrice;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(this.product, this.number + " for " + this.amount, -discountN);
        }
        return discount;
    }
}
