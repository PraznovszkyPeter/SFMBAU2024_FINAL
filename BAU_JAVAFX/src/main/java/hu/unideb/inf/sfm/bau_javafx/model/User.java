package hu.unideb.inf.sfm.bau_javafx.model;

import jakarta.persistence.*;
import javafx.beans.property.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String surname;
    private String forename;
    private Integer registrationNumber;
    private boolean firstLogin;
    private boolean resetPassword;

    @Enumerated(EnumType.STRING)
    private usertype usertype;

    public enum usertype{
        DOCTOR, MANAGER
    }

    //For the Observing
    @Transient
    private StringProperty usernameProperty;

    @Transient
    private StringProperty forenameProperty;

    @Transient
    private StringProperty surnameProperty;

    @Transient
    private ObjectProperty<Integer> registrationNumberProperty;

    @Transient
    private BooleanProperty firstLoginProperty;

    @Transient
    private BooleanProperty resetPasswordProperty;

    @Transient
    private ObjectProperty<usertype> usertypeProperty;

    public User() {
        this.firstLogin = true;
        this.resetPassword = false;
    }

    public User(String username, String password, String surname, String forename, Integer registrationNumber, usertype usertype) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
        this.surname = surname;
        this.forename = forename;
        this.registrationNumber = registrationNumber;
        this.usertype = usertype;
        this.firstLogin = true;
        this.resetPassword = false;
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.password);
    }

    public void setPassword(String s) {
        this.password = BCrypt.hashpw(s, BCrypt.gensalt());
    }

    public String getPassword() {
        return this.password;
    }

    // Standard field getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
        if (usernameProperty != null) this.usernameProperty.set(username);
    }

    public String getForename() { return forename; }
    public void setForename(String forename) {
        this.forename = forename;
        if (forenameProperty != null) this.forenameProperty.set(forename);
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        this.surname = surname;
        if (surnameProperty != null) this.surnameProperty.set(surname);
    }

    public Integer getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
        if (registrationNumberProperty != null) this.registrationNumberProperty.set(registrationNumber);
    }

    public boolean isResetPassword() { return resetPassword; }
    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
        if (resetPasswordProperty != null) this.resetPasswordProperty.set(resetPassword);
    }

    public User.usertype getUsertype() { return usertype; }
    public void setUsertype(User.usertype usertype) {
        this.usertype = usertype;
        if (usertypeProperty != null) this.usertypeProperty.set(usertype);
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    // Property getters with lazy initialization
    public StringProperty usernameProperty() {
        if (usernameProperty == null) {
            usernameProperty = new SimpleStringProperty(username);
            usernameProperty.addListener((observable, oldValue, newValue) -> setUsername(newValue));
        }
        return usernameProperty;
    }

    public StringProperty forenameProperty() {
        if (forenameProperty == null) {
            forenameProperty = new SimpleStringProperty(forename);
            forenameProperty.addListener((observable, oldValue, newValue) -> setForename(newValue));
        }
        return forenameProperty;
    }

    public StringProperty surnameProperty() {
        if (surnameProperty == null) {
            surnameProperty = new SimpleStringProperty(surname);
            surnameProperty.addListener((observable, oldValue, newValue) -> setSurname(newValue));
        }
        return surnameProperty;
    }

    public ObjectProperty<Integer> registrationNumberProperty() {
        if (registrationNumberProperty == null) {
            registrationNumberProperty = new SimpleObjectProperty<>(registrationNumber); // Can be null
            registrationNumberProperty.addListener((observable, oldValue, newValue) -> setRegistrationNumber(newValue));
        }
        return registrationNumberProperty;
    }

    public BooleanProperty resetPasswordProperty() {
        if (resetPasswordProperty == null) {
            resetPasswordProperty = new SimpleBooleanProperty(resetPassword);
            resetPasswordProperty.addListener((observable, oldValue, newValue) -> setResetPassword(newValue));
        }
        return resetPasswordProperty;
    }

    public ObjectProperty<User.usertype> usertypeProperty() {
        if (usertypeProperty == null) {
            usertypeProperty = new SimpleObjectProperty<>(usertype);
            usertypeProperty.addListener((observable, oldValue, newValue) -> setUsertype(newValue));
        }
        return usertypeProperty;
    }

    public BooleanProperty firstLoginProperty() {
        if (firstLoginProperty == null) {
            firstLoginProperty = new SimpleBooleanProperty(firstLogin);
            // Listen for changes to firstLoginProperty and perform actions accordingly
            firstLoginProperty.addListener((observable, oldValue, newValue) -> setResetPassword(newValue));
        }
        return firstLoginProperty;
    }
}
