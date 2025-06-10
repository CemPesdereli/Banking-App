package com.cem.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Accounts, Cards and Loans information"
)
public record CustomerDetailsDto(@Schema(
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
                                         description = "Accounts details of the customer"

                                 )
                                 AccountsDto accountsDto,
                                 @Schema(
                                         description = "Cards details of the customer"

                                 )
                                 CardsDto cardsDto,
                                 @Schema(
                                         description = "Loans details of the customer"

                                 )
                                 LoansDto loansDto) {
}
