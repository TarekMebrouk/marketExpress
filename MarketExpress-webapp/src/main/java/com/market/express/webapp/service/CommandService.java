package com.market.express.webapp.service;

import com.market.express.webapp.model.Client;

public interface CommandService {
	
	public Client addCommand(final Long clientId, final Long deliveryType);
	
	public Client deleteCommand(final Long clientId, final Long commandId);
}
