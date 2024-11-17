package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@PropertySource("classpath:event.properties")
public class Emailing {
    @Value("${MAIL_USER}")
    private String username;

    @Value("${MAIL_PASSWORD}")
    private String password;

    @Value("${MAIL_HOST}")
    private String host;

    @Value("${MAIL_PORT}")
    private String port;

    public Emailing() {
    }

    @PostConstruct
    public void init() {
    }

    public boolean SendMail(String reason, String text, Appointment appointment, User doctor) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(appointment.getEmail()));
            message.setSubject("Kért állatorvosi időpont vizsgálatának lezárása");
            message.setText(formattedText(text, reason, appointment, doctor));

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String formattedText(String text, String reason, Appointment appointment, User doctor) {
        String txt = "Tisztelt " + appointment.getKeeperName() + "!\n\n";
        txt += "A mai napon Önnek volt egy időpontja nálunk, a BAU Állatorvosi Rendelőnél, ami lezárult az alábbi indokkal:\n";
        txt += reason.replace("_", " ") + "\n\n";
        if (text != null) {
            txt += doctor.getSurname() + " " + doctor.getForename() + " megjegyzése a vizsgálathoz:\n";
            txt += text + "\n";
        }
        txt += "\nEz egy automatikusan generált üzenet, kérjük ne válaszoljon rá!\n\nTisztelettel:\nBAU Állatorvosi Rendelő";

        return txt;
    }
}
