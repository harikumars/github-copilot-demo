package com.copilot.sample.model;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String transactionId;
    private String transactionAmount;
    private String transactionDate;
    private String transactionStatus;
    private String description ;
    private String customerId;

}
