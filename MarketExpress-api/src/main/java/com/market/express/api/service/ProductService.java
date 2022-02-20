package com.market.express.api.service;

import java.util.Optional;
import com.market.express.api.model.Product;

public interface ProductService {
	
	public Optional<Product> getProductById(final Long id);
	
	public Iterable<Product> getProducts();
	
	public Iterable<Product> getProductByCategory(String category);
	
	public void deleteProduct(final Long id);
	
	public Product saveProduct(Product product);
	
	public Product addDeliveryTypeById(final Long productId, final Long deliveryTypeId);
	
	public Product deleteDeliveryTypeById(final Long productId, final Long deliveryTypeId);
}

