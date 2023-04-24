package com.enoca.service;

import com.enoca.DTO.request.CustomerRequest;
import com.enoca.DTO.request.CustomerUpdateRequest;
import com.enoca.DTO.response.CustomerResponse;
import com.enoca.domain.Customer;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.exception.message.ErrorMessage;
import com.enoca.mapper.CustomerMapper;
import com.enoca.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;

        this.customerMapper = customerMapper;
    }

    public void createCustomer(CustomerRequest customerRequest) {
        Customer customer =new Customer();
        customer.setName(customerRequest.getName());
        customer.setAge(customerRequest.getAge());

        //customer.setOrders(customerRequest.getOrders());

        customerRepository.save(customer);

    }

    public Page<CustomerResponse> findAllWithPage(Pageable pageable) {
        Page<Customer> customerPage=customerRepository.findAll(pageable);

        return customerPage.map(customer -> customerMapper.customerToCustomerResponse(customer));
    }

    public void customerUpdate(Long id, CustomerUpdateRequest customerUpdateRequest) {
        //Böyle bir  customer mevcut mu?
        Customer customer =customerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION)));
     customer.setName(customerUpdateRequest.getName());
     customer.setAge(customerUpdateRequest.getAge());

     customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        //Böyle bir  customer mevcut mu?
        Customer customer =customerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION)));

        customerRepository.deleteById(id);
    }
}
