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
			/*Applicant applicant = applicantService.createApplicant("Kaan Bora 3" );


			CreditApplication application = creditApplicationService.createApplication(applicant,703);
			assesementService.startProcess(application);

			System.out.println(applicant.toString());
			System.out.println(application.toString());

			Applicant applicant1 = applicantService.createApplicant("Kaan Bora 4" );


			CreditApplication application1 = creditApplicationService.createApplication(applicant1,1703);
			assesementService.startProcess(application1);

			System.out.println(applicant1.toString());
			System.out.println(application1.toString());

			Applicant applicant2 = applicantService.createApplicant("Kaan Bora 5" );


			CreditApplication application2 = creditApplicationService.createApplication(applicant2,303);
			assesementService.startProcess(application2);

			System.out.println(applicant2.toString());
			System.out.println(application2.toString());*/
		};




	}



}
