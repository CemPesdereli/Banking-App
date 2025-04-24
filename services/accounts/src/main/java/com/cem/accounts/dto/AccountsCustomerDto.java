package com.cem.accounts.dto;

public record AccountsCustomerDto(
                                  String name,
                                  String email,
                                  String mobileNumber,
                                  Long accountNumber,
                                  String accountType,
                                  String branchAddress) {

}
