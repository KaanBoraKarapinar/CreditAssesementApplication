package com.karapinar.creditmanagement.businesslogicservices;

import com.karapinar.creditmanagement.model.CreditApplication;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;


public class AssesCreditAmountAndRecordService implements JavaDelegate {

    /**
     * Updates variable set of Flowable
     * @param execution
     */
    public void execute(DelegateExecution execution) {

        Map<String,Object> map = execution.getVariables();
        CreditApplication application = (CreditApplication) map.get("creditApplicationJavaObject");

        application.setProcessInstanceId(execution.getProcessInstanceId());
        execution.setVariables(map);

        boolean isDeveloperObject = application.getApplicant().isDeveloperObject();

        if(isDeveloperObject){
            return;
        } //Changes after that will not be saved for developer objects





    }

}
