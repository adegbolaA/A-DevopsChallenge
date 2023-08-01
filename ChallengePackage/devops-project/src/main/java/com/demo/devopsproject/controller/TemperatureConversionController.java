package com.demo.devopsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.devopsproject.converter.ConversionProblem;

@RestController
public class TemperatureConversionController {
    private final ConversionProblem conversionProblem;

    @Autowired
    public TemperatureConversionController(ConversionProblem conversionProblem) {
        this.conversionProblem = conversionProblem;
    }

    @GetMapping("/convert")
    public String convertTemperature(@RequestParam double input,
            @RequestParam String inputUnit,
            @RequestParam String targetUnit,
            @RequestParam double studentResponse) {
        return conversionProblem.checkResponse(input, inputUnit.toUpperCase(), targetUnit.toUpperCase(),
                studentResponse);
    }
}
