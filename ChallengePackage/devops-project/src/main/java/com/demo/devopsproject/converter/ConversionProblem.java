package com.demo.devopsproject.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionProblem {

    private final TemperatureConverter temperatureConverter;

    @Autowired
    public ConversionProblem(TemperatureConverter temperatureConverter) {
        this.temperatureConverter = temperatureConverter;
    }

    public String checkResponse(double input, String inputUnit, String targetUnit, double studentResponse) {
        try {
            double authoritativeAnswer = temperatureConverter.convert(input, inputUnit, targetUnit);
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