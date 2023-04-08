package com.mortagage.domain;

import java.math.BigDecimal;

public record EligibilityReport(boolean isEligible, BigDecimal mortgageCost) {
    public static final EligibilityReport DEFAULT = new EligibilityReport(false, BigDecimal.ZERO);
}
