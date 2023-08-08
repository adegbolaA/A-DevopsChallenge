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

    public String checkResponse(double input, String inputUnit, String targetUnit, Object studentResponse) {
        double authoritativeAnswer;
        try {
            authoritativeAnswer = temperatureConverter.convert(input, inputUnit, targetUnit);
        } catch (IllegalArgumentException e) {
            return "Invalid";
        }

        if (studentResponse instanceof Double) {
            double studentNumericResponse = (Double) studentResponse;
            double roundedStudentResponse = Math.round(studentNumericResponse * 10.0) / 10.0;
            double roundedAuthoritativeAnswer = Math.round(authoritativeAnswer * 10.0) / 10.0;

            if (Double.compare(roundedStudentResponse, roundedAuthoritativeAnswer) == 0) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        } else if (studentResponse instanceof String) {
            // If the studentResponse is a string, treat it as a string comparison
            String studentStringResponse = (String) studentResponse;
            String roundedAuthoritativeAnswerAsString = String.valueOf(Math.round(authoritativeAnswer * 10.0) / 10.0);

            if (studentStringResponse.equalsIgnoreCase(roundedAuthoritativeAnswerAsString)) {
                return "Correct";
            } else {
                return "Incorrect";
            }
        } else {
            // If the studentResponse is neither a valid numeric value nor a string,
            // consider it Invalid
            return "Invalid";
        }
    }
}
