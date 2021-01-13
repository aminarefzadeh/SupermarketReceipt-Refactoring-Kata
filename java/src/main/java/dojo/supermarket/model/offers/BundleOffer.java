package main.java.dojo.supermarket.model.offers;

import java.util.ArrayList;
import java.util.List;

public class BundleOffer {
    private final List<ProductQuantity> items;

    public BundleOffer(List<ProductQuantity> items) {
        this.items = items;
    }

    Discount getDiscount(List<ProductQuantity> cartItems, SupermarketCatalog catalog){
        if (!this.isApplicable(cartItems)){
            return null;
        }
        double total = calculateTotal(cartItems, catalog);
        return Discount(null, "bundle", -total * 0.1);
    }

    boolean isApplicable(List<ProductQuantity> cartItems){
        return cartItems.containsAll(this.items);
    }

    double calculateTotal(List<ProductQuantity> cartItems, SupermarketCatalog catalog){
        double total = 0;
        double unitPrice = catalog.getUnitPrice(this.product);
        for (ProductQuantity item: cartItems){
            total += item.quantity * unitPrice;
        }
        return total;
    }
}
