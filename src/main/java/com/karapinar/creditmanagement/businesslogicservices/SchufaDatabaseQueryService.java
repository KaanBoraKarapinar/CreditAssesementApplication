package com.karapinar.creditmanagement.businesslogicservices;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SchufaDatabaseQueryService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("schufadb");
        System.out.println(execution.toString());

    }

}
