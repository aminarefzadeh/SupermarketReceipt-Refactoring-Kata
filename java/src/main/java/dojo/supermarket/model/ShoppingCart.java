package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    public void addItemQuantity(Product product, double quantity) {
        for (ProductQuantity p: items){
            if (p.product == product){
                p.quantity += quantity;
                return;
            }
        }
        items.add(new ProductQuantity(product, quantity));
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (ProductQuantity p: items) {
            double quantity = p.quantity;
            if (offers.containsKey(p.product)) {
                Offer offer = offers.get(p.product);
                Discount discount = offer.getDiscount(catalog, quantity);
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
}
