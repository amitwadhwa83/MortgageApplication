package com.mortagage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class EligibilityRules {
    @Autowired
    List<EligibilityRule> rules;

    public boolean check(BigDecimal income, BigDecimal loanValue, BigDecimal homeValue) {
        boolean isNotEligible = rules.stream()
                .anyMatch(eligibilityRule -> !eligibilityRule.isEligible(income, loanValue, homeValue));
        return !isNotEligible;
    }
}