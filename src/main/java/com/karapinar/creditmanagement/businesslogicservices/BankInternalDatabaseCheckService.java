package com.karapinar.creditmanagement.businesslogicservices;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Random;

public class BankInternalDatabaseCheckService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("bank intern");
        Random random = new Random();

        int intvalue = random.nextInt(0,2);



    }

}
