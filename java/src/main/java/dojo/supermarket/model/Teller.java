package dojo.supermarket.model;

import main.java.dojo.supermarket.model.offers.BundleOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();
    private final List<BundleOffer> bundleOffers = new ArrayList<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, new Offer(offerType, product, argument));
    }

    public void addBundleOffer(BundleOffer bundleOffer) {
        this.bundleOffers.add(bundleOffer);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }

        this.handleBundleOffers(theCart.getItems(), receipt);
        receipt.handleOffers(theCart.getItems(), this.offers, this.catalog);
        return receipt;
    }

    void handleBundleOffers(List<ProductQuantity> productQuantities, Receipt receipt) {
        for (BundleOffer bundleOffer: bundleOffers) {
            Discount discount = bundleOffer.getDiscount(productQuantities, this.catalog);
            if (discount != null)
                receipt.addDiscount(discount);
        }
    }
}
