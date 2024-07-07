import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

class Vehicle {
    private String make;
    private String model;
    private double milesPerGallon;

    public Vehicle(String make, String model, double milesPerGallon) {
        this.make = make;
        this.model = model;
        this.milesPerGallon = milesPerGallon;
    }

    public double getMilesPerGallon() {
        return milesPerGallon;
    }

    @Override
    public String toString() {
        return "Make: " + make + ", Model: " + model + ", Miles per Gallon: " + milesPerGallon;
    }
}

public class VehicleManager {
    public static void main(String[] args) {
        LinkedList<Vehicle> vehicles = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean keepAdding = true;

        while (keepAdding) {
            System.out.print("Enter vehicle make: ");
            String make = scanner.nextLine();
            System.out.print("Enter vehicle model: ");
            String model = scanner.nextLine();
            double milesPerGallon;
            do {
                System.out.print("Enter miles per gallon (positive number): ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
                milesPerGallon = scanner.nextDouble();
                scanner.nextLine();
            } while (milesPerGallon <= 0);

            Vehicle vehicle = new Vehicle(make, model, milesPerGallon);
            vehicles.add(vehicle);

            System.out.print("Do you want to add another vehicle? (y/n): ");
            String response = scanner.nextLine();
            keepAdding = response.equalsIgnoreCase("y");
        }

        vehicles.sort((v1, v2) -> Double.compare(v1.getMilesPerGallon(), v2.getMilesPerGallon()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicle_data.txt"))) {
            for (Vehicle v : vehicles) {
                writer.write(v.toString());
                writer.newLine();
            }
            System.out.println("Vehicle data saved to 'vehicle_data.txt'.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
