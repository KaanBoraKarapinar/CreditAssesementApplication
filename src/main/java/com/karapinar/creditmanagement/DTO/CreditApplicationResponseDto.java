package com.karapinar.creditmanagement.DTO;

import lombok.Data;

@Data
public class CreditApplicationResponseDto {
    private Long id;
    private Long applicantId;
    private String redirectingMarketplace;
    private Long creditAmount;
    private String processInstanceId;
    private String status;

}
