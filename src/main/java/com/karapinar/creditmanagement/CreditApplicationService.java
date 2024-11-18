package com.karapinar.creditmanagement;

import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.model.Status;
import com.karapinar.creditmanagement.repository.CreditApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;

    public CreditApplication getCreditApplicationById(Long applicantId){
        return creditApplicationRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("No applicant found"));

    }

    public CreditApplication createApplication(Applicant applicant, BigDecimal creditAmount){

        CreditApplication creditApplication = new CreditApplication(applicant, creditAmount, Status.ACTIVE);
        return creditApplicationRepository.save(creditApplication);

    }



    public void ApproveCreditApplicationById(Long applicationId){

        CreditApplication application = creditApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("No applicantion found"));

        application.setStatus(Status.APPROVED);
    }

    public void RejectCreditApplicationById(Long applicationId){
        CreditApplication application = creditApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("No applicantion found"));
        application.setStatus(Status.REJECTED);
    }

    public void SendCreditApplicationToManualCheckById(Long applicationId){
        CreditApplication application = creditApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("No applicantion found"));
        application.setStatus(Status.INMANUALCONTROL);
    }





}
