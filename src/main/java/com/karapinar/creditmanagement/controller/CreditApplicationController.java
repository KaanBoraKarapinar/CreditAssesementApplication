package com.karapinar.creditmanagement.controller;


import com.karapinar.creditmanagement.ApplicantService;
import com.karapinar.creditmanagement.CreditApplicationService;
import com.karapinar.creditmanagement.CreditAssesementService;
import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.DTO.CreditApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.ApiResponse;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/application")
public class CreditApplicationController {

    final ApplicantService applicantService;
    final CreditApplicationService creditApplicationService;
    final CreditAssesementService creditAssesementService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse> applyCredit(@RequestBody CreditApplicationRequest request ){

        boolean applicantExists = applicantService.existsByPassportNumber(request.getPassportNumber());
        Applicant applicant;
        CreditApplication creditApplication;

        if (applicantExists) {
            applicant = applicantService.getApplicantBypassportNumber(request.getPassportNumber());
        } else {
            applicant = applicantService.createApplicant(request.getFullName(), request.getPassportNumber());
        }

        creditApplication = creditApplicationService.createApplication(
                applicant,
                request.getCreditAmount(),
                request.getRedirectingMarketplace()
        );

        creditAssesementService.startProcess(creditApplication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Credit application started successfully", "ID:" + creditApplication.getId()));


    }

    @GetMapping("/getStatusOfLatestApplication")
    public ResponseEntity<ApiResponse> getStatusOfLatestApplication(@RequestParam String passportNumber,
                                                                    @RequestParam Long creditAmount){

        //with form
        if(!applicantService.existsByPassportNumber(passportNumber)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is no applicant with test77 given passportnumber", "PASSNR:" + passportNumber));
        }
        Applicant applicant = applicantService.getApplicantBypassportNumber(passportNumber);
        CreditApplication cA = applicant.getApplications().getLast();

        if(!Objects.equals(cA.getCreditAmount(), creditAmount)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Applicant's last credit application and the credit amount doesnt match.", "PASSNR:" + passportNumber));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("",cA.getStatus()));



    }



}
