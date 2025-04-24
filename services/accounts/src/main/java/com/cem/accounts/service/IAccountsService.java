package com.cem.accounts.service;

import com.cem.accounts.dto.AccountsCustomerDto;
import com.cem.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);


    AccountsCustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(AccountsCustomerDto accountsCustomerDto);

    boolean deleteAccount(String mobileNumber);




}
