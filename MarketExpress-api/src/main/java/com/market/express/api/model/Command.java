package com.market.express.api.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "command")
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "client", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Client client;
	
	@Column(name = "delivery_type")
	private String deliveryType;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@ManyToMany
	@JoinTable(name = "command_products", 
			   joinColumns = @JoinColumn(referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<Product> commandProducts = new ArrayList<>();
	
	public void addProductCommand(Product product) {
		this.commandProducts.add(product);
	}
	
	public void deleteProductCommand(Product product) {
		this.commandProducts.remove(product);
	}
	
}
