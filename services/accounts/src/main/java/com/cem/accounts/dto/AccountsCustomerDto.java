package com.cem.accounts.dto;

import jakarta.validation.constraints.*;

public record AccountsCustomerDto(
                                  @NotEmpty(message = "Name can not be null or empty")
                                  @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters")
                                  String name,

                                  @NotEmpty(message = "Email can not be null or empty")
                                  @Email(message = "Invalid email address")
                                  String email,

                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  String mobileNumber,

                                  @NotNull(message = "AccountNumber can not be null")
                                  @Min(value = 1000000000L, message = "AccountNumber must be 10 digits")
                                  @Max(value = 9999999999L, message = "AccountNumber must be 10 digits")
                                  Long accountNumber,

                                  @NotEmpty(message = "AccountType can not be null or empty")
                                  String accountType,

                                  @NotEmpty(message = "BranchAddress can not be null or empty")
                                  String branchAddress) {

}
