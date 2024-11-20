package com.karapinar.creditmanagement.controller;

import com.karapinar.creditmanagement.ApplicantService;
import com.karapinar.creditmanagement.CreditApplicationService;
import com.karapinar.creditmanagement.CreditAssesementService;
import com.karapinar.creditmanagement.DTO.ApplicantResponseWithDetailsDto;
import com.karapinar.creditmanagement.DTO.ApplicantResponseWithoutDetailsDto;
import com.karapinar.creditmanagement.DTO.CreditApplicationResponseDto;
import com.karapinar.creditmanagement.DTO.DeveloperAllDetailsApplicationRequest;
import com.karapinar.creditmanagement.config.ModelMapperConfig;
import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.model.Status;
import com.karapinar.creditmanagement.repository.ApplicantRepository;
import com.karapinar.creditmanagement.repository.CreditApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.ApiResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/developer")
public class DeveloperServiceAndController {

    private final CreditApplicationRepository creditApplicationRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantService applicantService;
    private final CreditApplicationService creditApplicationService;
    private final CreditAssesementService creditAssesementService;
    private final ModelMapper modelMapper;

    public Applicant createApplicant(String fullname,String passportNumber, Double creditScore, Boolean isBankCustomer) {



        Applicant applicant = new Applicant("TEST_"+ fullname,passportNumber,creditScore,isBankCustomer);

        return applicantRepository.save(applicant);
    }

    public CreditApplication createApplication(Applicant applicant, Long creditAmount, String redirectingMarketplace){

        CreditApplication creditApplication = new CreditApplication(applicant, creditAmount, Status.ACTIVE, redirectingMarketplace);
        applicant.getApplications().add(creditApplication);
        return creditApplicationRepository.save(creditApplication);

    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse>  applyWithAllDetails(@RequestBody DeveloperAllDetailsApplicationRequest allDetails){


        boolean applicantExists = applicantService.existsByPassportNumber(allDetails.getPassportNumber());
        Applicant applicant;

        if (applicantExists) {
            applicant = applicantService.getApplicantBypassportNumber(allDetails.getPassportNumber());
        } else {
            applicant = createApplicant(allDetails.getFullName(), allDetails.getPassportNumber(),allDetails.getCreditScore(),allDetails.getIsBankCustomer());
        }
        CreditApplication creditApplication = createApplication(applicant, allDetails.getCreditAmount(), allDetails.getRedirectingMarketplace());

        creditAssesementService.startProcess(creditApplication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Credit application started successfully", "ID:" + creditApplication.getId()));

    }

    @GetMapping("/applications/{id}")
    public CreditApplicationResponseDto getApplicationDetailsByApplicationId(@PathVariable("id") Long id) {

        CreditApplication creditApplication = creditApplicationService.getCreditApplicationById(id);
        return modelMapper.map(creditApplication, CreditApplicationResponseDto.class);
    }

    @GetMapping("/applicants/{id}")
    public ApplicantResponseWithDetailsDto getApplicantDetailsByApplicationId(@PathVariable("id") Long id) {

        Applicant applicant = applicantService.getApplicantById(id);
        return modelMapper.map(applicant, ApplicantResponseWithDetailsDto.class);
    }

    @GetMapping("/applications")
    public List<CreditApplicationResponseDto> getAllApplicationDetails() {

        List<CreditApplication> creditApplications = creditApplicationRepository.findAll();
        return creditApplications.stream()
                .map(application -> modelMapper.map(application, CreditApplicationResponseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/applicants")
    public List<ApplicantResponseWithoutDetailsDto> getAllApplicantDetails() {

        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream()
                .map(applicant -> modelMapper.map(applicant, ApplicantResponseWithoutDetailsDto.class))
                .collect(Collectors.toList());
    }



}
