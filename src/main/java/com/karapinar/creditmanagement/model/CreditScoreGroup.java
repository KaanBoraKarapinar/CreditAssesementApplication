package com.karapinar.creditmanagement.model;

import lombok.extern.slf4j.Slf4j;

public enum CreditScoreGroup {

    A(1, 0.0, 0.14),
    B(2, 0.15, 0.35),
    C(3, 0.36, 0.53),
    D(4, 0.54, 0.66),
    E(5, 0.67, 0.86), // AND -> E olarak değiştirildi
    F(6, 0.87, 1.22),
    G(7, 1.23, 2.34),
    H(8, 2.35, 5.30),
    I(9, 5.31, 7.73),
    K(10, 7.74, 10.26),
    L(11, 10.27, 15.81),
    M(12, 15.82, 35.94),
    N(13, 35.95, 49.16),
    O(14, 49.17, 88.72),
    P(15, 88.73, 99.54);

    private final int intRangValue;
    private final double min;
    private final double max;


    // Constructor
    CreditScoreGroup(int intRangValue, double min, double max) {
        this.intRangValue = intRangValue;
        this.min = min;
        this.max = max;

    }



    public boolean isInRange(double creditScore) {
        return creditScore >= min && creditScore <= max;
    }


    public static CreditScoreGroup getGroupForRisk(double creditScore) {
        for (CreditScoreGroup group : CreditScoreGroup.values()) {
            if (group.isInRange(creditScore)) {
                return group;
            }
        }
        throw new IllegalArgumentException("Invalid risk quota: " + creditScore);
    }

    /**
     *
     * Returns whether query between min and max groups
     * @param query min <= QUERY <= max
     * @param min INCLUSIVE
     * @param max INCLUSIVE
     * @return
     */
    public static boolean isBetween(CreditScoreGroup query, CreditScoreGroup min, CreditScoreGroup max){

        if(min.intRangValue >= max.intRangValue){
            throw new RuntimeException("Bad isbetween borders");
        }
        boolean minCheck = query.intRangValue >= min.intRangValue;
        boolean maxCheck = query.intRangValue <= max.intRangValue;

        return minCheck && maxCheck;

    }


}
