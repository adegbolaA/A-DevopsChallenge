package com.demo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Temperature Conversion Problem Checker!");

            // Get input from the teacher
            System.out.print("Enter the input numerical value (default: 0): ");
            double inputNumericalValue;
            try {
                inputNumericalValue = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                inputNumericalValue = 0.0; // Use a default value if input cannot be parsed
            }

            System.out.print("Enter the input unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String inputUnitOfMeasure = scanner.nextLine();

            System.out.print("Enter the target unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String targetUnitOfMeasure = scanner.nextLine();

            ConversionProblem problem = new ConversionProblem(inputNumericalValue, inputUnitOfMeasure,
                    targetUnitOfMeasure);

            // Get student's response
            System.out.print("Enter the student's numeric response (default: 0): ");
            double studentResponse;
            try {
                studentResponse = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                studentResponse = 0.0; // Use a default value if student's response cannot be parsed
            }

            // Check student's response
            String result = problem.checkResponse(studentResponse);
            System.out.println("Result: " + result);

            // Don't close the scanner here
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numerical value.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
