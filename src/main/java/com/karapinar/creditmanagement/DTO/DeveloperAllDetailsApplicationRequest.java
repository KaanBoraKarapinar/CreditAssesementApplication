package com.karapinar.creditmanagement.DTO;
import lombok.Data;

@Data

public class DeveloperAllDetailsApplicationRequest {

        private String fullName;
        private String passportNumber;
        private Long creditAmount;
        private String redirectingMarketplace;
        private Double creditScore;
        private Boolean isBankCustomer;

}
