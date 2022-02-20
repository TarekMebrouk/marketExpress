package com.market.express.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.express.api.model.DeliveryTypes;
import com.market.express.api.model.Product;
import com.market.express.api.repository.DeliveryTypesRepository;
import com.market.express.api.repository.ProductRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private DeliveryTypesRepository deliveryTypesRepository;

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Iterable<Product> getProductByCategory(String category) {
		return productRepository.findByCategory(category);
	}
	
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product addDeliveryTypeById(final Long productId, final Long deliveryTypeId){
		Optional<Product> productData = productRepository.findById(productId);
		Optional<DeliveryTypes> DeliveryTypeData = deliveryTypesRepository.findById(deliveryTypeId);
		
		if (productData != null && DeliveryTypeData != null) {
			// get product and delivery type by id
			Product currentProduct = productData.get();
			DeliveryTypes deliveryType = DeliveryTypeData.get();
			
			// add new delivery type to current product
			currentProduct.addDeliveryType(deliveryType);
			
			// save current product with modifications
			return productRepository.save(currentProduct);
		}
		
		return null;
	}

	@Override
	public Product deleteDeliveryTypeById(Long productId, Long deliveryTypeId) {
		Optional<Product> productData = productRepository.findById(productId);
		Optional<DeliveryTypes> DeliveryTypeData = deliveryTypesRepository.findById(deliveryTypeId);
		
		if (productData != null && DeliveryTypeData != null) {
			// get product and delivery type by id
			Product currentProduct = productData.get();
			DeliveryTypes deliveryType = DeliveryTypeData.get();
			
			// delete old delivery type to current product
			currentProduct.deleteDeliveryType(deliveryType);
			
			// save current product with modifications
			return productRepository.save(currentProduct);
		}
		
		return null;
	}
	
}
