package com.market.express.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.market.express.webapp.configuration.CustomProperties;
import com.market.express.webapp.model.Product;

@Component
public class ProductProxy {

	@Autowired
	private CustomProperties properties;
	
	/**
	 * Get an Product by the id
	 * @param id The id of the product
	 * @return The product which matches the id
	 */
	public Product getProductById(Long id) {
		String baseApiUrl = properties.getApiUrl();
		String getProductUrl = baseApiUrl + "/products/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Product> response = restTemplate.exchange(getProductUrl, 
																 HttpMethod.GET, 
																 null, 
																 Product.class);
		System.out.println("Get Product call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get all Products 
	 * @return The product list 
	 */
	public Iterable<Product> getProducts() {
		String baseApiUrl = properties.getApiUrl();
		String getProductsUrl = baseApiUrl + "/products";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Product>> response = restTemplate.exchange(getProductsUrl,
																		   HttpMethod.GET, 
																		   null, 
																		   new ParameterizedTypeReference<Iterable<Product>>() {});
		System.out.println("Get All Product call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Get all Products by category
	 * @param category of product
	 * @return The product list 
	 */
	public Iterable<Product> getProductByCategory(String category) {
		String baseApiUrl = properties.getApiUrl();
		String getProductsUrl = baseApiUrl + "/products?category="+category;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Product>> response = restTemplate.exchange(getProductsUrl,
																		   HttpMethod.GET, 
																		   null, 
																		   new ParameterizedTypeReference<Iterable<Product>>() {});
		System.out.println("Get Products by category call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
}
