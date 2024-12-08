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
    private String complaint;
    private String keeperName;
    private String email;
    private String phoneNumber;

    public Appointment() {
    }

    public Appointment(LocalDateTime appointmentDate, String species, String complaint, String keeperName, String email, String phoneNumber) {
        this.appointmentDate = appointmentDate;
        this.species = species;
        this.complaint = complaint;
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

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
