package com.market.express.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.market.express.api.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select c from Client c where c.email like ?1 and c.password like ?2")
	Client findByEmailAndPassword(String email, String password);
}
