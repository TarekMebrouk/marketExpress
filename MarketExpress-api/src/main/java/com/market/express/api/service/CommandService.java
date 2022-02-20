package com.market.express.api.service;

import com.market.express.api.model.Client;

public interface CommandService {
	
	public Client saveCommand(final Long clientId, final Long deliveryTypeId);
	
	public Client deleteCommand(final Long clientId, final Long commandId);
}
