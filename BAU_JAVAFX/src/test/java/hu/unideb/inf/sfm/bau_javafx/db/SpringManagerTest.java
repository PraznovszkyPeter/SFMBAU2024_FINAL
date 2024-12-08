package hu.unideb.inf.sfm.bau_javafx.db;

import static org.junit.jupiter.api.Assertions.*;

import hu.unideb.inf.sfm.bau_javafx.frontend.Emailing;
import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.AppointmentRepository;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import hu.unideb.inf.sfm.bau_javafx.model.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
public class SpringManagerTest {

    @Autowired
    private SpringManager springManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Mock
    private Emailing emailingMock; // Mock the Emailing service

    @InjectMocks
    private SpringManager springManagerWithMock; // Inject the mock into SpringManager

    @BeforeEach
    void setUp() {
        springManager.start();
    }

    @AfterEach
    void tearDown() {
        springManager.stop();
    }

    @Test
    void start() {
        assertNotNull(this.springManager);
    }

    @Test
    void stop() {
        springManager.stop();
        assertDoesNotThrow(() -> springManager.stop());
    }

    @Test
    void testSaveUser() {
        User doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);
        boolean saved = this.springManager.saveUser(doctor);
        assertTrue(saved);

        User savedUser = springManager.getUser("drbubo");
        assertNotNull(savedUser);
        assertEquals("drbubo", savedUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        User doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);
        springManager.saveUser(doctor);
        boolean deleted = this.springManager.DeleteUser(doctor);
        assertTrue(deleted);

        User deletedUser = springManager.getUser(doctor.getUsername());
        assertNull(deletedUser);
        assertFalse(springManager.DeleteUser(doctor));
    }

    @Test
    void testGetUser() {
        User doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);
        springManager.saveUser(doctor);
        assertNotNull(springManager.getUser(doctor.getUsername()));
    }

    @Test
    void testGetUsers() {
        User doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);
        springManager.saveUser(doctor);

        List<User> users = springManager.getUsers("drbubo");

        assertFalse(users.contains(doctor));
    }

    @Test
    void testSendEmail() throws MessagingException {
        Appointment appointment = new Appointment();
        User doctor = new User("drbubo", "drbubo123", "Dr. Bubó", "Bubó", 12345, User.usertype.DOCTOR);

        when(emailingMock.SendMail(anyString(), anyString(), any(Appointment.class), any(User.class)))
                .thenReturn(true);

        boolean emailSent = springManagerWithMock.sendEmail("testType", "testReason", appointment, doctor);
        assertTrue(emailSent);

        verify(emailingMock, times(1)).SendMail(anyString(), anyString(), any(Appointment.class), any(User.class));
    }

    @Test
    void testDeleteAppointment() {
        Appointment appointment = new Appointment(LocalDateTime.now(), "kutya", "fáj a lába","Gipsz Jakab", "gipsz@minta.hu", "061234567");
        appointmentRepository.save(appointment);
        boolean deleted = springManager.DeleteAppointment(appointment);
        assertTrue(deleted);

        Appointment deletedAppointment = appointmentRepository.findById(appointment.getAppointmentDate()).orElse(null);
        assertNull(deletedAppointment);
        assertFalse(springManager.DeleteAppointment(appointment));
    }

    @Test
    void testGetAppointments() {
        int count = springManager.getAppointments().size();

        Appointment appointment = new Appointment(LocalDateTime.now(), "kutya", "fáj a lába","Gipsz Jakab", "gipsz@minta.hu", "061234567");
        appointmentRepository.save(appointment);

        assertEquals(count+1, springManager.getAppointments().size());
    }


}
