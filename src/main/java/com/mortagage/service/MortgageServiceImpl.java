package com.mortagage.service;

import com.mortagage.domain.EligibilityCheck;
import com.mortagage.domain.EligibilityReport;
import com.mortagage.domain.Rate;
import com.mortagage.exception.RateNotFoundException;
import com.mortagage.repository.RateRepository;
import com.mortagage.util.EligibilityRules;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.mortagage.util.Calculator.calculateMortgageCost;

@Service
public class MortgageServiceImpl implements MortgageService {

    private final RateRepository rateRepository;
    private final EligibilityRules eligibilityRules;

    public MortgageServiceImpl(RateRepository rateRepository, EligibilityRules eligibilityRules) {
        this.rateRepository = rateRepository;
        this.eligibilityRules = eligibilityRules;
    }

    @Override
    public List<Rate> listRate() {
        return rateRepository.findAll();
    }

    @Override
    public EligibilityReport checkEligibility(EligibilityCheck check) throws RateNotFoundException {
        if (check == null) return EligibilityReport.DEFAULT;

        boolean isEligible = eligibilityRules.check(check.income(), check.loanValue(), check.homeValue());
        if (!isEligible) return new EligibilityReport(false, BigDecimal.ZERO);

        int period = check.maturityPeriod();
        Rate rate = rateRepository.findByMaturityPeriod(period)
                .orElseThrow(() -> new RateNotFoundException(period));

        BigDecimal mortgageCost = calculateMortgageCost(check.loanValue(), period, rate);
        return new EligibilityReport(true, mortgageCost);
    }
}