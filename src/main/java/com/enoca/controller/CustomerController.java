package com.enoca.controller;

import com.enoca.DTO.messages.ResponseMessage;
import com.enoca.DTO.request.CustomerRequest;
import com.enoca.DTO.request.CustomerUpdateRequest;
import com.enoca.DTO.response.CustomerResponse;
import com.enoca.DTO.response.EnocaResponse;
import com.enoca.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EnocaResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);

        EnocaResponse response = new EnocaResponse();
        response.setMessage(ResponseMessage.CUSTOMER_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pages")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<CustomerResponse>> getAllWithPage(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size,
                                                                 @RequestParam("sort") String prop,
                                                                 @RequestParam(value = "direction",
                                                                         required = false,
                                                                         defaultValue = "DESC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<CustomerResponse> customerResponse = customerService.findAllWithPage(pageable);
        return ResponseEntity.ok(customerResponse);
    }
    // güncelleme
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnocaResponse>updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        customerService.customerUpdate(id, customerUpdateRequest);

        EnocaResponse response = new EnocaResponse();
        response.setMessage(ResponseMessage.CUSTOMER_UPDATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
   //customer silme işlemi
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnocaResponse>deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);

        EnocaResponse response = new EnocaResponse();
        response.setMessage(ResponseMessage.CUSTOMER_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);

    }
}
