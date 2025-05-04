package com.cem.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(
        name = "AccountsCustomer",
        description = "Schema to hold Accounts and Customer information"
)
public record AccountsCustomerDto(
                                @Schema(
                                            description = "Name of the customer",
                                            example = "Eazy Bytes"
                                    )
                                  @NotEmpty(message = "Name can not be null or empty")
                                  @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters")
                                  String name,

                                @Schema(
                                        description = "Email address of the customer",
                                        example = "tutor@eazybytes.com"
                                )
                                  @NotEmpty(message = "Email can not be null or empty")
                                  @Email(message = "Invalid email address")
                                  String email,

                                @Schema(
                                        description = "Mobile number of the customer",
                                        example = "1234567890"
                                )
                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  String mobileNumber,

                                @Schema(
                                        description = "Account number of Eazy Bank account",
                                        example = "1234567890"
                                )
                                  @NotNull(message = "AccountNumber can not be null")
                                  @Min(value = 1000000000L, message = "AccountNumber must be 10 digits")
                                  @Max(value = 9999999999L, message = "AccountNumber must be 10 digits")
                                  Long accountNumber,

                                @Schema(
                                        description = "Account type of Eazy Bank account",
                                        example = "Savings"
                                )
                                  @NotEmpty(message = "AccountType can not be null or empty")
                                  String accountType,

                                @Schema(
                                        description = "Eazy Bank branch address",
                                        example = "123Main Street, New York"
                                )
                                  @NotEmpty(message = "BranchAddress can not be null or empty")
                                  String branchAddress) {

}
