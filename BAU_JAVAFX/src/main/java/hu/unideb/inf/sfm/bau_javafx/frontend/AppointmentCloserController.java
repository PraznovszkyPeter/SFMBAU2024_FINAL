package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;

@Component
public class AppointmentCloserController {
    @FXML
    private Label emailAddress;

    @FXML
    private Button sendButton;

    @FXML
    private ComboBox<appTypes> appointmentTypes;

    @FXML
    private TextArea commentArea;

    public enum appTypes {NEM_JELENT_MEG, MŰTÉT_SZÜKSÉGES, GYÓGYSZERT_KAPOTT}

    private Appointment closingAppointment;
    private User doctor;
    private DoctorController doctorController;

    @FXML
    void sendMail(ActionEvent event) throws MessagingException {
        if (JavaFXMain.manager.sendEmail(appointmentTypes.getValue().toString(), commentArea.getText(), closingAppointment, doctor))
        {
            if (JavaFXMain.manager.DeleteAppointment(closingAppointment))
            {
                new Alerts().InformationAlert("Sikeres mentés", "Időpont törölve, e-mail elküldve.");
            }
            else
            {
                new Alerts().ErrorAlert("Sikertelen törlés", "E-mail elküldve, időpont nem lett törölve!");
            }
        }
        else
        {
            new Alerts().ErrorAlert("Sikertelen kapcsolat", "Nem sikerült elküldeni az e-mailt, időpont is megmaradt!");
        }

        this.doctorController.reloadAppointments();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setClosingAttributes(Appointment appointment, User doctor, DoctorController doctorController) {
        this.closingAppointment = appointment;
        this.doctor = doctor;
        this.doctorController = doctorController;
        emailAddress.setText(appointment.getEmail());
    }

    @FXML
    public void initialize() {
        sendButton.setDisable(true);

        appointmentTypes.setItems(FXCollections.observableArrayList(appTypes.values()));

        appointmentTypes.valueProperty().addListener((observable, oldValue, newValue) -> checkField());
    }

    private void checkField() {
        sendButton.setDisable(appointmentTypes.getValue() == null);
    }
}
