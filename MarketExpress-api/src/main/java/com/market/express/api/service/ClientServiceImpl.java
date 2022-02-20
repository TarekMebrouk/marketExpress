package com.market.express.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.express.api.model.Client;
import com.market.express.api.model.Product;
import com.market.express.api.repository.ClientRepository;
import com.market.express.api.repository.ProductRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Optional<Client> getClient(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client login(Client client) {
		return clientRepository.findByEmailAndPassword(client.getEmail(), client.getPassword());
	}

	@Override
	public Iterable<Client> getClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client AddProductToCart(final Long clientId, Long productId) {
		Optional<Client> clientData = clientRepository.findById(clientId);
		Optional<Product> productData = productRepository.findById(productId);
		
		if (clientData != null && productData != null) {
			// get client and product Objects
			Client currentClient = clientData.get();
			Product product = productData.get();
			
			// add product to client cart 
			currentClient.addProduct(product);
			
			// save modifications
			return clientRepository.save(currentClient);
		}
		
		return null;
	}

	@Override
	public Client DeleteProductFromCart(final Long clientId, Long productId) {
		Optional<Client> clientData = clientRepository.findById(clientId);
		Optional<Product> productData = productRepository.findById(productId);
		
		if (clientData != null && productData != null) {
			// get client and product Objects
			Client currentClient = clientData.get();
			Product product = productData.get();
			
			// add product to client cart 
			currentClient.deleteProduct(product);
			
			// save modifications
			return clientRepository.save(currentClient);
		}
		
		return null;
	}


}
