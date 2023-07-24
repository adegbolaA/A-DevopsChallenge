package com.demo.converter;

public class TemperatureConverter {

    // TemperatureConverter utility class

    public static double convert(double value, String fromUnit, String toUnit) {
        double convertedFromValue = convertToCelsius(value, fromUnit);
        return convertFromCelsius(convertedFromValue, toUnit);
    }

    private static double convertToCelsius(double value, String fromUnit) {
        switch (fromUnit.toUpperCase()) {
            case "KELVIN":
                return value - 273.15;
            case "CELSIUS":
                return value;
            case "FAHRENHEIT":
                return (value - 32) * 5 / 9;
            case "RANKINE":
                return (value - 491.67) * 5 / 9;
            default:
                throw new IllegalArgumentException("Unsupported input unit of measure: " + fromUnit);
        }
    }

    private static double convertFromCelsius(double value, String toUnit) {
        switch (toUnit.toUpperCase()) {
            case "KELVIN":
                return value + 273.15;
            case "CELSIUS":
                return value;
            case "FAHRENHEIT":
                return value * 9 / 5 + 32;
            case "RANKINE":
                return value * 9 / 5 + 491.67;
            default:
                throw new IllegalArgumentException("Unsupported target unit of measure: " + toUnit);
        }
    }

}
