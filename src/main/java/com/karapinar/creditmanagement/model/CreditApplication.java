package com.karapinar.creditmanagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
public class CreditApplication {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    @Getter
    private final Applicant applicant;
    ;

    @Getter
    private final BigDecimal creditAmount;




    @Enumerated(EnumType.STRING)
    @Getter
    private Status status;


    public CreditApplication(Applicant applicant, BigDecimal creditAmount, Status status) {
        this.applicant = applicant;
        this.creditAmount = creditAmount;
        this.status = status;
    }

    public CreditApplication() {
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


