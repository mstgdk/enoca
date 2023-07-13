package com.enoca.mapper;

import com.enoca.DTO.response.CustomerResponse;
import com.enoca.DTO.response.LikeResponse;
import com.enoca.DTO.response.OrderResponse;
import com.enoca.domain.Customer;
import com.enoca.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {




    CustomerResponse customerToCustomerResponse(Customer customer);


    List<LikeResponse>map(List<Customer>customers);

    List<CustomerResponse> map2(List<Customer>customer);
}
