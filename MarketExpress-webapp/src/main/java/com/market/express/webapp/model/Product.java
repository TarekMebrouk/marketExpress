package com.market.express.webapp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Product {

	private long id;
	
	private String title;
	
	private String description;
	
	private float price;
	
	private int quantity;
	
	private String category;
	
	private List<DeliveryTypes> deliveryTypes = new ArrayList<DeliveryTypes>();

}
