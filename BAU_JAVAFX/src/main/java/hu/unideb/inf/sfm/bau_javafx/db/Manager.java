package hu.unideb.inf.sfm.bau_javafx.db;

import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import javax.mail.MessagingException;
import java.util.List;

public interface Manager {
    public void start();
    public void stop();
    public boolean saveUser(User user);
    public boolean DeleteUser(User user);
    public User getUser(String username);
    public List<User> getUsers(String username);

    public boolean DeleteAppointment(Appointment appointment);
    public List<Appointment> getAppointments();
    public boolean sendEmail(String apptype, String reason, Appointment appointment, User doctor) throws MessagingException;
}
