package com.market.express.webapp.service;

import com.market.express.webapp.model.Client;

public interface ClientService {
	
	public Client login(Client client);
	
	public Client register(Client client);
	
	public Client updateClient(Client client);
	
	public Client getClient(final Long id);
	
	public void deleteClient(final Long clientId);

}
