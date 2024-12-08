package hu.unideb.inf.sfm.bau_javafx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment(LocalDateTime.of(2024, 11, 22, 9, 30), "kutya", "fáj a lába","Gipsz Jakab", "gipsz@minta.hu", "061234567");
    }

    @Test
    void emptyConstructor() {
        Appointment appointmentEmpty = new Appointment();
    }

    @Test
    void testAppintmentDate() {
        assertEquals(LocalDateTime.of(2024, 11, 22, 9, 30), appointment.getAppointmentDate());

        appointment.setAppointmentDate(LocalDateTime.of(2024, 11, 22, 19, 30));
        assertEquals(LocalDateTime.of(2024, 11, 22, 19, 30), appointment.getAppointmentDate());
    }

    @Test
    void testSpecies() {
        assertEquals("kutya", appointment.getSpecies());

        appointment.setSpecies("Mexikói axolotl");
        assertEquals("Mexikói axolotl", appointment.getSpecies());
    }

    @Test
    void testComplaint() {
        assertEquals("fáj a lába", appointment.getComplaint());

        appointment.setComplaint("Az ipafai papnak fapipája van, ezért az ipafai fapipa - papi fapipa.");
        assertEquals("Az ipafai papnak fapipája van, ezért az ipafai fapipa - papi fapipa.", appointment.getComplaint());
    }

    @Test
    void testKeeperName() {
        assertEquals("Gipsz Jakab", appointment.getKeeperName());

        appointment.setKeeperName("Teszt Elek");
        assertEquals("Teszt Elek", appointment.getKeeperName());
    }

    @Test
    void testKeeperEmail() {
        assertEquals("gipsz@minta.hu", appointment.getEmail());

        appointment.setEmail("elek@teszt.hu");
        assertEquals("elek@teszt.hu", appointment.getEmail());
    }

    @Test
    void testKeeperPhone() {
        assertEquals("061234567", appointment.getPhoneNumber());

        appointment.setPhoneNumber("0620406080");
        assertEquals("0620406080", appointment.getPhoneNumber());
    }
}
