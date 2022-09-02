package com.copilot.sample.model;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
        @Id
        private String customerId;
        private String customerName;
        private String customerAddress;
        private String customerPhone;
        private String customerEmail;
//add zipcode and city
        private String customerZipcode;
        private String customerCity;
        //add state
        private String customerState;
        
}
