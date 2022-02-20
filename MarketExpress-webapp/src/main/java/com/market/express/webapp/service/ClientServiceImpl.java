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
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientProxy clientProxy;

	@Override
	public Client login(Client client) {
		return clientProxy.loginClient(client);
	}

	@Override
	public Client register(Client client) {
		return clientProxy.registerClient(client);
	}

	@Override
	public Client updateClient(Client client) {
		return clientProxy.updateClient(client);
	}

	@Override
	public Client getClient(Long id) {
		return clientProxy.getClient(id);
	}

	@Override
	public void deleteClient(Long clientId) {
		clientProxy.deleteClient(clientId);
	}

}
