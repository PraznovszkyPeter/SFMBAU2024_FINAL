package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String surname;
    private String forename;
    private Integer registrationNumber;

    @Enumerated(EnumType.STRING)
    private usertype usertype;

    public enum usertype{
        DOCTOR, MANAGER
    }

    public User() {}

    public User(String username, String password, String surname, String forename, Integer registrationNumber, usertype usertype) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.surname = surname;
        this.forename = forename;
        this.registrationNumber = registrationNumber;
        this.usertype = usertype;
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public User.usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(User.usertype usertype) {
        this.usertype = usertype;
    }
}
