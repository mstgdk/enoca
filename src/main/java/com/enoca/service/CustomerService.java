package com.enoca.service;

import com.enoca.DTO.request.CustomerRequest;
import com.enoca.domain.Customer;
import com.enoca.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public void createCustomer(CustomerRequest customerRequest) {
        Customer customer =new Customer();
        customer.setName(customerRequest.getName());
        customer.setAge(customerRequest.getAge());

        customer.setOrders(customerRequest.getOrders());

        customerRepository.save(customer);


    }
}
