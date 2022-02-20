package com.market.express.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.market.express.webapp.configuration.CustomProperties;
import com.market.express.webapp.model.Client;

@Component
public class ClientProxy {
	
	@Autowired
	CustomProperties properties;
	
	/**
	 * Get an client by the id
	 * @param id The id of the client
	 * @return The client which matches the id
	 */
	public Client getClient(Long id) {
		String baseApiUrl = properties.getApiUrl();
		String getClientUrl = baseApiUrl + "/clients/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(getClientUrl, 
																HttpMethod.GET, 
																null, 
																Client.class);
		System.out.println("Get Client call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update an client - using the PUT HTTP Method.
	 * @param Existing client to update
	 * @return The client modified
	 */
	public Client updateClient(Client client) {
		String baseApiUrl = properties.getApiUrl();
		String updateClientUrl = baseApiUrl + "/clients/" + client.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Client> request = new HttpEntity<Client>(client);
		ResponseEntity<Client> response = restTemplate.exchange(updateClientUrl, 
																HttpMethod.PUT, 
																request, 
																Client.class);
		System.out.println("Update Client call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Register new client - using the POST HTTP Method.
	 * @param new client to add
	 * @return The client modified
	 */
	public Client registerClient(Client client) {
		String baseApiUrl = properties.getApiUrl();
		String registerClientUrl = baseApiUrl + "/clients/register";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Client> request = new HttpEntity<Client>(client);
		ResponseEntity<Client> response = restTemplate.exchange(registerClientUrl, 
																HttpMethod.POST, 
																request, 
																Client.class);
		System.out.println("Register new Client call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Login client - using the POST HTTP Method.
	 * @param client email and password
	 * @return The client modified
	 */
	public Client loginClient(Client client) {
		String baseApiUrl = properties.getApiUrl();
		String loginClientUrl = baseApiUrl + "/clients/login";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Client> request = new HttpEntity<Client>(client);
		ResponseEntity<Client> response = restTemplate.exchange(loginClientUrl , 
																HttpMethod.POST, 
																request, 
																Client.class);
		System.out.println("Login Client call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Delete client - using the DELETE HTTP Method.
	 * @param client id to delete
	 */
	public void deleteClient(Long id) {
		String baseApiUrl = properties.getApiUrl();
		String loginClientUrl = baseApiUrl + "/clients/"+id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(loginClientUrl , 
																HttpMethod.DELETE, 
																null, 
																Client.class);
		System.out.println("Delete Client call " + response.getStatusCode().toString());
	}
	
	/**
	 * Add product to cart of client - using the PUT HTTP Method.
	 * @param client and product id
	 * @return The client modified
	 */
	public Client addProductCart(Long clientId, Long productId) {
		String baseApiUrl = properties.getApiUrl();
		String addProductCartUrl = baseApiUrl + "/clients/"+clientId+"/cart/"+productId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(addProductCartUrl , 
																HttpMethod.PUT, 
																null, 
																Client.class);
		System.out.println("Add product to client cart call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Delete product from cart of client - using the DELETE HTTP Method.
	 * @param client and product id to delete
	 * @return The client modified
	 */
	public Client deleteProductCart(Long clientId, Long productId) {
		String baseApiUrl = properties.getApiUrl();
		String addProductCartUrl = baseApiUrl + "/clients/"+clientId+"/cart/"+productId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(addProductCartUrl , 
																HttpMethod.DELETE, 
																null, 
																Client.class);
		System.out.println("Delete product from client cart call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * Add command for client - using the POST HTTP Method.
	 * @param client id and delivery type as Request parameter
	 * @return The client modified
	 */
	public Client addCommand(Long id, Long DeliveryType) {
		String baseApiUrl = properties.getApiUrl();
		String addCommandUrl = baseApiUrl + "/clients/"+id+"/commands?deliveryTypeId="+DeliveryType;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(addCommandUrl , 
																HttpMethod.POST, 
																null, 
																Client.class);
		System.out.println("Add command for client call " + response.getStatusCode().toString());

		return response.getBody();
	}
	
	/**
	 * delete command of client - using the DELETE HTTP Method.
	 * @param client and command id to delete
	 * @return The client modified
	 */
	public Client deleteCommand(Long clientId, Long commandId) {
		String baseApiUrl = properties.getApiUrl();
		String deleteCommandUrl = baseApiUrl + "/clients/"+clientId+"/commands/"+commandId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Client> response = restTemplate.exchange(deleteCommandUrl , 
																HttpMethod.DELETE, 
																null, 
																Client.class);
		System.out.println("Delete command of client call " + response.getStatusCode().toString());

		return response.getBody();
	}
}
