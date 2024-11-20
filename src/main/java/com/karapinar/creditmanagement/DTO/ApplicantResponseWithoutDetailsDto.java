package com.karapinar.creditmanagement.DTO;

import lombok.Data;

import java.util.List;


/*
*  D Does only list application ids
 */
@Data
public class ApplicantResponseWithoutDetailsDto {
    private Long id;
    private String passportNumber;
    private String fullname;
    private double creditScore;
    private String creditScoreGroup;
    private boolean bankCustomer;
    private List<Long> applicationIDs;


}
