package com.karapinar.creditmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {


    public Applicant(String fullname, String passportNumber){

        this.fullname = fullname;
        this.passportNumber = passportNumber;
    }

    //todo: DELETE, FOR DEBUG PURPOSES
    public Applicant(String fullname, String passportNumber, double creditscore, boolean bankCustomer){
        this.fullname = fullname;
        this.passportNumber = passportNumber;
        this.setCreditScore(creditscore);
        this.bankCustomer = bankCustomer;
        this.developerObject = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    private String fullname;

    private double creditScore;

    @Enumerated(EnumType.STRING)
    private CreditScoreGroup creditScoreGroup;

    private boolean bankCustomer;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<CreditApplication> applications = new ArrayList<>();


    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
        this.creditScoreGroup= CreditScoreGroup.getGroupForRisk(creditScore);

    }

    public void setCreditScoreGroup(CreditScoreGroup creditScoreGroup) {
        throw new RuntimeException("Credit score group can not be set manually, please use setcreditscore");
    }

    @Getter
    private boolean developerObject = false;




}
