package com.mortagage.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record EligibilityCheck(BigDecimal income, int maturityPeriod, BigDecimal loanValue, BigDecimal homeValue) {
    public EligibilityCheck {
        Objects.requireNonNull(income, "Income cannot be null");
        Objects.requireNonNull(loanValue, "Loan Value cannot be null");
        Objects.requireNonNull(homeValue, "Home Value cannot be null");
    }
}
