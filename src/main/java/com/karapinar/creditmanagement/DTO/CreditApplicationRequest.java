package com.karapinar.creditmanagement.DTO;
import lombok.Data;

@Data

public class CreditApplicationRequest {

        private String fullName;
        private String passportNumber;
        private Long creditAmount;
        private String redirectingMarketplace;

}
