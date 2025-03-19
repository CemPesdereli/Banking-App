package com.cem.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Accounts extends BaseEntity {

    @Id
    private Long customerId;

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
    private String mobileNumber;





}
