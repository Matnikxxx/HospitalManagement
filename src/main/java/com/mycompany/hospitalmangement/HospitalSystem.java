package com.mycompany.hospitalmangement;



import com.mycompany.hospitalmangement.Patient;
import com.mycompany.hospitalmangement.PatientCategory;
import com.mycompany.hospitalmangement.Inpatient;
import com.mycompany.hospitalmangement.Bed;
    import java.util.Scanner;

public class HospitalSystem {

    private Patient[] patients = new Patient[100];
    private int patientCount = 0;

    private Bed[] beds = new Bed[20];

    private Scanner scanner = new Scanner(System.in);

    // Create 20 beds
    public HospitalSystem() {

        for (int i = 0; i < beds.length; i++) {

            String bedNumber = String.format("B%02d", i + 1);

            beds[i] = new Bed(bedNumber);
        }
    }

    // Register patient
    public void registerPatient(Patient patient) {

        if (patientCount < patients.length) {

            patients[patientCount] = patient;
            patientCount++;

            System.out.println("Patient registered successfully.");

        } else {

            System.out.println("Patient storage is full.");
        }
    }

    // Search patient
    public Patient searchPatient(String patientId) {

        for (int i = 0; i < patientCount; i++) {

            if (patients[i].getPatientId()
                    .equalsIgnoreCase(patientId)) {

                return patients[i];
            }
        }

        return null;
    }

    // Display all patients
    public void displayPatients() {

        if (patientCount == 0) {

            System.out.println("No patients are currently registered.");
            return;
        }

        System.out.println("\n========== REGISTERED PATIENTS ==========");

        for (int i = 0; i < patientCount; i++) {

            System.out.println("\n" + patients[i]);
            System.out.println("--------------------------------");
        }
    }

    // Update patient
    public boolean updatePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        System.out.print("Enter new first name: ");
        patient.setFirstName(scanner.nextLine());

        System.out.print("Enter new last name: ");
        patient.setLastName(scanner.nextLine());

        System.out.print("Enter new age: ");
        patient.setAge(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter new gender: ");
        patient.setGender(scanner.nextLine());

        System.out.print("Enter new medical condition: ");
        patient.setMedicalCondition(scanner.nextLine());

        System.out.println("Select category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        String categoryChoice = scanner.nextLine();

        if (categoryChoice.equals("1")) {
            patient.setPatientCategory(PatientCategory.INPATIENT);
        } else if (categoryChoice.equals("2")) {
            patient.setPatientCategory(PatientCategory.OUTPATIENT);
        } else if (categoryChoice.equals("3")) {
            patient.setPatientCategory(PatientCategory.EMERGENCY);
        }

        return true;
    }

    // Delete patient
    public boolean deletePatient(String patientId) {

        for (int i = 0; i < patientCount; i++) {

            if (patients[i].getPatientId()
                    .equalsIgnoreCase(patientId)) {

                // Release bed if patient is an inpatient
                releaseBed(patientId);

                for (int j = i; j < patientCount - 1; j++) {

                    patients[j] = patients[j + 1];
                }

                patients[patientCount - 1] = null;
                patientCount--;

                return true;
            }
        }

        return false;
    }

    // Allocate bed
    public boolean allocateBed(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");
            return false;
        }

        if (patient.getPatientCategory()
                != PatientCategory.INPATIENT) {

            System.out.println(
                    "Only Inpatients may be allocated a hospital bed.");

            return false;
        }

        // Check if the patient is already in a bed
        for (Bed bed : beds) {

            if (bed.isOccupied()
                    && bed.getPatient().getPatientId()
                    .equalsIgnoreCase(patientId)) {

                System.out.println(
                        "This patient already has a bed.");

                return false;
            }
        }

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {

                Inpatient inpatient;

                if (patient instanceof Inpatient) {

                    inpatient = (Inpatient) patient;

                } else {

                    inpatient = new Inpatient(
                            patient.getPatientId(),
                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getAge(),
                            patient.getGender(),
                            patient.getMedicalCondition(),
                            "Ward 1",
                            bed.getBedNumber()
                    );

                    replacePatient(patientId, inpatient);
                }

                bed.allocatePatient(inpatient);

                System.out.println(
                        "Bed " + bed.getBedNumber()
                        + " allocated successfully.");

                return true;
            }
        }

        System.out.println("No beds are available.");

        return false;
    }

    // Replace patient object after converting to Inpatient
    private void replacePatient(String patientId, Inpatient inpatient) {

        for (int i = 0; i < patientCount; i++) {

            if (patients[i].getPatientId()
                    .equalsIgnoreCase(patientId)) {

                patients[i] = inpatient;
                return;
            }
        }
    }

    // Release bed
    public boolean releaseBed(String patientId) {

        for (Bed bed : beds) {

            if (bed.isOccupied()
                    && bed.getPatient().getPatientId()
                    .equalsIgnoreCase(patientId)) {

                bed.releaseBed();

                System.out.println("Bed released successfully.");

                return true;
            }
        }

        return false;
    }

    // Display ward
    public void displayWard() {

        System.out.println("\n========== WARD LAYOUT ==========");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                int index = row * 5 + column;

                if (beds[index].isOccupied()) {

                    System.out.print(
                            "[" + beds[index].getBedNumber()
                            + " Occupied] ");

                } else {

                    System.out.print(
                            "[" + beds[index].getBedNumber()
                            + " Available] ");
                }
            }

            System.out.println();
        }
    }

    // Display available beds
    public void displayAvailableBeds() {

        System.out.println("\n========== AVAILABLE BEDS ==========");

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {

                System.out.println(bed.getBedNumber());
            }
        }
    }

    // Display occupied beds
    public void displayOccupiedBeds() {

        System.out.println("\n========== OCCUPIED BEDS ==========");

        for (Bed bed : beds) {

            if (bed.isOccupied()) {

                System.out.println(
                        bed.getBedNumber()
                        + " - Patient: "
                        + bed.getPatient().getPatientId());
            }
        }
    }

    // Total patients
    public int getPatientCount() {

        return patientCount;
    }

    // Total occupied beds
    public int getOccupiedBedCount() {

        int count = 0;

        for (Bed bed : beds) {

            if (bed.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    // Occupancy percentage
    public double getOccupancyPercentage() {

        return (getOccupiedBedCount() / 20.0) * 100;
    }

    // Complete report
    public void displayReport() {

        System.out.println("\n========== HOSPITAL REPORT ==========");

        displayPatients();

        displayAvailableBeds();

        displayOccupiedBeds();

        System.out.println(
                "\nTotal Registered Patients: "
                + getPatientCount());

        System.out.println(
                "Total Occupied Beds: "
                + getOccupiedBedCount());

        System.out.println(
                "Ward Occupancy Percentage: "
                + getOccupancyPercentage() + "%");
    }
}
    

