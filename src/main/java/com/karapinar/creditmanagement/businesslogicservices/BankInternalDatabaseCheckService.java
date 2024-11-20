package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Random;

public class BankInternalDatabaseCheckService implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        System.out.println("bank intern");
        Random random = new Random();

        Map<String,Object> map = execution.getVariables();
        CreditApplication application = (CreditApplication) map.get("creditApplicationJavaObject");

        boolean isDeveloperObject = application.getApplicant().isDeveloperObject();

        if(isDeveloperObject){
            return;
        } //Changes after that will not be saved for developer objects

        int intvalue = random.nextInt(0,2);

        if(intvalue == 1){

        }else{
            application.getApplicant().setBankCustomer(false);
        }
        execution.setVariables(map);





    }

}
