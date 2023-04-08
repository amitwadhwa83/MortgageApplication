package com.mortagage.util;

import java.math.BigDecimal;

public interface EligibilityRule {
    boolean isEligible(BigDecimal income, BigDecimal loanValue, BigDecimal homeValue);
}
