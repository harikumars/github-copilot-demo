package com.copilot.sample.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String transactionId;
    private String transactionAmount;
    private String transactionDate;
    private String transactionType;
    private String transactionStatus;
    private String transactionDescription;
//add customer id string
    private String customerId;
}
