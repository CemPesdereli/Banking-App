package com.eazybytes.loans.service.impl;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.entity.Loans;
import com.eazybytes.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.mapper.LoansMapper;
import com.eazybytes.loans.repository.LoansRepository;
import com.eazybytes.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans= loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }


    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.toLoanDto(loans);
    }


    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.loanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.loanNumber()));

        Loans newLoan = LoansMapper.toLoan(loansDto);
        newLoan.setLoanId(loans.getLoanId());
        loansRepository.save(newLoan);
        return  true;
    }


    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }


}
