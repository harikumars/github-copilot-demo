package com.copilot.sample.model;



import lombok.Data;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

        private String customerId;
        private String customerName;
        private String customerAddress;
        private String customerPhone;
        private String customerEmail;

        
}
