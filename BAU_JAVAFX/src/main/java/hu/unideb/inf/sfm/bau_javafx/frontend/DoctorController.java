package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.Appointment;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static hu.unideb.inf.sfm.bau_javafx.frontend.UserEditor.UserEdit;

public class DoctorController {
    @FXML
    private Label animalSpecies;

    @FXML
    private Label animalComplaint;

    @FXML
    private Label keeperEmail;

    @FXML
    private Label keeperMobile;

    @FXML
    private Label keeperName;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, LocalDateTime> visitDateColumn;

    @FXML
    private TableColumn<Appointment, String> keeperNameColumn;

    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private User loggedInUser;

    @FXML
    public void initialize() {
        visitDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAppointmentDate()));
        keeperNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKeeperName()));
        appointmentTable.setItems(appointments);

        appointmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showAppointmentDetails(newValue);
        });
    }

    @FXML
    void ChangePassword(ActionEvent event) {
        Password.ChangePassword(this.loggedInUser);
    }

    @FXML
    void EditMyData(ActionEvent event) throws Exception {
        UserEdit(getClass(),this.loggedInUser.getUsername() + " adatainak változtatása", this.loggedInUser, null);
    }

    @FXML
    void closeVisit(ActionEvent event) {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/AppointmentCloserFXML.fxml"));
                Parent root = fxmlLoader.load();

                AppointmentCloserController controller = fxmlLoader.getController();

                controller.setClosingAttributes(selectedAppointment, loggedInUser, this);

                Stage appointmentStage = new Stage();
                appointmentStage.setTitle("Időpont lezárása");
                appointmentStage.setScene(new Scene(root));

                Image image = new Image("/images/baulog.png");
                appointmentStage.getIcons().add(image);
                appointmentStage.show();
            } catch (IOException e) {
                System.err.println("Error loading UserEditorFXML: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void showAppointmentDetails(Appointment appointment) {
        animalSpecies.setText("");
        animalComplaint.setText("");
        keeperName.setText("");
        keeperEmail.setText("");
        keeperMobile.setText("");


        if (appointment != null) {
            animalSpecies.setText(appointment.getSpecies());
            animalComplaint.setText(appointment.getComplaint());
            keeperName.setText(appointment.getKeeperName());
            keeperEmail.setText(appointment.getEmail());
            keeperMobile.setText(appointment.getPhoneNumber());
        }
    }

    public void reloadAppointments() {
        this.appointments.clear();

        List<Appointment> updatedAppointmentList = JavaFXMain.manager.getAppointments();

        if (updatedAppointmentList != null) {
            this.appointments.setAll(updatedAppointmentList);
        }

        this.appointmentTable.refresh();

        if (!this.appointments.isEmpty()) {
            this.appointmentTable.getSelectionModel().clearSelection();
            showAppointmentDetails(null);
        }
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.reloadAppointments();
    }
}
