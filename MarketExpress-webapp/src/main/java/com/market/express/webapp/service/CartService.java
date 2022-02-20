package com.market.express.webapp.service;

import com.market.express.webapp.model.Client;

public interface CartService {

	public Client addProductCart(final Long clientId, final Long productId);
	
	public Client deleteProductCart(final Long clientId, final Long productId);
	
}
