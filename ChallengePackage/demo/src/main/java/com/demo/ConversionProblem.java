package com.demo;

import com.demo.converter.TemperatureConverter;

public class ConversionProblem {
    private final double input;
    private final String inputUnit;
    private final String targetUnit;

    public ConversionProblem(double input, String inputUnit, String targetUnit) {
        this.input = input;
        this.inputUnit = inputUnit.toUpperCase();
        this.targetUnit = targetUnit.toUpperCase();
    }

    public String checkResponse(double studentResponse) {
        try {
            double authoritativeAnswer = TemperatureConverter.convert(input, inputUnit, targetUnit);
            double roundedStudentResponse = Math.round(studentResponse * 10.0) / 10.0;
            double roundedAuthoritativeAnswer = Math.round(authoritativeAnswer * 10.0) / 10.0;

            if (roundedStudentResponse == roundedAuthoritativeAnswer) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        } catch (IllegalArgumentException e) {
            return "Invalid";
        }
    }
}
