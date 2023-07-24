package com.demo;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Temperature Conversion Problem Checker!");

            // Get input from the teacher
            System.out.print("Enter the input numerical value: ");
            double inputNumericalValue = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter the input unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String inputUnitOfMeasure = scanner.nextLine();

            System.out.print("Enter the target unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String targetUnitOfMeasure = scanner.nextLine();

            ConversionProblem problem = new ConversionProblem(inputNumericalValue, inputUnitOfMeasure,
                    targetUnitOfMeasure);

            // Get student's response
            System.out.print("Enter the student's numeric response: ");
            double studentResponse = Double.parseDouble(scanner.nextLine());

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
