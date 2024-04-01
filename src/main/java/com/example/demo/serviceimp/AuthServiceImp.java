package com.example.demo.serviceimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterCustomerRequest;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;

@Service
public class AuthServiceImp implements AuthService{

    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public LoginResponse registerCustomer(RegisterCustomerRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer newCustomer = new Customer(request);
        customerRepository.save(newCustomer);

        return jwtService.generateToken(newCustomer);
    }

    @Override
    public Object loginCustomer(LoginRequest request) {
        Optional<Customer> customer = customerRepository.findByEmail(request.getEmail());
        if (!customer.isPresent()) {
            return null;
        }
        if (passwordEncoder.matches(request.getPassword(), customer.get().getPassword())) {
            return jwtService.generateToken(customer.get());
        }
        return null;
    }
    

    public boolean doesEmailExists(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.isPresent();
    }
}
