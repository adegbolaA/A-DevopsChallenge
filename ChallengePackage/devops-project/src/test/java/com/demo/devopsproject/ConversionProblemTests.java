package com.demo.devopsproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.demo.devopsproject.converter.ConversionProblem;
import com.demo.devopsproject.converter.TemperatureConverter;

@SpringBootTest
public class ConversionProblemTests {

    private ConversionProblem conversionProblem;

    @BeforeEach
    public void setUp() {
        conversionProblem = new ConversionProblem(new TemperatureConverter());
    }

    @Test
    public void testCorrectResponse() {
        double input = 100.0;
        String inputUnit = "Celsius";
        String targetUnit = "Fahrenheit";
        double studentResponse = 212.0;

        String result = conversionProblem.checkResponse(input, inputUnit, targetUnit, studentResponse);
        Assertions.assertEquals("Correct", result);
    }

    @Test
    public void testIncorrectResponse() {
        double input = 100.0;
        String inputUnit = "Celsius";
        String targetUnit = "Fahrenheit";
        double studentResponse = 200.0;

        String result = conversionProblem.checkResponse(input, inputUnit, targetUnit, studentResponse);
        Assertions.assertEquals("Incorrect", result);
    }

    @Test
    public void testInvalidResponse() {
        double input = 100.0;
        String inputUnit = "InvalidUnit";
        String targetUnit = "Fahrenheit";
        double studentResponse = 212.0;

        String result = conversionProblem.checkResponse(input, inputUnit, targetUnit, studentResponse);
        Assertions.assertEquals("Invalid", result);
    }
}
