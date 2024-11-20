package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Random;

public class SchufaDatabaseQueryService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("schufadb");
        Random random = new Random();

        Map<String,Object> map = execution.getVariables();
        CreditApplication application = (CreditApplication) map.get("creditApplicationJavaObject");

        boolean isDeveloperObject = application.getApplicant().isDeveloperObject();

        if(isDeveloperObject){
            return;
        } //Changes after that will not be saved for developer objects

        int value;

        double probability = random.nextDouble();

        if (probability < 0.5) {

            value = random.nextInt(10) + 1; // [1, 10]
        } else {

            value = random.nextInt(90) + 11; // [11, 100]
        }

        application.getApplicant().setCreditScore(value);
        execution.setVariables(map);


    }

}
