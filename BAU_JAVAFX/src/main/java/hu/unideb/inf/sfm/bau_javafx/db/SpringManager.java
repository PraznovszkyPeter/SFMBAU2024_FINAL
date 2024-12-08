package hu.unideb.inf.sfm.bau_javafx.db;

import hu.unideb.inf.sfm.bau_javafx.BauJavafxApplication;
import hu.unideb.inf.sfm.bau_javafx.frontend.Emailing;
import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.AppointmentRepository;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import hu.unideb.inf.sfm.bau_javafx.model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SpringManager implements Manager{

    private ConfigurableApplicationContext context;
    private Emailing emailing;

    @Override
    public void start() {
        context = SpringApplication.run(BauJavafxApplication.class);
        emailing = context.getBean(Emailing.class);
        emailing.init();
    }

    @Override
    public void stop() {
        context.close();
    }

    @Override
    public boolean saveUser(User user) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean DeleteUser(User user) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        try {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                userRepository.delete(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getUser(String username) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers(String username) {
        UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);
        return userRepository.findAllExcludingUser(username);
    }

    @Override
    public boolean DeleteAppointment(Appointment appointment) {
        AppointmentRepository appointmentRepository = (AppointmentRepository) context.getBean(AppointmentRepository.class);
        try {
            if (appointmentRepository.findByAppointmentDate(appointment.getAppointmentDate()) != null) {
                appointmentRepository.delete(appointment);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Appointment> getAppointments() {
        AppointmentRepository appointmentRepository = (AppointmentRepository) context.getBean(AppointmentRepository.class);

        LocalDate local=LocalDate.now();
        LocalDateTime today = local.atStartOfDay();
        LocalDateTime tomorrow = local.plusDays(1).atStartOfDay();
        
        return appointmentRepository.findAllTodayAppointment(today, tomorrow);
    }

    @Override
    public boolean sendEmail(String apptype, String reason, Appointment appointment, User doctor) throws MessagingException {
        return this.emailing.SendMail(apptype, reason, appointment, doctor);
    }
}
