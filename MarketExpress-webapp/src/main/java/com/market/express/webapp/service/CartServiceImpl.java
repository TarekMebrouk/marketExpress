package com.market.express.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.express.webapp.model.Client;
import com.market.express.webapp.repository.ClientProxy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	ClientProxy clientProxy;
	
	@Override
	public Client addProductCart(Long clientId, Long productId) {
		return clientProxy.addProductCart(clientId, productId);
	}

	@Override
	public Client deleteProductCart(Long clientId, Long productId) {
		return clientProxy.deleteProductCart(clientId, productId);
	}

}
