package com.mycompany.hospitalmangement;


public class Bed {
  

    private String bedNumber;
    private Inpatient patient;

    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.patient = null;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public Inpatient getPatient() {
        return patient;
    }

    public boolean isOccupied() {
        return patient != null;
    }

    public boolean allocatePatient(Inpatient patient) {

        if (this.patient == null) {

            this.patient = patient;

            patient.setWardNumber("Ward 1");
            patient.setBedNumber(bedNumber);

            return true;
        }

        return false;
    }

    public void releaseBed() {

        if (patient != null) {

            patient.setWardNumber("");
            patient.setBedNumber("");
        }

        patient = null;
    }
}
    

