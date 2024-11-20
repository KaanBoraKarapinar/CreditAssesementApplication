package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Random;

public class CreditLimitCheckService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("credit limit");







    }

}
