package api.store;

import api.store.descriptions.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreApplicationTests {

	@Autowired
	StoreController storeController;

	@Test
	public void testCreate() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		storeController.addProduct(product);
		assertEquals(product.getBrand(), storeController.getProduct(product.getId()).getBrand());
	}

	@Test
	public void testExceptionForCreate() {
		Product product = new Product(1L, "Phone", "Apple", null, "Iphone description", 0f, 8.6f);
		assertThrows(IllegalArgumentException.class, () -> storeController.addProduct(product));
	}

	@Test
	public void testDelete() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		storeController.addProduct(product);
		storeController.deleteProduct(product.getId());
		assertThrows(IllegalArgumentException.class, () -> storeController.getProduct(product.getId()));
	}

	@Test
	public void testViewAll() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		storeController.addProduct(product);
		List<Product> products = storeController.getAllProducts();
		assertTrue(products.size() > 0);
	}

	@Test
	public void testPrice() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		storeController.addProduct(product);
		storeController.changePrice(product.getId(), 20.0f);
		assertEquals(20.0f, storeController.getProduct(product.getId()).getPrice());
	}

	@Test
	public void testChangeCategory() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		storeController.addProduct(product);
		storeController.changeCategory(product.getId(), "Audio, Video");
		assertEquals("Audio, Video", storeController.getProduct(product.getId()).getCategory());
	}

	@Test
	public void testFindProductException() {
		Product product = new Product(1L, "Phone", "Apple", "Iphone XS MAX", "Iphone description", 3000.0f, 8.6f);
		assertThrows(IllegalArgumentException.class, () -> storeController.getProduct(product.getId()));
	}
}
