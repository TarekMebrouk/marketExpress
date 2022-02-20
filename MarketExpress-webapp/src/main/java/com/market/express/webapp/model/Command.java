package com.market.express.webapp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Command {

	private long id;
	
	private String deliveryType;
	
	private Date deliveryDate;
	
	private float totalPrice;
	
	private List<Product> commandProducts = new ArrayList<>();
	
}
