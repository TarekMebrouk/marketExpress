package com.market.express.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.market.express.api.model.Product;
import com.market.express.api.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	/**
	 * Read - Get one product 
	 * @param id The id of the product
	 * @return An Product object full filled
	 */
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") final Long id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			return product.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get products by category or all products
	 * @param category The category of the product
	 * @return An Product List object
	 */
	@GetMapping("")
	public Iterable<Product> getProduct(@RequestParam(name = "category", required = false) final String category) {
		if (category == null)
			return productService.getProducts();
		else
			return productService.getProductByCategory(category);
	}

	/**
	 * Delete - Delete an product
	 * @param id - The id of the product to delete
	 */
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") final Long id) {
		productService.deleteProduct(id);
	}

	/**
	 * Add - Add an product
	 * @param id - The id of the product to delete
	 * @return An product Object
	 */
	@PostMapping("")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	/**
	 * Add - Add an DeliveryType to product
	 * @param id - The id of the product
	 * @param id - The id of the deliveryType to add
	 * @return An product Object
	 */
	@PutMapping("/{product_id}/deliverytypes/{delivery_type_id}")
	public Product addDeliveryType(@PathVariable("product_id") final Long productId, 
								   @PathVariable("delivery_type_id") final Long deliveryTypeId) {
		return productService.addDeliveryTypeById(productId, deliveryTypeId);
	}

	/**
	 * Delete - Delete an DeliveryType from product
	 * @param id - The id of the product
	 * @param id - The id of the deliveryType to delete
	 * @return An product Object
	 */
	@DeleteMapping("/{product_id}/deliverytypes/{delivery_type_id}")
	public Product deleteDeliveryType(@PathVariable("product_id") final Long productId, 
								   @PathVariable("delivery_type_id") final Long deliveryTypeId) {
		return productService.deleteDeliveryTypeById(productId, deliveryTypeId);
	}
}
