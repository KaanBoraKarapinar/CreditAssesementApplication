package com.karapinar.creditmanagement.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.flowable.engine.runtime.ProcessInstance;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;


@Entity
public class CreditApplication {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    @Getter
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private final Applicant applicant;

    @Getter
    @Setter
    private final String redirectingMarketplace;

    @Getter
    private final Long creditAmount;

    @Setter
    @Getter
    private String processInstanceId;




    @Enumerated(EnumType.STRING)
    @Getter
    private Status status;




    public CreditApplication(Applicant applicant, long creditAmount, Status status, String redirectingMarketplace) {
        this.applicant = applicant;
        this.creditAmount = creditAmount;
        this.status = status;
        this.redirectingMarketplace = redirectingMarketplace;
    }

    public CreditApplication() {
        this.redirectingMarketplace = null;
        //error handling for jpa:
        this.applicant = null;
        this.creditAmount = null;
    }

    public void setStatus(Status status) {

        if (this.status == Status.REJECTED || this.status == Status.APPROVED){
            throw new RuntimeException("Rejected or Approved applications can not be changed");
        }
        this.status = status;
    }




}


