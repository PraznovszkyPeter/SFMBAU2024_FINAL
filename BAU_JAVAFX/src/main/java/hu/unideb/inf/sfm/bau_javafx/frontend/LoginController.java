package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    Stage stage;

    @FXML
    private Button exit_button;

    @FXML
    private Button login_button;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    void exit(ActionEvent event) {
        JavaFXMain.manager.stop();
        stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void login(ActionEvent event) throws Exception {
        loginCheck(username.getText(), password.getText());
    }

    void loginCheck(String username, String password) throws Exception {
        if(username.isEmpty() || password.isEmpty()) {
            new Alerts().LoginAlert("Kötelező kitölteni mindkét mezőt!");
            return;
        }
        //Checking if they are in the system:
        User user = JavaFXMain.manager.getUser(username);
        if (user == null) {
            new Alerts().LoginAlert("Nem létezik "+username+" nevű felhasználó!");
            return;
        }
        if (!user.checkPassword(password)) {
            new Alerts().LoginAlert("Hibás bejelentkezési adatok!");
            return;
        }
        if (user.isResetPassword())
        {
            new Alerts().ResetPasswordAlert();
            user.setPassword(Password.SetPassword("Adja meg az új jelszavát!"));
            user.setResetPassword(false);
            if (!JavaFXMain.manager.saveUser(user)) {
                new Alerts().ErrorAlert("Mentési hiba","Nem sikerült elmenteni a változásokat! Próbálja később");
                return;
            }
            this.password.setText("");
            new Alerts().LoginAfterPasswordSetting();
            return;
        }
        if (user.getUsertype().name().equals("MANAGER"))
        {
            startManager((Stage) login_button.getScene().getWindow());
        }
        else{
            startDoctor((Stage) login_button.getScene().getWindow());
        }
    }

    public void startDoctor(Stage stage) throws Exception{
        try {
            // Load the DoctorFXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/DoctorFXML.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage and set the scene
            Stage doctorStage = new Stage();
            doctorStage.setTitle("BAU Doktor nézet");
            doctorStage.setScene(new Scene(root));

            Image image = new Image(getClass().getResourceAsStream("/images/baulog.png"));
            doctorStage.getIcons().add(image);

            doctorStage.show();

            // Close the current stage (Login)
            stage.close();
        } catch (IOException e) {
            System.err.println("Error loading DoctorFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startManager(Stage stage) throws Exception{
        try {
            // Load the ManagerFXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/ManagerFXML.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage and set the scene
            Stage managerStage = new Stage();
            managerStage.setTitle("BAU Menedzser nézet");
            managerStage.setScene(new Scene(root));

            Image image = new Image(getClass().getResourceAsStream("/images/baulog.png"));
            managerStage.getIcons().add(image);

            managerStage.show();

            // Close the current stage (Login)
            stage.close();
        } catch (IOException e) {
            System.err.println("Error loading ManagerFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
