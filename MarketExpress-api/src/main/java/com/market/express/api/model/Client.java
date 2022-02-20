package com.market.express.api.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	private String email;
	
	private String password;
	
	@ManyToMany
	@JoinTable(name = "cart", 
			   joinColumns = @JoinColumn(referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<Product> cart = new ArrayList<>();
	
	@OneToMany(mappedBy = "client")
	private List<Command> commands = new ArrayList<>();
	
	public void addProduct(Product product) {
		this.cart.add(product);
	}
	
	public void deleteProduct(Product product) {
		this.cart.remove(product);
	}
	
	public void clearCart() {
		this.cart.clear();
	}
	
	public void addCommand(Command command) {
		this.commands.add(command);
	}
	
	public void deleteCommand(Command command) {
		this.commands.remove(command);
	}
	
}
