package com.mycompany.hospitalmangement;


public class Patient {

    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory patientCategory;

    // Constructor
    public Patient(String patientId, String firstName, String lastName,
                   int age, String gender, String medicalCondition,
                   PatientCategory patientCategory) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.patientCategory = patientCategory;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public PatientCategory getPatientCategory() {
        return patientCategory;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public void setPatientCategory(PatientCategory patientCategory) {
        this.patientCategory = patientCategory;
    }

    // Method that can be overridden
    public void displayDetails() {

        System.out.println("Patient ID: " + patientId);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Patient Category: " + patientCategory);
    }

    @Override
    public String toString() {

        return "Patient ID: " + patientId
                + "\nFirst Name: " + firstName
                + "\nLast Name: " + lastName
                + "\nAge: " + age
                + "\nGender: " + gender
                + "\nMedical Condition: " + medicalCondition
                + "\nPatient Category: " + patientCategory;
    }
}


// Patient  options
enum PatientCategory {
    INPATIENT,
    OUTPATIENT,
    EMERGENCY
}


// Inpatient inherits from Patient
class Inpatient extends Patient {

    private String wardNumber;
    private String bedNumber;

    // Constructor
    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition,
                     String wardNumber, String bedNumber) {

        super(patientId, firstName, lastName, age, gender,
              medicalCondition, PatientCategory.INPATIENT);

        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    // Method overriding
    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Bed Number: " + bedNumber);
    }

    @Override
    public String toString() {

        return super.toString()
                + "\nWard Number: " + wardNumber
                + "\nBed Number: " + bedNumber;
    }
}
    

