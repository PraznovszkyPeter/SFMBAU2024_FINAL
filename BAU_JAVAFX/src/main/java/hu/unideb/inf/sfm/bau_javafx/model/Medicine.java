package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Medicine {
    @Id
    private String medicineName;
    private String disease;
    private double dosage;
    private String species;

    public Medicine() {

    }

    public Medicine(String medicineName, String disease, double dosage, String species) {
        this.medicineName = medicineName;
        this.disease = disease;
        this.dosage = dosage;
        this.species = species;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
