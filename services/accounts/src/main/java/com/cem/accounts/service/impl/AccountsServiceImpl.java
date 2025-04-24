package com.cem.accounts.service.impl;

import com.cem.accounts.constants.AccountsConstants;
import com.cem.accounts.dto.AccountsCustomerDto;
import com.cem.accounts.dto.AccountsDto;
import com.cem.accounts.dto.CustomerDto;
import com.cem.accounts.entity.Accounts;
import com.cem.accounts.entity.Customer;
import com.cem.accounts.exception.CustomerAlreadyExistsException;
import com.cem.accounts.exception.ResourceNotFoundException;
import com.cem.accounts.mapper.AccountsMapper;
import com.cem.accounts.mapper.CustomerMapper;
import com.cem.accounts.repository.AccountsRepository;
import com.cem.accounts.repository.CustomerRepository;
import com.cem.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given Mobile Number " + customerDto.mobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");


        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));


    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    @Override
    public AccountsCustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow( () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
        return AccountsMapper.mapToAccountsCustomerDto(customer, accounts);


    }

    @Override
    public boolean updateAccount(AccountsCustomerDto accountsCustomerDto) {

        boolean isUpdated = false;

        if(accountsCustomerDto.accountNumber() !=null ){
            Accounts accounts = accountsRepository.findById(accountsCustomerDto.accountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsCustomerDto.accountNumber().toString())
            );
            // need validation for checking null for these values
            accounts.setAccountType(accountsCustomerDto.accountType());
            accounts.setBranchAddress(accountsCustomerDto.branchAddress());
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            // need validation for checking null for these values
            customer.setName(accountsCustomerDto.name());
            customer.setEmail(accountsCustomerDto.email());
            customer.setMobileNumber(accountsCustomerDto.mobileNumber());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;

    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;

    }

}
