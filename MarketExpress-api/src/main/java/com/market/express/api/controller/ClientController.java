package com.market.express.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.market.express.api.model.Client;
import com.market.express.api.service.ClientServiceImpl;
import com.market.express.api.service.CommandServiceImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientServiceImpl clientService;
	
	@Autowired
	private CommandServiceImpl commandService;
	
	/**
	 * Register - Add a new client
	 * @param client An object Client
	 * @return The Client object saved
	 */
	@PostMapping("/register")
	public Client registerClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}
	
	/**
	 * Login - Add a new client
	 * @param client An object Client
	 * @return The Client object saved
	 */
	@PostMapping("/login")
	public Client loginClient(@RequestBody Client client) {
		return clientService.login(client);
	}
	
	
	/**
	 * Read - Get one client 
	 * @param id The id of the client
	 * @return An Client object full filled
	 */
	@GetMapping("/{id}")
	public Client getClient(@PathVariable("id") final Long id) {
		Optional<Client> client = clientService.getClient(id);
		if(client.isPresent()) {
			return client.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all clients
	 * @return An Client list Object
	 */
	@GetMapping("")
	public Iterable<Client> getClients() {
		return clientService.getClients();
	}
	
	/**
	 * Update - Update an existing client
	 * @param id - The id of the client to update
	 * @param client - The client object updated
	 * @return
	 */
	@PutMapping("/{id}")
	public Client updateClient(@PathVariable("id") final Long id, @RequestBody Client client) {
		Optional<Client> old_client = clientService.getClient(id);
		
		if(old_client.isPresent()) {
			Client currentClient = old_client.get();
			
			// set new full name
			String fullName = client.getFullName();
			if(fullName != null) {
				currentClient.setFullName(fullName);
			}
			
			// set new email
			String email = client.getEmail();
			if(email != null) {
				currentClient.setEmail(email);
			}
			
			// set new password
			String password = client.getPassword();
			if(password != null) {
				currentClient.setPassword(password);
			}
			
			// save new client information 
			clientService.saveClient(currentClient);
			return currentClient;
			
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an client
	 * @param id - The id of the client to delete
	 */
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") final Long id) {
		clientService.deleteClient(id);
	}

	
	/**
	 * Add Product - Add a new product
	 * @param client id and product id
	 * @return The Client object saved
	 */
	@PutMapping("/{client_id}/cart/{product_id}")
	public Client addProductCart(@PathVariable("client_id") final Long client_id,
								 @PathVariable("product_id") final Long product_id) {
		return clientService.AddProductToCart(client_id, product_id);
	}
	
	/**
	 * Delete Product - Delete old product
	 * @param client id and product id
	 * @return The Client object saved
	 */
	@DeleteMapping("/{client_id}/cart/{product_id}")
	public Client deleteProductCart(@PathVariable("client_id") final Long client_id,
								 @PathVariable("product_id") final Long product_id) {
		return clientService.DeleteProductFromCart(client_id, product_id);
	}
	
	/**
	 * Create Command - Add a new command
	 * @param command Object and client id
	 * @return The Client object saved
	 */
	@PostMapping("/{id}/commands")
	public Client addCommand(@PathVariable("id") final Long id, @RequestParam(name = "deliveryTypeId") final Long deliveryTypeId) {
		return commandService.saveCommand(id, deliveryTypeId);
	}
	
	/**
	 * Delete Command - Add a new command
	 * @param command Object and client id
	 * @return The Client object saved
	 */
	@DeleteMapping("/{client_id}/commands/{command_id}")
	public Client deleteCommand(@PathVariable("client_id") final Long clientId, 
								@PathVariable("command_id") final Long commandId) {
		return commandService.deleteCommand(clientId, commandId);
	}

}
