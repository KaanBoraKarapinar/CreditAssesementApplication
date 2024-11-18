package com.karapinar.creditmanagement;


import com.karapinar.creditmanagement.model.CreditApplication;
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
        variables.put("creditApplication", creditApplication);

        runtimeService.startProcessInstanceByKey("creditApproval", variables);


    }




}
