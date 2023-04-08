package com.mortagage.domain;

import java.math.BigDecimal;

public record EligibilityCheck(BigDecimal income, int maturityPeriod, BigDecimal loanValue, BigDecimal homeValue) {
}
