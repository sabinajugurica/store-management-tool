package api.store;

import api.store.descriptions.Product;
import api.store.services.StoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/MyProducts")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return storeService.viewAllProducts();
    }

    @GetMapping(path = "{id}")
    public Product getProduct( @PathVariable  long id) {
        return storeService.findProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        storeService.addProduct(product);
    }

    @PutMapping(path = "{id}/price/{newPrice}")
    public void changePrice(@PathVariable long id, @PathVariable float newPrice){
       storeService.changePrice(id, newPrice);
    }

    @PutMapping(path = "/category/{id}")
    public void changeCategory(@PathVariable long id, @RequestBody String newCategory){
        storeService.changeCategory(id, newCategory);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable long id){
        storeService.deleteProduct(id);
    }
}
