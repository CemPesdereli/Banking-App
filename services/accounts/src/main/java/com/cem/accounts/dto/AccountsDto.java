package com.cem.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public record AccountsDto(
        @Schema(
                description = "Account number of Eazy Bank account",
                example = "1234567890"
        )
        Long accountNumber,

        @Schema(
                description = "Account type of Eazy Bank account",
                example = "Savings"
        )
        String accountType,
        @Schema(
                description = "Eazy Bank branch address",
                example = "123Main Street, New York"
        )
        String branchAddress) {


}
