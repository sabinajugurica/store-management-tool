package api.store.services;

import api.store.constants.Constants;
import api.store.descriptions.Product;
import api.store.descriptions.ProductRepository;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class StoreService implements IStoreService {

    private final ProductRepository productRepository;
    private final Logger logger;

    public StoreService(ProductRepository productRepository) throws IOException {
        this.productRepository = productRepository;
        FileHandler handler = new FileHandler(Constants.LOG_FILE_PATH);
        handler.setFormatter(new SimpleFormatter());
        logger = Logger.getLogger("api.store.services");
        logger.addHandler(handler);
    }

    @Override
    public List<Product> viewAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(long id) {
        return productRepository.findById(id).orElseThrow(
            () -> {
                logger.warning(Constants.WRONG_ID_LOG_MESSAGE);
                return new IllegalArgumentException(Constants.WRONG_ID_LOG_MESSAGE);
            });
    }

    @Override
    public void addProduct(Product product) {
       if(!checkProduct(product)) {
           logger.warning(Constants.INCORRECT_PRODUCT_LOG_MESSAGE);
           throw new IllegalArgumentException(Constants.INCORRECT_PRODUCT_LOG_MESSAGE);
       }
           productRepository.save(product);
    }

    @Override
    public void changeCategory(long id, String newCategory) {
        try {
            Product productToBeUpdated = findProduct(id);
            productToBeUpdated.setCategory(newCategory);
            productRepository.save(productToBeUpdated);
        }catch (IllegalArgumentException ex){
            logger.warning(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void changePrice(long id, float newPrice) {
        try {
            Product productToBeUpdated = findProduct(id);
            productToBeUpdated.setPrice(newPrice);
            productRepository.save(productToBeUpdated);
        } catch (IllegalArgumentException ex){
           logger.warning(ex.getMessage());
           throw ex;
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            if(findProduct(id) != null){
                productRepository.deleteById(id);
            }
        } catch (IllegalArgumentException ex){
            logger.warning(ex.getMessage());
            throw ex;
        }
    }

    private Boolean checkProduct(Product product){
        return product.getPrice() > 0f && !product.getName().isEmpty();
    }
}
