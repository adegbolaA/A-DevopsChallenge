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

    public String checkResponse(double input, String inputUnit, String targetUnit, String studentResponse) {
        double authoritativeAnswer;
        try {
            authoritativeAnswer = temperatureConverter.convert(input, inputUnit, targetUnit);
        } catch (IllegalArgumentException e) {
            return "Invalid";
        }

        try {
            double studentNumericResponse = Double.parseDouble(studentResponse);
            double roundedStudentResponse = Math.round(studentNumericResponse * 10.0) / 10.0;
            double roundedAuthoritativeAnswer = Math.round(authoritativeAnswer * 10.0) / 10.0;

            if (Double.compare(roundedStudentResponse, roundedAuthoritativeAnswer) == 0) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        } catch (NumberFormatException e) {
            // If the studentResponse is not a valid numeric value, treat it as a string
            // comparison
            if (studentResponse.equalsIgnoreCase(String.valueOf(authoritativeAnswer))) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        }
    }

}
