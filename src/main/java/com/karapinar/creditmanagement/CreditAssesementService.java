package com.karapinar.creditmanagement;


import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.model.CreditScoreGroup;
import jakarta.transaction.Transactional;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreditAssesementService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Transactional
    public void startProcess(CreditApplication creditApplication) {
       Map<String, Object> variables = new HashMap<>();
        variables.put("creditApplicationJavaObject", creditApplication);

        runtimeService.startProcessInstanceByKey("creditApproval", variables);


    }

    public boolean isCreditAmountHigherThenSoftAssesementRules(CreditApplication creditApplication){

        final int limit = 1000;

        return creditApplication.getCreditAmount() >= 1000;

    }


    public boolean isCreditScoreSufficient(CreditApplication creditApplication,
                                           CreditScoreGroup lowerBorder,
                                           CreditScoreGroup upperBorder) {
        return CreditScoreGroup.isBetween(creditApplication.getApplicant().getCreditScoreGroup(), lowerBorder, upperBorder);
    }

    //** soft assesement, when under the limit
    public boolean isCreditScoreSufficientForAutoApproveForSoftAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.A, CreditScoreGroup.H);
    }

    public boolean isCreditScoreSufficientForManualCheckForSoftAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.I, CreditScoreGroup.M);
    }

    public boolean isCreditScoreNotSufficientForSoftAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.N, CreditScoreGroup.P);
    }

    public boolean isCreditScoreSufficientForAutoApproveForStrictAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.A, CreditScoreGroup.H);
    }

    public boolean isCreditScoreSufficientForManualCheckForStrictAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.I, CreditScoreGroup.K);
    }

    public boolean isCreditScoreNotSufficientForStrictAssesement(CreditApplication creditApplication) {
        return isCreditScoreSufficient(creditApplication, CreditScoreGroup.L, CreditScoreGroup.P);
    }






}
