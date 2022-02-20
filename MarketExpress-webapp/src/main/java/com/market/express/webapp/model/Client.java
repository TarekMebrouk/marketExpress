package com.market.express.webapp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Client {

	private long id;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private List<Product> cart = new ArrayList<>();
	
	private List<Command> commands = new ArrayList<>();
	
}
