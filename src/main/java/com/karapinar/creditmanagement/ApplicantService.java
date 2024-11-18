package com.karapinar.creditmanagement;

import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public Applicant getApplicantById(Long applicantId){
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("No applicant found"));

    }

    public Applicant getApplicantByFullName(String fullName){

        return applicantRepository.findByFullname(fullName)
                .orElseThrow(() -> new RuntimeException("Applicant not found with fullname: " + fullName));
    }

    public List<Applicant> searchApplicantsByName(String name){

        return applicantRepository.findByFullnameContaining(name);

    }

    public Applicant createApplicant(String fullname, boolean isBankCustomer) {

        Applicant applicant = new Applicant(fullname, isBankCustomer);


        return applicantRepository.save(applicant);
    }

    public void setApplicantCreditScore(Long applicantId, double creditScore){


        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() -> new RuntimeException("No such applicant") );
        applicant.setCreditScore(creditScore);

    }

    public List<CreditApplication> getAllApplicationsOfApplicant(Long applicantId){

        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() -> new RuntimeException("No such applicant") );
        return applicant.getApplications();
    }





}