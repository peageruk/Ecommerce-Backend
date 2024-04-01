package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	private String orderTrackingNumber;
	private int quantity;
	private BigDecimal totalprice;
	
	@UpdateTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date lastUpdated;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
	private Set<Item> orderlist = new HashSet<Item>();
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName = "id")
	private Address address;
	
	
	public void addtoList(Item item) {
		if(item != null) {
			if(orderlist == null) {
				this.orderlist = new HashSet<Item>();
			}
			
			orderlist.add(item);
			item.setOrder(this);
		}
	}
	

}
