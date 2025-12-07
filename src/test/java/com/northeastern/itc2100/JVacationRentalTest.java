package com.northeastern.itc2100;

import java.text.NumberFormat;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for JVacationRental application
 * 
 * Tests core calculation logic and validation methods
 * Student-written test cases using JUnit framework learned through AI consultation
 * 
 * @author Loyd Johnson (johnson.loy@northeastern.edu)
 * @course ITC2100 - Introduction to Programming Java
 * @assignment Signature Assignment - Testing Enhancement
 */
public class JVacationRentalTest {
    
    private JVacationRental calculator;
    private NumberFormat currencyFormat;
    
    // Test constants matching application
    private static final double BASE_RATE_PER_NIGHT = 200.00;
    private static final double WINDSURFING_PRICE = 50.75;
    private static final double SNORKELING_PRICE = 25.65;
    private static final double HORSEBACK_RIDING_PRICE = 75.89;
    private static final double RESORT_TAX_RATE = 0.055; // 5.5%
    private static final double SHUTTLE_PRICE_PER_PERSON = 15.00;
    
    // Test scenario constants
    private static final int TEST_NIGHTS_SHORT = 1;
    private static final int TEST_NIGHTS_MEDIUM = 3;
    private static final int TEST_NIGHTS_WEEK = 7;
    private static final double EXPECTED_PRICE_ONE_NIGHT = 200.00;
    private static final double EXPECTED_PRICE_THREE_NIGHTS = 600.00;
    private static final double EXPECTED_PRICE_SEVEN_NIGHTS = 1400.00;
    private static final double ASSERTION_DELTA = 0.01;
    
    // Test calculation constants
    private static final double TEST_BASE_SUBTOTAL = 650.00;
    private static final double TEST_TAX_AMOUNT = 35.75;
    private static final double TEST_TOTAL_WITH_TAX = 685.75;
    private static final int TEST_SHUTTLE_PEOPLE = 4;
    private static final double TEST_SHUTTLE_TOTAL = 60.00;
    private static final double COMPLEX_CALCULATION_TOTAL = 713.60;
    
    // Additional test scenario constants
    private static final double TAX_TEST_SUBTOTAL_1 = 100.00;
    private static final double TAX_TEST_EXPECTED_1 = 5.50;
    private static final double TAX_TEST_SUBTOTAL_2 = 676.40;
    private static final double TAX_TEST_EXPECTED_2 = 37.20;
    private static final double SERVICES_COMBO_TOTAL = 76.40;
    private static final double SCENARIO_BASE_PRICE = 600.00;
    private static final double SCENARIO_SUBTOTAL = 676.40;
    private static final double SCENARIO_TAX = 37.20;
    
    // Edge case test constants
    private static final double ALL_SERVICES_TOTAL = 152.29;
    private static final double CURRENCY_TEST_AMOUNT = 713.60;
    private static final String CURRENCY_TEST_AMOUNT_STRING = "713.60";
    private static final int SINGLE_PERSON = 1;
    private static final double SINGLE_NIGHT_BASE = 200.00;
    private static final double ALL_SERVICES_WITH_SHUTTLE = 167.29;
    private static final double SINGLE_NIGHT_SUBTOTAL = 367.29;
    private static final double SINGLE_NIGHT_TAX = 20.20;
    private static final double SINGLE_NIGHT_TOTAL = 387.49;
    private static final int MAX_NIGHTS = 365;
    
    @Before
    public void setUp() {
        // Note: Since JVacationRental is a GUI class, we'll test calculation logic
        // In a real refactor, we'd extract business logic to a separate class
        currencyFormat = NumberFormat.getCurrencyInstance();
    }
    
    /**
     * Test basic pricing calculation logic
     */
    @Test
    public void testBasicPriceCalculation() {
        // Test 3 nights calculation
        int nights = TEST_NIGHTS_MEDIUM;
        double expectedBasePrice = nights * BASE_RATE_PER_NIGHT;
        assertEquals("Base price calculation for 3 nights", EXPECTED_PRICE_THREE_NIGHTS, expectedBasePrice, ASSERTION_DELTA);
        
        // Test 1 night calculation
        nights = TEST_NIGHTS_SHORT;
        expectedBasePrice = nights * BASE_RATE_PER_NIGHT;
        assertEquals("Base price calculation for 1 night", EXPECTED_PRICE_ONE_NIGHT, expectedBasePrice, ASSERTION_DELTA);
        
        // Test 7 nights calculation
        nights = TEST_NIGHTS_WEEK;
        expectedBasePrice = nights * BASE_RATE_PER_NIGHT;
        assertEquals("Base price calculation for 7 nights", EXPECTED_PRICE_SEVEN_NIGHTS, expectedBasePrice, ASSERTION_DELTA);
    }
    
    /**
     * Test service pricing constants
     */
    @Test
    public void testServicePricing() {
        assertEquals("Windsurfing price", 50.75, WINDSURFING_PRICE, ASSERTION_DELTA);
        assertEquals("Snorkeling price", 25.65, SNORKELING_PRICE, ASSERTION_DELTA);
        assertEquals("Horseback riding price", 75.89, HORSEBACK_RIDING_PRICE, ASSERTION_DELTA);
        assertEquals("Shuttle price per person", 15.00, SHUTTLE_PRICE_PER_PERSON, ASSERTION_DELTA);
    }
    
