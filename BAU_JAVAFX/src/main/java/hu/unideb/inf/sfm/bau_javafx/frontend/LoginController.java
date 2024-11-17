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

    @FXML
    void resetPassword(ActionEvent event) {
        String uname = username.getText();
        if (uname.isEmpty()) {
            new Alerts().LoginAlert("Kötelező kitölteni a felhasználónév mezőt a beazonosításhoz!");
            return;
        }
        User user = JavaFXMain.manager.getUser(uname);
        if (user == null) {
            new Alerts().LoginAlert("Nem létezik " + uname + " nevű felhasználó!");
            return;
        }
        user.setResetPassword(true);
        if (!JavaFXMain.manager.saveUser(user)) {
            new Alerts().ErrorAlert("Mentési hiba", "Nem sikerült elmenteni a változásokat! Próbálja később");
            return;
        }
        new Alerts().InformationAlert("Rendszergazda értesítve", "Kérjük várja meg, míg a rendszergazda beállít egy ideiglenes jelszót!");
        return;
    }

    void loginCheck(String username, String password) throws Exception {
        if (username.isEmpty() || password.isEmpty()) {
            new Alerts().LoginAlert("Kötelező kitölteni mindkét mezőt!");
            return;
        }
        //Checking if they are in the system:
        User user = JavaFXMain.manager.getUser(username);
        if (user == null) {
            new Alerts().LoginAlert("Nem létezik " + username + " nevű felhasználó!");
            return;
        }
        if (!user.checkPassword(password)) {
            new Alerts().LoginAlert("Hibás bejelentkezési adatok!");
            return;
        }
        if (user.isResetPassword()) {
            new Alerts().ResetPasswordAlert(user);
            this.password.setText("");
            return;
        }
        if (user.isFirstLogin()) {
            new Alerts().FirstLoginAlert(user);
            this.password.setText("");
            return;
        }
        if (user.getUsertype().name().equals("MANAGER")) {
            startManager((Stage) login_button.getScene().getWindow(), user);
        } else {
            startDoctor((Stage) login_button.getScene().getWindow(), user);
        }
    }

    public void startDoctor(Stage stage, User user) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/DoctorFXML.fxml"));
            Parent root = fxmlLoader.load();

            DoctorController doctorcontroller = fxmlLoader.getController();
            doctorcontroller.setLoggedInUser(user);

            Stage doctorStage = new Stage();
            doctorStage.setTitle("BAU Doktor nézet");
            doctorStage.setScene(new Scene(root));

            Image image = new Image(getClass().getResourceAsStream("/images/baulog.png"));
            doctorStage.getIcons().add(image);

            doctorStage.show();

            stage.close();
        } catch (IOException e) {
            System.err.println("Error loading DoctorFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void startManager(Stage stage, User user) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/ManagerFXML.fxml"));
            Parent root = fxmlLoader.load();

            ManagerController managerController = fxmlLoader.getController();
            managerController.setLoggedInUser(user);

            Stage managerStage = new Stage();
            managerStage.setTitle("BAU Menedzser nézet");
            managerStage.setScene(new Scene(root));

            Image image = new Image(getClass().getResourceAsStream("/images/baulog.png"));
            managerStage.getIcons().add(image);
            managerStage.show();

            stage.close();
        } catch (IOException e) {
            System.err.println("Error loading ManagerFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}