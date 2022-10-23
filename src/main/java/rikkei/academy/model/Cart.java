package rikkei.academy.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> entry = selectItemInCart(product);
            int newQty = entry.getValue() + 1;
            products.replace(entry.getKey(), newQty);
        }
    }

    public void removeProduct(Product product) {
        if (checkItemInCart(product)) {
            Map.Entry<Product, Integer> entry = selectItemInCart(product);
            int newQty = entry.getValue() - 1;
            if (newQty == 0) {
                products.remove(entry.getKey());
            } else {
                products.replace(entry.getKey(), newQty);
            }
        }
    }

    public Integer countProductQty() {
        Integer productQty = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQty += entry.getValue();
        }
        return productQty;
    }

    public Integer countItemQty() {
        return products.size();
    }

    public float countTotalPayment() {
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * entry.getValue();
        }
        return payment;
    }
}
