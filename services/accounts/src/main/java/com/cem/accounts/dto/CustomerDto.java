package com.cem.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(

        @NotEmpty(message = "Name can not be null or empty")
        @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters")
        String name,

        @NotEmpty(message = "Email can not be null or empty")
        @Email(message = "Invalid email address")
        String email,

        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        String mobileNumber) {


}
