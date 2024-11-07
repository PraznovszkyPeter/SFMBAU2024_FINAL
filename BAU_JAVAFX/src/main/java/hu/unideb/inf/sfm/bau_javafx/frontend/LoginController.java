package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class LoginController {
    @Value("${SITE_MANAGER}")
    private String manager;
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
    void login(ActionEvent event) {
        loginCheck(username.getText(), password.getText());
    }

    void loginCheck(String username, String password)
    {
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
        if (!user.getPassword().equals(password)) {
            new Alerts().LoginAlert("Hibás bejelentkezési adatok!");
            return;
        }
        if (username.equals(manager))
        {
            System.out.println("This will load Manager View");
        }
        else{
            openDoctor((Stage) login_button.getScene().getWindow());
        }
    }

    public void openDoctor(Stage currentStage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DoctorFXML.fxml"));
            Parent root = fxmlLoader.load();

            // Create and configure the new stage
            Stage stage = new Stage();
            stage.setTitle("BAU");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);

            // Set the icon for the new stage
            Image image = new Image(LoginController.class.getResourceAsStream("images/baulog.png"));
            stage.getIcons().add(image);

            // Show the new stage
            stage.show();

            // Close the provided current stage
            currentStage.close();

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
