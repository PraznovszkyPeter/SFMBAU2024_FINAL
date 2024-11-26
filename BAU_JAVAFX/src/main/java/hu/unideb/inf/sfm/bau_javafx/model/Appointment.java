package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
public class Appointment {
    @Id
    private LocalDateTime appointmentDate;
    private String species;
    private int numberOfAnimals;
    @Enumerated(EnumType.STRING)
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

    public Appointment(LocalDateTime appointmentDate, String species, int numberOfAnimals, examination examinationType, String comment, String keeperName, String email, String phoneNumber) {
        this.appointmentDate = appointmentDate;
        this.species = species;
        this.numberOfAnimals = numberOfAnimals;
        this.examinationType = examinationType;
        this.comment = comment;
        this.keeperName = keeperName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
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
