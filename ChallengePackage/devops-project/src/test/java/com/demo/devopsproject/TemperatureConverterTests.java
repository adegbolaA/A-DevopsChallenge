package com.demo.devopsproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.devopsproject.converter.TemperatureConverter;

@SpringBootTest
public class TemperatureConverterTests {

	private TemperatureConverter temperatureConverter;

	@BeforeEach
	public void setUp() {
		temperatureConverter = new TemperatureConverter();
	}

	@Test
	public void testCelsiusToFahrenheitConversion() {
		double celsiusValue = 100.0;
		double fahrenheitValue = temperatureConverter.convert(celsiusValue, "Celsius", "Fahrenheit");
		Assertions.assertEquals(212.0, fahrenheitValue);
	}

	@Test
	public void testFahrenheitToCelsiusConversion() {
		double fahrenheitValue = 212.0;
		double celsiusValue = temperatureConverter.convert(fahrenheitValue, "Fahrenheit", "Celsius");
		Assertions.assertEquals(100.0, celsiusValue);
	}

	@Test
	public void testInvalidUnitConversion() {
		double invalidValue = 100.0;
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> temperatureConverter.convert(invalidValue, "InvalidUnit", "Celsius"));
	}
}
