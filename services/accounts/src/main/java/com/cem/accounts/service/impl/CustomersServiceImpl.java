package com.cem.accounts.service.impl;

import com.cem.accounts.dto.CardsDto;
import com.cem.accounts.dto.CustomerDetailsDto;
import com.cem.accounts.dto.LoansDto;
import com.cem.accounts.entity.Accounts;
import com.cem.accounts.entity.Customer;
import com.cem.accounts.exception.ResourceNotFoundException;
import com.cem.accounts.mapper.AccountsMapper;
import com.cem.accounts.repository.AccountsRepository;
import com.cem.accounts.repository.CustomerRepository;
import com.cem.accounts.service.IAccountsService;
import com.cem.accounts.service.ICustomersService;
import com.cem.accounts.service.client.CardsFeignClient;
import com.cem.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow( () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);

        return AccountsMapper.mapToCustomerDetailsDto(customer, AccountsMapper.mapToAccountsDto(accounts), cardsDtoResponseEntity.getBody(), loansDtoResponseEntity.getBody());

    }
}
