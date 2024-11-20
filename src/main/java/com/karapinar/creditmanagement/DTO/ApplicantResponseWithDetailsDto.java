package com.karapinar.creditmanagement.DTO;

import lombok.Data;

import java.util.List;


/*
* Shows details of applications
 */
@Data
public class ApplicantResponseWithDetailsDto {
    private Long id;
    private String passportNumber;
    private String fullname;
    private double creditScore;
    private String creditScoreGroup;
    private boolean bankCustomer;
    private List<CreditApplicationResponseDto> applications;


}
