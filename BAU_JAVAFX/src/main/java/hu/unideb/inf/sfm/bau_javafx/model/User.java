package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String surname;
    private String forename;
    private int registrationNumber;

    public User() {

    }

    public User(String username, String password, String surname, String forename, int registrationNumber) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.forename = forename;
        this.registrationNumber = registrationNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

}
