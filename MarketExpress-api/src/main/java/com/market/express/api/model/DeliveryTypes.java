package com.market.express.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "delivery_type")
public class DeliveryTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private float price;
	
	@Column(name = "days_limit")
	private int daysLimit;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "deliveryTypes")
	private List<Product> products = new ArrayList<Product>();
	
}
