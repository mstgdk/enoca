package com.enoca.controller;

import com.enoca.DTO.messages.ResponseMessage;
import com.enoca.DTO.request.CustomerRequest;
import com.enoca.DTO.response.EnocaResponse;
import com.enoca.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnocaResponse>createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        customerService.createCustomer(customerRequest);

        EnocaResponse response = new EnocaResponse();
        response.setMessage(ResponseMessage.CUSTOMER_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
}
