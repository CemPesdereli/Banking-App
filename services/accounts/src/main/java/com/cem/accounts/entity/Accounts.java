package com.cem.accounts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private Long customerId;



    private String accountType;

    private String branchAddress;


}
