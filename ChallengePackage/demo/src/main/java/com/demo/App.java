package com.demo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Welcome to the Temperature Conversion Problem Checker!\n");

            // Get input from the teacher
            System.out.print("Enter the input numerical value (default: 0): ");
            double inputNumericalValue;
            if (scanner.hasNextLine()) {
                inputNumericalValue = Double.parseDouble(scanner.nextLine());
            } else {
                inputNumericalValue = 0.0; // Use a default value if no input provided
            }

            System.out.print("Enter the input unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String inputUnitOfMeasure;
            if (scanner.hasNextLine()) {
                inputUnitOfMeasure = scanner.nextLine();
            } else {
                inputUnitOfMeasure = "Kelvin"; // Use a default value if no input provided
            }

            System.out.print("Enter the target unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String targetUnitOfMeasure;
            if (scanner.hasNextLine()) {
                targetUnitOfMeasure = scanner.nextLine();
            } else {
                targetUnitOfMeasure = "Celsius"; // Use a default value if no input provided
            }

            ConversionProblem problem = new ConversionProblem(inputNumericalValue, inputUnitOfMeasure,
                    targetUnitOfMeasure);

            // Get student's response
            System.out.print("Enter the student's numeric response: ");
            double studentResponse;
            if (scanner.hasNextLine()) {
                studentResponse = Double.parseDouble(scanner.nextLine());
            } else {
                studentResponse = 0.0; // Use a default value if no input provided
            }

            // Check student's response
            String result = problem.checkResponse(studentResponse);
            System.out.println("Result: " + result);

            scanner.close();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numerical value.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
