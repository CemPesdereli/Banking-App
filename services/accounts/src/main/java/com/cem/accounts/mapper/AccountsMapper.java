package com.cem.accounts.mapper;

import com.cem.accounts.dto.AccountsDto;
import com.cem.accounts.entity.Accounts;

public class AccountsMapper {

    public static Accounts mapToAccounts(AccountsDto accountsDto) {
        return Accounts.builder()
                .accountNumber(accountsDto.accountNumber())
                .accountType(accountsDto.accountType())
                .branchAddress(accountsDto.branchAddress())
                .build();
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return new AccountsDto(
                accounts.getAccountNumber(),
                accounts.getAccountType(),
                accounts.getBranchAddress()
        );
    }
}
