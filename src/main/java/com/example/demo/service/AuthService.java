package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterCustomerRequest;


public interface AuthService {
 
     public LoginResponse registerCustomer(RegisterCustomerRequest request);
     public Object loginCustomer(LoginRequest request);
     public boolean  doesEmailExists(String email);
    
}
