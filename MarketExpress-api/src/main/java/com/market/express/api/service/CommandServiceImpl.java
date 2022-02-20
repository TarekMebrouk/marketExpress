package com.market.express.api.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.market.express.api.model.Client;
import com.market.express.api.model.Command;
import com.market.express.api.model.DeliveryTypes;
import com.market.express.api.model.Product;
import com.market.express.api.repository.ClientRepository;
import com.market.express.api.repository.CommandRepository;
import com.market.express.api.repository.DeliveryTypesRepository;
import com.market.express.api.repository.ProductRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Service
public class CommandServiceImpl implements CommandService{
	
	@Autowired
	private CommandRepository commandRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private DeliveryTypesRepository deliveryTypesRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Client saveCommand(Long clientId, final Long deliveryTypeId) {
		Optional<Client> clientData = clientRepository.findById(clientId);
		Optional<DeliveryTypes> deliveryTypeData = deliveryTypesRepository.findById(deliveryTypeId);
		
		if (clientData != null && deliveryTypeData != null) {
			// create new Command Object 
			Command command = new Command();
			
			// get client and deliveryType Objects
			Client currentClient = clientData.get();
			command.setClient(currentClient);
			command.setCommandProducts(new ArrayList<Product>(currentClient.getCart()));
			
			DeliveryTypes deliveryType = deliveryTypeData.get();
			command.setDeliveryType(deliveryType.getName());
			
			// calculate total price 
			float totalPrice = this.calculateTotalPrice(currentClient, deliveryType);
			command.setTotalPrice(totalPrice);
			
			// calculate delivery date limit
			Date dateLimit = this.calculateDateLimit(deliveryType);
			command.setDeliveryDate(dateLimit);
			
			// save Command 
			command = commandRepository.save(command);
			
			// delete client cart products and reduce quantity
			for(Product product: currentClient.getCart()) {
				product.decreaseQuantity();
				productRepository.save(product);
			}
			currentClient.clearCart();
			
			// save client modifications
			return clientRepository.save(currentClient);
		}
		return null;
	}

	@Override
	public Client deleteCommand(Long clientId, Long commandId) {
		// delete command 
		commandRepository.deleteById(commandId);
		
		// get new client after modifications
		return clientRepository.findById(clientId).get();
	}
	
	private float calculateTotalPrice(Client client, DeliveryTypes deliveryType) {
		float total = 0;
		
		// calculate sum of products prices
		for(Product product: client.getCart()) {
			total += product.getPrice();
		}
		
		// add delivery price
		total += deliveryType.getPrice();
		
		return total;
	}
	
	private Date calculateDateLimit(DeliveryTypes deliveryType) {
		// get current Date
		Date currentDate = new Date(new java.util.Date().getTime());
		
		// add delivery days limit to current Date
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, deliveryType.getDaysLimit());
        
        // return Date limit of delivery
        return new Date(c.getTimeInMillis());
	}
}
