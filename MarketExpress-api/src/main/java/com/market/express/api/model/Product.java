package com.market.express.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String description;
	
	private float price;
	
	private int quantity;
	
	private String category;
	
	@ManyToMany
	@JoinTable(name = "product_delivery_types", 
			   joinColumns = @JoinColumn(referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<DeliveryTypes> deliveryTypes = new ArrayList<DeliveryTypes>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "cart")
	private List<Client> clients = new ArrayList<Client>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "commandProducts")
	private List<Command> productCommands = new ArrayList<Command>();
	
	public void addDeliveryType(DeliveryTypes deliveryType) {
		this.deliveryTypes.add(deliveryType);
	}
	
	public void deleteDeliveryType(DeliveryTypes deliveryType) {
		this.deliveryTypes.remove(deliveryType);
	}
	
	public void decreaseQuantity() {
		if (this.quantity > 0)
			this.quantity--;
	}
}