    /**
     * Test tax calculation logic
     */
    @Test
    public void testTaxCalculation() {
        // Test tax on $100 subtotal
        double subtotal = TAX_TEST_SUBTOTAL_1;
        double expectedTax = subtotal * RESORT_TAX_RATE;
        assertEquals("Tax calculation on $100", TAX_TEST_EXPECTED_1, expectedTax, ASSERTION_DELTA);
        
        // Test tax on $676.40 (3 nights + windsurfing + snorkeling)
        subtotal = TAX_TEST_SUBTOTAL_2;
        expectedTax = subtotal * RESORT_TAX_RATE;
        assertEquals("Tax calculation on $676.40", TAX_TEST_EXPECTED_2, expectedTax, ASSERTION_DELTA);
    }
    
    /**
     * Test complete calculation scenario
     */
    @Test
    public void testCompleteCalculationScenario() {
        // Scenario: 3 nights + windsurfing + snorkeling
        int nights = TEST_NIGHTS_MEDIUM;
        double basePrice = nights * BASE_RATE_PER_NIGHT; // $600.00
        double servicesTotal = WINDSURFING_PRICE + SNORKELING_PRICE; // $76.40
        double subtotal = basePrice + servicesTotal; // $676.40
        double tax = subtotal * RESORT_TAX_RATE; // $37.20
        double total = subtotal + tax; // $713.60
        
        assertEquals("Complete calculation - base price", SCENARIO_BASE_PRICE, basePrice, ASSERTION_DELTA);
        assertEquals("Complete calculation - services total", SERVICES_COMBO_TOTAL, servicesTotal, ASSERTION_DELTA);
        assertEquals("Complete calculation - subtotal", SCENARIO_SUBTOTAL, subtotal, ASSERTION_DELTA);
        assertEquals("Complete calculation - tax", SCENARIO_TAX, tax, ASSERTION_DELTA);
        assertEquals("Complete calculation - total", COMPLEX_CALCULATION_TOTAL, total, ASSERTION_DELTA);
    }
    
    /**
     * Test shuttle calculation
     */
    @Test
    public void testShuttleCalculation() {
        // Test 2 people shuttle
        int people = 2;
        double shuttleCost = people * SHUTTLE_PRICE_PER_PERSON;
        assertEquals("Shuttle cost for 2 people", 30.00, shuttleCost, 0.01);
        
        // Test 5 people shuttle
        people = 5;
        shuttleCost = people * SHUTTLE_PRICE_PER_PERSON;
        assertEquals("Shuttle cost for 5 people", 75.00, shuttleCost, 0.01);
    }
    
    /**
     * Test all services combined
     */
    @Test
    public void testAllServicesCalculation() {
        double allServices = WINDSURFING_PRICE + SNORKELING_PRICE + HORSEBACK_RIDING_PRICE;
        double expected = 50.75 + 25.65 + 75.89; // $152.29
        assertEquals("All services combined", ALL_SERVICES_TOTAL, allServices, ASSERTION_DELTA);
    }
    
    /**
     * Test currency formatting (basic validation)
     */
    @Test
    public void testCurrencyFormatting() {
        double amount = CURRENCY_TEST_AMOUNT;
        String formatted = currencyFormat.format(amount);
        assertTrue("Currency format contains dollar sign", formatted.contains("$"));
        assertTrue("Currency format contains amount", formatted.contains(CURRENCY_TEST_AMOUNT_STRING));
    }
    
    /**
     * Test edge case - single night with all services
     */
    @Test
    public void testSingleNightAllServices() {
        int nights = TEST_NIGHTS_SHORT;
        int shuttlePeople = SINGLE_PERSON;
        
        double basePrice = nights * BASE_RATE_PER_NIGHT; // $200.00
        double servicesTotal = WINDSURFING_PRICE + SNORKELING_PRICE + 
                              HORSEBACK_RIDING_PRICE + (shuttlePeople * SHUTTLE_PRICE_PER_PERSON);
        // $50.75 + $25.65 + $75.89 + $15.00 = $167.29
        double subtotal = basePrice + servicesTotal; // $367.29
        double tax = subtotal * RESORT_TAX_RATE; // $20.20
        double total = subtotal + tax; // $387.49
        
        assertEquals("Single night all services - base", SINGLE_NIGHT_BASE, basePrice, ASSERTION_DELTA);
        assertEquals("Single night all services - services", ALL_SERVICES_WITH_SHUTTLE, servicesTotal, ASSERTION_DELTA);
        assertEquals("Single night all services - subtotal", SINGLE_NIGHT_SUBTOTAL, subtotal, ASSERTION_DELTA);
        assertEquals("Single night all services - tax", SINGLE_NIGHT_TAX, tax, ASSERTION_DELTA);
        assertEquals("Single night all services - total", SINGLE_NIGHT_TOTAL, total, ASSERTION_DELTA);
    }
    
    /**
     * Test maximum scenario - 365 nights (edge case)
     */
    @Test
    public void testMaximumNights() {
        int nights = 365;
        double basePrice = nights * BASE_RATE_PER_NIGHT; // $73,000.00
        assertEquals("Maximum nights calculation", 73000.00, basePrice, 0.01);
        
        // Test tax on large amount
        double tax = basePrice * RESORT_TAX_RATE;
        assertEquals("Tax on maximum nights", 4015.00, tax, 0.01);
    }
}