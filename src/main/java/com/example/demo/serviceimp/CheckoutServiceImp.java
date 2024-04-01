package com.example.demo.serviceimp;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CheckoutService;


@Service
public class CheckoutServiceImp  implements CheckoutService{
	
	private CustomerRepository customerRepository;
    
	@Autowired
	public CheckoutServiceImp(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public PurchaseResponse placeholder(Purchase purchase) {
		
        Order order = purchase.getOrder();
        
        
        String orderTrackNumber =  getOrderTrackingNumber();
        
        order.setOrderTrackingNumber(orderTrackNumber);
        
        
        Set<Item> products = purchase.getProducts();
        
        products.forEach(product -> order.addtoList(product));
        
        
        order.setAddress(purchase.getAddress());
        
        Customer customer = purchase.getCustomer();
        
        customer.addOrder(order);
        
        
        customerRepository.save(customer);
        
        
        
		
		
		return new PurchaseResponse(orderTrackNumber);
	}

	private String getOrderTrackingNumber() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}

}
