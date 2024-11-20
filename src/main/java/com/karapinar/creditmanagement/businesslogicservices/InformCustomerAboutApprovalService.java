package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.model.Status;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

public class InformCustomerAboutApprovalService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        Map<String,Object> map = execution.getVariables();
        CreditApplication application = (CreditApplication) map.get("creditApplicationJavaObject");

        application.setStatus(Status.APPROVED);
        execution.setVariables(map);




    }

}
