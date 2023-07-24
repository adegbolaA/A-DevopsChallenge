package com.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.demo.converter.TemperatureConverter;

public class TemperatureConverterTest {

    @Test
    public void testKelvinToCelsius() {
        double result = TemperatureConverter.convert(300, "kelvin", "celsius");
        assertEquals(26.85, result, 0.01);
    }

    @Test
    public void testCelsiusToKelvin() {
        double result = TemperatureConverter.convert(25, "celsius", "kelvin");
        assertEquals(298.15, result, 0.01);
    }

    @Test
    public void testKelvinToFahrenheit() {
        double result = TemperatureConverter.convert(500, "kelvin", "fahrenheit");
        assertEquals(440.33, result, 0.01);
    }

    @Test
    public void testFahrenheitToKelvin() {
        double result = TemperatureConverter.convert(68, "fahrenheit", "kelvin");
        assertEquals(293.15, result, 0.01);
    }

    @Test
    public void testCelsiusToFahrenheit() {
        double result = TemperatureConverter.convert(25, "celsius", "fahrenheit");
        assertEquals(77.0, result, 0.01);
    }

    @Test
    public void testFahrenheitToCelsius() {
        double result = TemperatureConverter.convert(100, "fahrenheit", "celsius");
        assertEquals(37.78, result, 0.01);
    }

    @Test
    public void testRankineToCelsius() {
        double result = TemperatureConverter.convert(500, "rankine", "celsius");
        assertEquals(4.63, result, 0.01);
    }

    @Test
    public void testCelsiusToRankine() {
        double result = TemperatureConverter.convert(50, "celsius", "rankine");
        assertEquals(581.67, result, 0.01);
    }

    @Test
    public void testInvalidUnitConversion() {
        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.convert(100, "INVALID_UNIT", "celsius");
        });
    }

    @Test
    public void testInvalidTemperatureInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.convert(Double.NaN, "CELSIUSss", "KELVIN");
        });
    }

}
