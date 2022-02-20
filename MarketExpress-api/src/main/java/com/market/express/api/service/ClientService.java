package com.market.express.api.service;

import java.util.Optional;
import com.market.express.api.model.Client;

public interface ClientService {
	
	public Optional<Client> getClient(final Long id);
	
	public void deleteClient(final Long id);
	
	public Client saveClient(Client client);
	
	public Client login(Client client);
	
	public Iterable<Client> getClients();
	
	public Client AddProductToCart(final Long clientId, final Long productId);
	
	public Client DeleteProductFromCart(final Long clientId, final Long productId);
	
}
