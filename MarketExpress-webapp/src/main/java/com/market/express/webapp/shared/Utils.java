package com.market.express.webapp.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.market.express.webapp.model.Client;
import com.market.express.webapp.model.DeliveryTypes;
import com.market.express.webapp.model.Product;
import com.market.express.webapp.service.ClientService;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Getter
@Setter
@Component
public class Utils {
	
	@Autowired
	ClientService clientService;
	
	public Client getCurrentClient(HttpServletRequest request) {
		// get current client ID from session variables
		Long clientId = (Long) request.getSession().getAttribute("client");
		if (clientId != null) {
			return clientService.getClient(clientId);
		}
		else {
			return new Client();
		}
	}
	
	public List<DeliveryTypes> getPossibleDeliveryTypes(Client client){
		ArrayList<DeliveryTypes> types = new ArrayList<>();
		
		// get list of all delivery types
		Set<Long> deliveryTypesId = new HashSet<Long>();
		for (Product product: client.getCart()) {
			for(DeliveryTypes deliveryType: product.getDeliveryTypes()) {
				deliveryTypesId.add(deliveryType.getId());
			}
		}
		
		// get list of all delivery types Id in string
		String allTypes = "";
		for (Product product: client.getCart()) {
			for(DeliveryTypes deliveryType: product.getDeliveryTypes()) {
				allTypes += Long.toString(deliveryType.getId()) + ".";
			}
		}
		
		// get intersection of delivery types
		for (Long id: deliveryTypesId) {
			int count = org.springframework.util.StringUtils.countOccurrencesOf(allTypes, Long.toString(id));
			if (count == client.getCart().size()) {
				for(DeliveryTypes deliveryType: client.getCart().get(0).getDeliveryTypes()) {
					if (deliveryType.getId() == id) {
						types.add(deliveryType);
						break;
					}
				}
			}
		}
		
		return types;
	}
	
}
