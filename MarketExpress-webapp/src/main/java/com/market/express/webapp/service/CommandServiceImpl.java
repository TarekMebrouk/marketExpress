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
public class CommandServiceImpl implements CommandService{
	
	@Autowired
	ClientProxy clientProxy;
	
	@Override
	public Client addCommand(Long clientId, Long deliveryType) {
		return clientProxy.addCommand(clientId, deliveryType);
	}

	@Override
	public Client deleteCommand(Long clientId, Long commandId) {
		return clientProxy.deleteCommand(clientId, commandId);
	}

}
