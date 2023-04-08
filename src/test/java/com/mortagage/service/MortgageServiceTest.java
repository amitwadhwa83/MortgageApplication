package com.mortagage.service;

import com.mortagage.domain.EligibilityCheck;
import com.mortagage.domain.EligibilityReport;
import com.mortagage.domain.Rate;
import com.mortagage.exception.RateNotFoundException;
import com.mortagage.repository.RateRepository;
import com.mortagage.util.EligibilityRules;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MortgageServiceTest {
    RateRepository rateRepository = mock(RateRepository.class);
    EligibilityRules eligibilityRules = mock(EligibilityRules.class);
    MortgageService mortgageService = new MortgageServiceImpl(rateRepository, eligibilityRules);
    Random random = new Random();

    @Test
    @DisplayName("When all rates are requested then they are all returned")
    public void expectRatesListForListRate() {
        when(rateRepository.findAll()).thenReturn(aListOfRate());
        List<Rate> response = mortgageService.listRate();
        verify(rateRepository, times(1)).findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    @DisplayName("When eligibility is requested with empty input then will get the default eligibility rate")
    public void expectDefaultReportForEligibilityCheckWithEmptyInput() throws RateNotFoundException {
        assertTrue(mortgageService.checkEligibility(null).equals(EligibilityReport.DEFAULT));
    }

    @Test
    @DisplayName("When eligibility is requested with business rules violating input then will get the default eligibility rate")
    public void expectDefaultEligibiltyReportForEligibilityCheckForRulesViolations() throws RateNotFoundException {
        when(eligibilityRules.check(any(), any(), any())).thenReturn(false);
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(10),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        assertTrue(mortgageService.checkEligibility(eligibilityCheck).equals(EligibilityReport.DEFAULT));
    }

    @Test
    @DisplayName("When eligibility is requested with input passing business rules then will get report with a rate")
    public void expectSuccessfulEligibiltyReportForEligibilityCheckForValidInput() throws RateNotFoundException {
        when(eligibilityRules.check(any(), any(), any())).thenReturn(true);
        Rate rate = aRandomRate();
        when(rateRepository.findByMaturityPeriod(anyInt())).thenReturn(Optional.of(rate));
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(40),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        EligibilityReport response = mortgageService.checkEligibility(eligibilityCheck);
        assertFalse(response.equals(EligibilityReport.DEFAULT));
        assertNotNull(response.mortgageCost());
        assertTrue(response.isEligible());
    }

    @Test(expected = RateNotFoundException.class)
    @DisplayName("When eligibility is requested with non existent maturity period then will fail with exception")
    public void expectExceptionForEligibleCheckWhenRateDoesNotExist() throws RateNotFoundException {
        when(eligibilityRules.check(any(), any(), any())).thenReturn(true);
        mortgageService.checkEligibility(new EligibilityCheck(BigDecimal.valueOf(40),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10)));
    }

    private List<Rate> aListOfRate() {
        return List.of(new Rate(), aRandomRate());
    }

    private Rate aRandomRate() {
        return new Rate(random.nextInt(2), random.nextDouble(4)
                , LocalDateTime.now());
    }
}