package com.karapinar.creditmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {


    public Applicant(String fullname, boolean isBankCustomer){

        this.fullname = fullname;
        this.bankCustomer = isBankCustomer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private double creditScore;

    @Enumerated(EnumType.STRING)
    private CreditScoreGroup creditScoreGroup;

    private boolean bankCustomer;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditApplication> applications;


    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
        this.creditScoreGroup= CreditScoreGroup.getGroupForRisk(creditScore);

    }

    public void setCreditScoreGroup(CreditScoreGroup creditScoreGroup) {
        throw new RuntimeException("Credit score group can not be set manually, please use setcreditscore");
    }
}
