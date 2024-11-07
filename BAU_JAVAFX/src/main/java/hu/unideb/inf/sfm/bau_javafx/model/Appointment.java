package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Entity
public class Appointment {
    @Id
    private LocalDate appointmentDate;
    private String species;
    private int numberOfAnimals;
    private examination examinationType;
    private String comment;
    private String keeperName;
    private String email;
    private String phoneNumber;

    public enum examination {
        BETEGVIZSGALAT, VEMHESSEGVIZSGALAT, VEDOOLTAS
    }

    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate, String species, int numberOfAnimals, examination examinationType, String comment, String keeperName, String email, String phoneNumber) {
        this.appointmentDate = appointmentDate;
        this.species = species;
        this.numberOfAnimals = numberOfAnimals;
        this.examinationType = examinationType;
        this.comment = comment;
        this.keeperName = keeperName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void setNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }

    public examination getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(examination examinationType) {
        this.examinationType = examinationType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
