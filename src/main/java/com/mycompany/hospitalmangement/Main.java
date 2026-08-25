package com.mycompany.hospitalmangement;


import com.mycompany.hospitalmangement.Patient;
import com.mycompany.hospitalmangement.PatientCategory;
import com.mycompany.hospitalmangement.Inpatient;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HospitalSystem hospital = new HospitalSystem();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n======================================");
            System.out.println("      HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Allocate Bed");
            System.out.println("7. Release Bed");
            System.out.println("8. Ward Overview");
            System.out.println("9. Available Beds");
            System.out.println("10. Occupied Beds");
            System.out.println("11. Hospital Report");
            System.out.println("12. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.println("\n--- REGISTER PATIENT ---");

                    System.out.print("Patient ID: ");
                    String patientId = scanner.nextLine();

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Age: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Medical Condition: ");
                    String condition = scanner.nextLine();

                    System.out.println("\nSelect Patient Category:");
                    System.out.println("1. Inpatient");
                    System.out.println("2. Outpatient");
                    System.out.println("3. Emergency");

                    System.out.print("Enter category: ");

                    String categoryChoice =
                            scanner.nextLine();

                    PatientCategory category;

                    if (categoryChoice.equals("1")) {

                        category = PatientCategory.INPATIENT;

                    } else if (categoryChoice.equals("2")) {

                        category = PatientCategory.OUTPATIENT;

                    } else if (categoryChoice.equals("3")) {

                        category = PatientCategory.EMERGENCY;

                    } else {

                        System.out.println(
                                "Invalid category.");

                        break;
                    }

                    Patient patient;

                    if (category == PatientCategory.INPATIENT) {

                        patient = new Inpatient(
                                patientId,
                                firstName,
                                lastName,
                                age,
                                gender,
                                condition,
                                "",
                                ""
                        );

                    } else {

                        patient = new Patient(
                                patientId,
                                firstName,
                                lastName,
                                age,
                                gender,
                                condition,
                                category
                        );
                    }

                    hospital.registerPatient(patient);

                    break;

                case "2":

                    System.out.print(
                            "\nEnter Patient ID: ");

                    String searchId = scanner.nextLine();

                    Patient foundPatient =
                            hospital.searchPatient(searchId);

                    if (foundPatient != null) {

                        System.out.println("\n--- PATIENT DETAILS ---");

                        foundPatient.displayDetails();

                    } else {

                        System.out.println(
                                "Patient could not be found.");
                    }

                    break;

                case "3":

                    System.out.print(
                            "\nEnter Patient ID to update: ");

                    String updateId = scanner.nextLine();

                    if (hospital.updatePatient(updateId)) {

                        System.out.println(
                                "Patient updated successfully.");

                    } else {

                        System.out.println(
                                "Patient could not be found.");
                    }

                    break;

                case "4":

                    System.out.print(
                            "\nEnter Patient ID to delete: ");

                    String deleteId = scanner.nextLine();

                    System.out.print(
                            "Are you sure? (Y/N): ");

                    String confirmation =
                            scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("Y")) {

                        if (hospital.deletePatient(deleteId)) {

                            System.out.println(
                                    "Patient deleted successfully.");

                        } else {

                            System.out.println(
                                    "Patient could not be found.");
                        }

                    } else {

                        System.out.println(
                                "Delete cancelled.");
                    }

                    break;

                case "5":

                    hospital.displayPatients();

                    break;

                case "6":

                    System.out.print(
                            "\nEnter Inpatient ID: ");

                    String allocationId =
                            scanner.nextLine();

                    hospital.allocateBed(allocationId);

                    break;

                case "7":

                    System.out.print(
                            "\nEnter Patient ID: ");

                    String releaseId =
                            scanner.nextLine();

                    if (!hospital.releaseBed(releaseId)) {

                        System.out.println(
                                "No occupied bed found "
                                + "for this patient.");
                    }

                    break;

                case "8":

                    hospital.displayWard();

                    break;

                case "9":

                    hospital.displayAvailableBeds();

                    break;

                case "10":

                    hospital.displayOccupiedBeds();

                    break;

                case "11":

                    hospital.displayReport();

                    break;

                case "12":

                    System.out.println(
                            "\nThank you for using the "
                            + "Hospital Management System.");

                    running = false;

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}