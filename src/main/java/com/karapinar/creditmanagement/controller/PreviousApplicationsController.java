package com.karapinar.creditmanagement.controller;

import lombok.AllArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PreviousApplicationsController {

    @Autowired
    final RuntimeService runtimeService;



}
