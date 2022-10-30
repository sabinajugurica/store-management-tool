package api.store.services;

import api.store.descriptions.Product;
import api.store.descriptions.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreService implements IStoreService {


    private final ProductRepository productRepository;

    public StoreService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> viewAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(long id) {
        return  productRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Product with the specified doesn't exist"));
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void changeCategory(long id, String newCategory) {
        Product productToBeUpdated = findProductForUpdate(id);
        productToBeUpdated.setCategory(newCategory);
        productRepository.save(productToBeUpdated);
    }

    @Override
    public void changePrice(long id, float newPrice) {
        System.out.println("changePrice | " + id + " | " + newPrice);
        Product productToBeUpdated = findProductForUpdate(id);
        System.out.println(productToBeUpdated.getBrand() + " | " + productToBeUpdated.getPrice());
        productToBeUpdated.setPrice(newPrice);
        System.out.println(productToBeUpdated.getPrice());
        productRepository.save(productToBeUpdated);
    }

    @Override
    public void deleteProduct(long id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists){
            throw new IllegalStateException(String.format("Product with id %s does not exist.", id));
        }
        productRepository.deleteById(id);
    }

    private Product findProductForUpdate(long id){
        System.out.println(id);
        return productRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Product with id doesn't exist"));
    }
}
