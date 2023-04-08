package com.mortagage.service;

import com.mortagage.domain.EligibilityCheck;
import com.mortagage.domain.EligibilityReport;
import com.mortagage.domain.Rate;
import com.mortagage.exception.RateNotFoundException;

import java.util.List;

public interface MortgageService {
    List<Rate> listRate();
    EligibilityReport checkEligibility(EligibilityCheck eligibilityCheck) throws RateNotFoundException;
}
