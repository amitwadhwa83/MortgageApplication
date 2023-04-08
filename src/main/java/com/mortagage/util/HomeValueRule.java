package com.mortagage.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class HomeValueRule implements EligibilityRule {
    @Override
    public boolean isEligible(BigDecimal income, BigDecimal loanValue, BigDecimal homeValue) {
        return homeValue.compareTo(loanValue) >= 0;
    }
}
