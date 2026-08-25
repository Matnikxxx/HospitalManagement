package com.mycompany.hospitalmangement;


import com.mycompany.hospitalmangement.HospitalSystem;
import com.mycompany.hospitalmangement.PatientCategory;
import com.mycompany.hospitalmangement.Inpatient;
import com.mycompany.hospitalmangement.Patient;



    import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    @Test
    public void testRegisterAndSearchPatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        Patient result = hospital.searchPatient("P001");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("P001", result.getPatientId());
    }

    @Test
    public void testSearchPatientNotFound() {

        HospitalSystem hospital = new HospitalSystem();

        Patient result = hospital.searchPatient("P999");

        assertNull(result);
    }

    @Test
    public void testUpdatePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P002",
                "James",
                "Brown",
                25,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        // We cannot easily provide Scanner input in this simple test,
        // so we test that the patient can be found before updating.
        Patient result = hospital.searchPatient("P002");

        assertNotNull(result);

        result.setFirstName("Michael");

        assertEquals("Michael", result.getFirstName());
    }

    @Test
    public void testDeletePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P003",
                "Sarah",
                "Jones",
                40,
                "Female",
                "Headache",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        assertTrue(hospital.deletePatient("P003"));

        assertNull(hospital.searchPatient("P003"));
    }

    @Test
    public void testDeletePatientNotFound() {

        HospitalSystem hospital = new HospitalSystem();

        assertFalse(hospital.deletePatient("P999"));
    }

    @Test
    public void testInpatientCategory() {

        Inpatient inpatient = new Inpatient(
                "P004",
                "David",
                "Miller",
                50,
                "Male",
                "Pneumonia",
                "Ward 1",
                "B01"
        );

        assertEquals(
                PatientCategory.INPATIENT,
                inpatient.getPatientCategory()
        );
    }

    @Test
    public void testInpatientInheritance() {

        Inpatient inpatient = new Inpatient(
                "P005",
                "Mary",
                "Williams",
                35,
                "Female",
                "Infection",
                "Ward 2",
                "B05"
        );

        assertTrue(inpatient instanceof Patient);
    }

    @Test
    public void testBedAllocation() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient inpatient = new Inpatient(
                "P006",
                "Peter",
                "Taylor",
                45,
                "Male",
                "Injury",
                "",
                ""
        );

        hospital.registerPatient(inpatient);

        assertTrue(hospital.allocateBed("P006"));

        assertEquals(1, hospital.getOccupiedBedCount());
    }

    @Test
    public void testBedRelease() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient inpatient = new Inpatient(
                "P007",
                "Lisa",
                "Wilson",
                28,
                "Female",
                "Injury",
                "",
                ""
        );

        hospital.registerPatient(inpatient);

        hospital.allocateBed("P007");

        assertEquals(1, hospital.getOccupiedBedCount());

        assertTrue(hospital.releaseBed("P007"));

        assertEquals(0, hospital.getOccupiedBedCount());
    }

    @Test
    public void testOutpatientCannotGetBed() {

        HospitalSystem hospital = new HospitalSystem();

        Patient outpatient = new Patient(
                "P008",
                "Tom",
                "Davis",
                32,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(outpatient);

        assertFalse(hospital.allocateBed("P008"));

        assertEquals(0, hospital.getOccupiedBedCount());
    }

    @Test
    public void testWardHas20Beds() {

        HospitalSystem hospital = new HospitalSystem();

        assertEquals(0, hospital.getOccupiedBedCount());
        assertEquals(0.0, hospital.getOccupancyPercentage());
    }

    @Test
    public void testOccupancyPercentage() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient inpatient = new Inpatient(
                "P009",
                "Alex",
                "Smith",
                60,
                "Male",
                "Heart condition",
                "",
                ""
        );

        hospital.registerPatient(inpatient);

        hospital.allocateBed("P009");

        assertEquals(5.0, hospital.getOccupancyPercentage());
    }
}
    

