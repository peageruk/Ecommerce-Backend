package com.example.demo.entity;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.dto.RegisterCustomerRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Order> orders = new HashSet<Order>(); 


	public Customer(RegisterCustomerRequest request) {
		this.firstName = request.getFirstname();
		this.lastName = request.getLastname();
		this.email = request.getEmail();
		this.password = request.getPassword();
	}
	
	
	public void addOrder(Order order) {
		if(order != null) {
			if(orders == null) {
				orders = new HashSet<Order>();
			}
			orders.add(order);
			order.setCustomer(this);
		}
	}

}

   
