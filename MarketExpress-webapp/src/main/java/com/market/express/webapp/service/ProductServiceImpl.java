package com.market.express.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.express.webapp.model.Product;
import com.market.express.webapp.repository.ProductProxy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductProxy productProxy;
	
	@Override
	public Iterable<Product> getProducts() {
		return productProxy.getProducts();
	}

	@Override
	public Iterable<Product> getProductsByCategory(String category) {
		return productProxy.getProductByCategory(category);
	}

	@Override
	public Product getProductById(Long id) {
		return productProxy.getProductById(id);
	}

}
