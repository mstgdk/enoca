package com.enoca.mapper;

import com.enoca.DTO.response.CustomerResponse;
import com.enoca.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

   // List<CustomerResponse> map(List<Customer>customer);

    @Mapping(target="orders", ignore = true)
    CustomerResponse customerToCustomerResponse(Customer customer);
}
