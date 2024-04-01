package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.service.CheckoutService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/purchase")
@Log4j2
public class CheckOutController {
	
	
	private CheckoutService checkoutService;

	public CheckOutController(CheckoutService checkoutService) {
		super();
		this.checkoutService = checkoutService;
	}
	
	
	@PostMapping()
	public PurchaseResponse placeholder(@RequestBody Purchase purchase) {
		
		PurchaseResponse purchaseResponse = this.checkoutService.placeholder(purchase);
		
		
		log.info("new purchase excute",purchase);
		
		
		return purchaseResponse;
	}

}
