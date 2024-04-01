package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import lombok.Data;


@Data
public class Purchase {
	
	private Customer customer;
	private Address address;
	private Order order;
	private Set<Item> products;
	

}
