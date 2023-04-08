package com.mortagage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortagage.domain.EligibilityCheck;
import com.mortagage.domain.EligibilityReport;
import com.mortagage.repository.RateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class MortgageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RateRepository repository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("When all rates are requested then they are all returned")
    void allRatesRequested() throws Exception {
        mockMvc
                .perform(get("/api/interest-rates"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize((int) repository.count())));
    }

    @Test
    @DisplayName("When mortgage-check is requested then it's correctly calculated")
    void mortageCheckCorrect() throws Exception {
        EligibilityCheck eligibilityCheck = new EligibilityCheck(BigDecimal.valueOf(100),
                12, BigDecimal.valueOf(10), BigDecimal.valueOf(10));
        EligibilityReport eligibilityReport =
                mapper
                        .readValue(
                                mockMvc
                                        .perform(
                                                post("/api/mortgage-check")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(mapper.writeValueAsString(eligibilityCheck)))
                                        .andExpect(status().is2xxSuccessful())
                                        .andExpect(jsonPath("isEligible", equalTo(true)))
                                        .andExpect(jsonPath("mortgageCost", closeTo(BigDecimal.valueOf(0.001), BigDecimal.valueOf(1))))
                                        .andReturn()
                                        .getResponse()
                                        .getContentAsString(),
                                EligibilityReport.class);
        assertNotNull(eligibilityReport.mortgageCost());
        assertTrue(eligibilityReport.isEligible());
    }
}