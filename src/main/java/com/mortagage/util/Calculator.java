package com.mortagage.util;

import com.mortagage.domain.Rate;

import java.math.BigDecimal;

public class Calculator {

    final static int MONTHS_IN_YEAR = 12;
    final static int PERCENT = 100;

    public static BigDecimal calculateMortgageCost(BigDecimal loanValue, int period, Rate rate) {
        BigDecimal principal = loanValue;
        double monthlyInterest = rate.getInterestRate() / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = period * MONTHS_IN_YEAR;
        double mathPower = Math.pow(1 + monthlyInterest, numberOfPayments);
        BigDecimal monthlyPayment = principal.multiply(BigDecimal.valueOf((monthlyInterest * mathPower / (mathPower - 1))));
        return monthlyPayment;
    }
}
