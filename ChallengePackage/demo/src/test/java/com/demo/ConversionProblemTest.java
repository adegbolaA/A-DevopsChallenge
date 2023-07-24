package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConversionProblemTest {
    @Test
    public void testWhenConvertingFromFahrenhietToRankine() {
        double input = 84.2;
        String inputUnit = "FAHRENHEIT";
        String targetUnit = "RANKINE";
        double studentResponse = 543.94;

        ConversionProblem problem = new ConversionProblem(input, inputUnit, targetUnit);
        String result = problem.checkResponse(studentResponse);
        assertEquals("Correct", result);
    }

    @Test
    public void testWhenConvertingFromKelvinToFahrenheit() {
        double input = 317.33;
        String inputUnit = "KELVIN";
        String targetUnit = "FAHRENHEIT";
        double studentResponse = 111.554;

        ConversionProblem problem = new ConversionProblem(input, inputUnit, targetUnit);
        String result = problem.checkResponse(studentResponse);
        assertEquals("Incorrect", result);
    }

    @Test
    public void testIncorrectConvertFromFahrenheitToRankine() {
        double input = 6.5;
        String inputUnit = "FAHRENHEIT";
        String targetUnit = "RANKINE";
        double studentResponse = 100; // Invalid student response

        ConversionProblem problem = new ConversionProblem(input, inputUnit, targetUnit);
        String result = problem.checkResponse(studentResponse);
        assertEquals("Incorrect", result);
    }

    @Test
    public void testInvalidConversion() {
        double input = 136.1;
        String inputUnit = "DOG_COW"; // Invalid input unit
        String targetUnit = "CELSIUS";
        double studentResponse = 45.32;

        ConversionProblem problem = new ConversionProblem(input, inputUnit, targetUnit);
        String result = problem.checkResponse(studentResponse);
        assertEquals("Invalid", result);
    }
}
