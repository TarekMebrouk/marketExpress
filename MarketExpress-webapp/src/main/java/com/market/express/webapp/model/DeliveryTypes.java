package com.market.express.webapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeliveryTypes {
	
	private long id;
	
	private String name;
	
	private float price;
	
	private int daysLimit;
	
}
