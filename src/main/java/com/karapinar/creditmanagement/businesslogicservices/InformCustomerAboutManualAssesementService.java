package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import com.karapinar.creditmanagement.model.Status;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

public class InformCustomerAboutManualAssesementService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("manual assesement");

        Map<String,Object> map = execution.getVariables();
        CreditApplication application = (CreditApplication) map.get("creditApplicationJavaObject");

        application.setStatus(Status.INMANUALCONTROL);
        execution.setVariables(map);


    }

}
