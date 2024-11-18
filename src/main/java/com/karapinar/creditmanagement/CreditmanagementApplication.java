package com.karapinar.creditmanagement;

import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.model.CreditApplication;
import org.flowable.spring.boot.app.App;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CreditmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditmanagementApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(CreditApplicationService creditApplicationService,ApplicantService applicantService, CreditAssesementService assesementService){

		return args -> {
			Applicant applicant = applicantService.createApplicant("Kaan Bora Karapinar2",true);


			CreditApplication application = creditApplicationService.createApplication(applicant,new BigDecimal(701));
			assesementService.startProcess(application);
		};




	}



}
