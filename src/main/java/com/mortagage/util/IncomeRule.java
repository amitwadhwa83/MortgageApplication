package com.mortagage.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class IncomeRule implements EligibilityRule {
    @Override
    public boolean isEligible(BigDecimal income, BigDecimal loanValue, BigDecimal homeValue) {
        return income.compareTo(loanValue.multiply(BigDecimal.valueOf(4))) >= 0;
    }
}
