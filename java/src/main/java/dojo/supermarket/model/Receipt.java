package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Receipt {
    private List<ReceiptItem> items = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : this.discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        this.items.add(new ReceiptItem(p, quantity, price, totalPrice));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    void handleOffers(List<ProductQuantity> productQuantities, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (ProductQuantity p: productQuantities) {
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
