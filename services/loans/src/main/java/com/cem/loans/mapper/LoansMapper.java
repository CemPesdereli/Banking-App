package com.cem.loans.mapper;

import com.cem.loans.dto.LoansDto;
import com.cem.loans.entity.Loans;

public class LoansMapper {

public static Loans toLoan(LoansDto loansDto) {
    return Loans.builder()
            .mobileNumber(loansDto.mobileNumber())
            .loanNumber(loansDto.loanNumber())
            .loanType(loansDto.loanType())
            .totalLoan(loansDto.totalLoan())
            .amountPaid(loansDto.amountPaid())
            .outstandingAmount(loansDto.outstandingAmount())
            .build();
}


public static LoansDto toLoanDto(Loans loans) {
    return new LoansDto(
        loans.getMobileNumber(),
        loans.getLoanNumber(),
        loans.getLoanType(),
        loans.getTotalLoan(),
        loans.getAmountPaid(),
        loans.getOutstandingAmount()
    );
}


}
