package com.demo;

import java.util.Scanner;

public class App {

    // Helper method to read input safely
    private static String safeReadLine(Scanner scanner) {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Temperature Conversion Problem Checker!");

            // Get input from the teacher
            System.out.print("Enter the input numerical value (default: 0): ");
            double inputNumericalValue = Double.parseDouble(safeReadLine(scanner));

            System.out.print("Enter the input unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String inputUnitOfMeasure = safeReadLine(scanner);

            System.out.print("Enter the target unit of measure (Kelvin, Celsius, Fahrenheit, Rankine): ");
            String targetUnitOfMeasure = safeReadLine(scanner);

            ConversionProblem problem = new ConversionProblem(inputNumericalValue, inputUnitOfMeasure,
                    targetUnitOfMeasure);

            // Get student's response
            System.out.print("Enter the student's numeric response (default: 0): ");
            double studentResponse = Double.parseDouble(safeReadLine(scanner));

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
