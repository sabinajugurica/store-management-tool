package api.store.services;

import api.store.descriptions.Product;

import java.util.List;

public interface IStoreService {
    List<Product> viewAllProducts();
    Product findProduct(long id);
    void addProduct(Product product);
    void changeCategory(long id, String newCategory);
    void changePrice(long id, float newPrice);
    void deleteProduct(long id);
}
