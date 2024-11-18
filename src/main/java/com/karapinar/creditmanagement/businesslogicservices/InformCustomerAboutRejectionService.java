package com.karapinar.creditmanagement.businesslogicservices;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class InformCustomerAboutRejectionService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("reject");


    }

}
