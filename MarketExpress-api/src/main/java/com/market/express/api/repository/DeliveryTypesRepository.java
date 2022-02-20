package com.market.express.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.market.express.api.model.DeliveryTypes;

@Repository
public interface DeliveryTypesRepository extends JpaRepository<DeliveryTypes, Long>{

}
