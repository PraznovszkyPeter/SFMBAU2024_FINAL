package hu.unideb.inf.sfm.bau_javafx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment(LocalDateTime.of(2024, 11, 22, 9, 30), "kutya", 2, Appointment.examination.VEMHESSEGVIZSGALAT, "", "Gipsz Jakab", "gipsz@minta.hu", "061234567");
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
    void testNumberOfAnimals() {
        assertEquals(2, appointment.getNumberOfAnimals());

        appointment.setNumberOfAnimals(appointment.getNumberOfAnimals() + 1);
        assertEquals(3, appointment.getNumberOfAnimals());
    }

    @Test
    void testExaminationType() {
        assertEquals(Appointment.examination.VEMHESSEGVIZSGALAT, appointment.getExaminationType());

        appointment.setExaminationType(Appointment.examination.BETEGVIZSGALAT);
        assertEquals(Appointment.examination.BETEGVIZSGALAT, appointment.getExaminationType());
    }

    @Test
    void testComment() {
        assertEquals("", appointment.getComment());

        appointment.setComment("Az ipafai papnak fapipája van, ezért az ipafai fapipa - papi fapipa.");
        assertEquals("Az ipafai papnak fapipája van, ezért az ipafai fapipa - papi fapipa.", appointment.getComment());
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
