package com.cem.accounts.mapper;

import com.cem.accounts.dto.CustomerDto;
import com.cem.accounts.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.name())
                .email(customerDto.email())
                .mobileNumber(customerDto.mobileNumber())
                .build();
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }
}
