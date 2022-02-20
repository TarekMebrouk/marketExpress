package com.market.express.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.market.express.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("select p from Product p where p.category like ?1%")
	Iterable<Product> findByCategory(String category);
}
