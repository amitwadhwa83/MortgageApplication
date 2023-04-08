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
    @DisplayName("When invoked will list all available current interest rates")
    public void expectRatesListForListRate() {
        //Given
        when(rateRepository.findAll()).thenReturn(aListOfRates());
        //When
        List<Rate> response = mortgageService.listRate();
        //Then
        verify(rateRepository, times(1)).findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    @DisplayName("When invoked will get the default eligibility rate for a mortgage check")
    public void expectDefaultReportForEligibilityCheckWithEmptyInput() throws RateNotFoundException {
        //When
        EligibilityReport response = mortgageService.checkEligibility(null);
        //Then
        assertTrue(response.equals(EligibilityReport.DEFAULT));
    }

    @Test
    @DisplayName("When invoked will get default/failed eligibility rate for a mortgage check")
    public void expectDefaultEligibiltyReportForEligibilityCheckForRulesViolations() throws RateNotFoundException {
        //Given
        when(eligibilityRules.check(any(), any(), any())).thenReturn(false);
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(10),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        //When
        EligibilityReport response = mortgageService.checkEligibility(eligibilityCheck);
        //Then
        assertTrue(response.equals(EligibilityReport.DEFAULT));
    }

    @Test
    @DisplayName("When invoked will get eligibility passed report with a proper rate for a mortgage check")
    public void expectSuccessfulEligibiltyReportForEligibilityCheckForValidInput() throws RateNotFoundException {
        //Given
        when(eligibilityRules.check(any(), any(), any())).thenReturn(true);
        Rate rate = aRate();
        when(rateRepository.findByMaturityPeriod(anyInt())).thenReturn(Optional.of(rate));
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(40),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        //When
        EligibilityReport response = mortgageService.checkEligibility(eligibilityCheck);
        //Then
        assertFalse(response.equals(EligibilityReport.DEFAULT));
        assertNotNull(response.mortgageCost());
        assertTrue(response.isEligible());
    }

    @Test(expected = RateNotFoundException.class)
    @DisplayName("When invoked will fail for rate not found exception for a mortgage check")
    public void expectExceptionForEligibleCheckWhenRateDoesNotExist() throws RateNotFoundException {
        //Given
        when(eligibilityRules.check(any(), any(), any())).thenReturn(true);
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(40),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        //When
        mortgageService.checkEligibility(eligibilityCheck);
        //Then
        // expect exception
    }

    private List<Rate> aListOfRates() {
        return List.of(new Rate(), aRate());
    }

    private Rate aRate() {
        return new Rate(random.nextInt(2), random.nextDouble(4)
                , LocalDateTime.now());
    }
}