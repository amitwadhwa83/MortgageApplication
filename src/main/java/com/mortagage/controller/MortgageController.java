package com.mortagage.controller;

import com.mortagage.domain.EligibilityCheck;
import com.mortagage.domain.EligibilityReport;
import com.mortagage.domain.Rate;
import com.mortagage.exception.RateNotFoundException;
import com.mortagage.service.MortgageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MortgageController {

    @Autowired
    private MortgageService mortgageService;

    @GetMapping("interest-rates")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of current interest rates")
    public List<Rate> listRate() {
        return mortgageService.listRate();
    }

    @PostMapping("mortgage-check")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Post the parameters to calculate for a mortgage check")
    public EligibilityReport checkEligibility(@RequestBody EligibilityCheck eligibilityCheck) throws RateNotFoundException {
        return mortgageService.checkEligibility(eligibilityCheck);
    }
}