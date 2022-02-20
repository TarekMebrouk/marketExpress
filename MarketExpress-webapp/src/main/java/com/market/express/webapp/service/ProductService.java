package com.market.express.webapp.service;

import com.market.express.webapp.model.Product;

public interface ProductService {
	
	public Iterable<Product> getProducts();
	
	public Iterable<Product> getProductsByCategory(String category);
	
	public Product getProductById(final Long id);
}
