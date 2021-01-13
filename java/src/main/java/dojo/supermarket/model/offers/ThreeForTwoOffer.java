package main.java.dojo.supermarket.model.offers;

public class ThreeForTwoOffer extends Offer {

    public ThreeForTwoOffer(Product product) {
        super(product);
    }

    Discount getDiscount(SupermarketCatalog catalog, double quantity){
        double unitPrice = catalog.getUnitPrice(this.product);
        int quantityAsInt = (int) quantity;
        Discount discount = null;
        int numberOfXs = quantityAsInt / 3;
        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(this.product, "3 for 2", -discountAmount);
        }
        return discount;
    }

}
