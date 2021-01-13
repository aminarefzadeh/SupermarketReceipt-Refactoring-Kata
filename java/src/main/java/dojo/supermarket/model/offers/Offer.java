package main.java.dojo.supermarket.model.offers;

public abstract class Offer {
    private final Product product;

    public Offer(Product product) {
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    abstract Discount getDiscount(SupermarketCatalog catalog, double quantity);

}

