package hu.unideb.inf.sfm.bau_javafx.model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.beans.property.BooleanProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class UserTest {
    private User doctor;
    private User manager;

    @BeforeEach
    void setUp() {
        doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);
        manager = new User("tesztelek", "tesztelek123", "Teszt", "Elek", null, User.usertype.MANAGER);
    }

    @Test
    void emptyConstructor() {
        User user = new User();
    }

    @Test
    void testUsername() {
        assertEquals(doctor.getUsername(), "drbubo");
        assertEquals(manager.getUsername(), "tesztelek");

        doctor.setUsername("drfules");
        assertEquals(doctor.getUsername(), "drfules");
        manager.setUsername("gipszjakab");
        assertEquals(manager.getUsername(), "gipszjakab");
    }

    @Test
    void testPassword() {
        assertTrue(doctor.checkPassword("drbubo123"));
        assertTrue(manager.checkPassword("tesztelek123"));

        doctor.setPassword("doktoros123");
        assertTrue(doctor.checkPassword("doktoros123"));
        manager.setPassword("manageres123");
        assertTrue(manager.checkPassword("manageres123"));

        assertNotNull(doctor.getPassword());
    }

    @Test
    void testForename() {
        assertEquals(doctor.getForename(), "Bubó");
        assertEquals(manager.getForename(), "Elek");

        doctor.setForename("István");
        assertEquals(doctor.getForename(), "István");
        manager.setForename("Jakab");
        assertEquals(manager.getForename(), "Jakab");
    }

    @Test
    void testSurname() {
        assertEquals(doctor.getSurname(), "Dr. Bubó");
        assertEquals(manager.getSurname(), "Teszt");

        doctor.setSurname("Dr. Füles");
        assertEquals(doctor.getSurname(), "Dr. Füles");
        manager.setSurname("Gipsz");
        assertEquals(manager.getSurname(), "Gipsz");
    }

    @Test
    void testRegistrationNumber() {
        assertEquals(doctor.getRegistrationNumber(), 12345);
        assertNull(manager.getRegistrationNumber());

        doctor.setRegistrationNumber(54321);
        assertEquals(doctor.getRegistrationNumber(), 54321);
        manager.setRegistrationNumber(12345);
        assertNull(manager.getRegistrationNumber());
    }

    @Test
    void testUsertype() {
        assertEquals(doctor.getUsertype(), User.usertype.DOCTOR);
        assertEquals(manager.getUsertype(), User.usertype.MANAGER);
    }

    @Test
    void testUsernameProperty() {
        assertNotNull(doctor.usernameProperty());
        assertEquals("drbubo", doctor.usernameProperty().get());

        doctor.setUsername("drfules");
        assertEquals("drfules", doctor.usernameProperty().get());
    }

    @Test
    void testForenameProperty() {
        assertNotNull(doctor.usernameProperty());
        assertEquals("Bubó", doctor.forenameProperty().get());

        doctor.setForename("István");
        assertEquals("István", doctor.forenameProperty().get());
    }

    @Test
    void testSurnameProperty() {
        assertNotNull(doctor.surnameProperty());
        assertEquals("Dr. Bubó", doctor.surnameProperty().get());

        doctor.setSurname("Dr. Fules");
        assertEquals("Dr. Fules", doctor.surnameProperty().get());
    }

    @Test
    void testRegistrationNumberProperty() {
        assertNotNull(doctor.registrationNumberProperty());
        assertEquals(12345, doctor.registrationNumberProperty().get());

        doctor.setRegistrationNumber(54321);
        assertEquals(54321, doctor.registrationNumberProperty().get());
    }

    @Test
    void testUsertypeProperty() {
        assertNotNull(doctor.usertypeProperty());
        assertEquals(User.usertype.DOCTOR, doctor.usertypeProperty().get());

        doctor.setUsertype(User.usertype.MANAGER);
        assertNull(doctor.getRegistrationNumber());
        assertEquals(User.usertype.MANAGER, doctor.usertypeProperty().get());

        doctor.setUsertype(User.usertype.DOCTOR);
    }

    @Test
    void testResetPasswordProperty() {
        assertNotNull(doctor.resetPasswordProperty());

        doctor.setResetPassword(true);
        assertTrue(doctor.resetPasswordProperty().get());
    }

    @Test
    void testResetters() {
        assertTrue(doctor.isFirstLogin());
        doctor.setFirstLogin(false);
        assertFalse(doctor.isFirstLogin());

        assertFalse(doctor.isResetPassword());
        doctor.setResetPassword(true);
        assertTrue(doctor.isResetPassword());
    }


}
